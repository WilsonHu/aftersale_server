package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.user.User;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import com.eservice.api.service.WechatUserInfoService;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.wxWebAccessTokenInfo;
import com.eservice.api.service.impl.UserServiceImpl;
import com.eservice.api.service.impl.WechatUserInfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/10/15.
*/
@RestController
@RequestMapping("/wechat/user/info")
public class WechatUserInfoController {
    @Resource
    private UserServiceImpl userService;

    @Value("${wx.wxspAppid}")
    private String wxspAppid;

    @Value("${wx.wxspSecret}")
    private String wxspSecret;

    @Value("${wx.grant_type}")
    private String wxGrant_type;

    @Value("${wx.requestUrl}")
    private String wxRequestUrl;

    /**
     * 公众号
     */
    @Value("${wx.gzhAppid}")
    private String wxGzhAppid;

    @Value("${wx.gzhSecret}")
    private String gzhSecret;

    @Value("${wx.token}")
    private String wxToken;

    /**
     *加密所用的秘钥 43位
     */
    @Value("${wx.encodingAesKey}")
    private String wxEncodingAesKey;

    private String WX_OAUTH_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private static final String urlGetAccessToken = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token";
    private String urlGetUserinfo = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";


    @Value("${eserviceBaseUrl}")
    private String eserviceBaseUrl;

    private String scope = "snsapi_userinfo"; // 采用网页授权的方式
    @Resource
    private CommonService commonService;

    private Logger logger = Logger.getLogger(CommonService.class);


    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    @PostMapping("/add")
    public Result add(WechatUserInfo wechatUserInfo) {
        wechatUserInfoService.save(wechatUserInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        wechatUserInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(WechatUserInfo wechatUserInfo) {
        wechatUserInfoService.update(wechatUserInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        WechatUserInfo wechatUserInfo = wechatUserInfoService.findById(id);
        return ResultGenerator.genSuccessResult(wechatUserInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<WechatUserInfo> list = wechatUserInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户登陆，向腾讯服务器获取unionId并保存，再返回用户信息（包括unionId）。
     * @param account
     * @param password
     * @param jsCode
     * @return
     */
    @PostMapping("/loginGetUnionIdAndSave")
    public Result loginGetUnionIdAndSave(@RequestParam(defaultValue = "0") String account,
                                         @RequestParam(defaultValue = "0") String password,
                                         @RequestParam(defaultValue = "0") String jsCode) {

        String message = null;
        if(account == null || "".equals(account)) {
            return ResultGenerator.genFailResult("账号不能为空！");
        } else if(password == null || "".equals(password)) {
            return ResultGenerator.genFailResult("密码不能为空！");
        }else {
            User user  = userService.requestLogin(account, password,null);
            if(user == null) {
                return ResultGenerator.genFailResult("账号/密码/unionid 不正确！");
            }else {

                Map<String, String> requestUrlParam = new HashMap<String, String>();
                requestUrlParam.put("appid",wxspAppid);
                requestUrlParam.put("secret",wxspSecret);
                /**
                 * 小程序调用wx.login返回的jsCode
                 */
                requestUrlParam.put("js_code", jsCode);
                requestUrlParam.put("grant_type", wxGrant_type);
                String respondStr = commonService.sendPost(wxRequestUrl, requestUrlParam);
                JSONObject jsonObject = JSON.parseObject(respondStr);

                if(jsonObject == null){
                    return ResultGenerator.genFailResult("jsonObj null, " + respondStr);
                }
                System.out.print("respondStr: " + respondStr );

                String unionId = (String) jsonObject.get("unionid");
                if(unionId != null){
                    user.setWechatUnionId(unionId);
                    userService.update(user);
                    return ResultGenerator.genSuccessResult("account:" +user.getAccount() + ",name:" + user.getName() + ",id:" + user.getId());
                } else {
                    /**
                     * 没有unionId（需要用户先关注公众号）,直接返回具体信息是为了小程序方便。
                     */
                    message = "请先关注公众号";
                    return ResultGenerator.genFailResult(message);
                }
            }
        }
    }

    /**
     * 根据js_code去微信的服务器请求unionID，
     * 如果得到的unionId已经存在我们数据库里面，返回该用户信息
     * @param jsCode
     * @return
     */
    @PostMapping("/getUsersByJsCode")
    public Result getUsersByJsCode(@RequestParam String jsCode ) {
        String message = null;
        Map<String, String> requestUrlParam = new HashMap<String, String>();
        requestUrlParam.put("appid",wxspAppid);
        requestUrlParam.put("secret",wxspSecret);
        requestUrlParam.put("js_code", jsCode);
        requestUrlParam.put("grant_type", wxGrant_type);

        String respondStr = commonService.sendPost(wxRequestUrl, requestUrlParam);
        JSONObject jsonObject = JSON.parseObject(respondStr);

        if(jsonObject == null){
            return ResultGenerator.genFailResult("jsonObj null, " + respondStr);
        }
        System.out.print("respondStr: " + respondStr );

        String unionId = (String) jsonObject.get("unionid");
        if(unionId != null){
            User user = userService.findBy("wechat_union_id", unionId);
            if(user != null){
                return ResultGenerator.genSuccessResult("account:" +user.getAccount() + ",name:" + user.getName() + ",id:" + user.getId());
            } else{
                return ResultGenerator.genFailResult("No user found by the js_code");
            }
        } else {
            message = "no unionId included in respond";
            return ResultGenerator.genFailResult(message);
        }

    }

    /**
     * 验证消息来自微信。
     * 若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。
     *
     * @param signature 	微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     *                      类似： e5c6c537c98ee3524b201a629f676c66779bb4a3、4bd5ea6f6757cd601dade20e7e499683e0c49e36每次变化。
     * @param timestamp     时间戳
     * @param nonce         随机数
     * @param echostr       收到的字符串 (明文，该字符串 待检验是否来自微信) eg: 不限于XML格式，XML格式比如" 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";
     * @return
     */
    @GetMapping("/wechatMessageVerify")
//    @RequestMapping(value = "/wechatMessageVerify", method = RequestMethod.GET,produces = {"text/plain;charset=UTF-8"})
    public void wechatMessageVerify(@RequestParam String signature,
                                    @RequestParam String timestamp,
                                    @RequestParam String nonce,
                                    @RequestParam(name = "echostr", required = false) String echostr, HttpServletResponse response ) {
        String msg = null;

        System.out.println(" aaaaa echostr is : " + echostr);
        logger.info("====wechatMessageVerify.  signature ========" + signature);
        logger.info("====wechatMessageVerify.  timestamp ========" + timestamp);
        logger.info("====wechatMessageVerify.  nonce ========" + nonce);
        logger.info("====wechatMessageVerify.  echostr ========" + echostr);
        try
        {
            /**
             * 提供接收和推送给公众平台消息的加解密接口
             */
            WXBizMsgCrypt pc = new WXBizMsgCrypt(wxToken, wxEncodingAesKey, wxGzhAppid);

            /**
             * 将公众平台回复用户的消息加密打包
             */
            String mingwen = pc.encryptMsg(echostr, timestamp, nonce);
            System.out.println("加密后: " + mingwen);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

            dbf.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            dbf.setFeature("http://xml.org/sax/features/external-general-entities", false);
            dbf.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

            dbf.setXIncludeAware(false);
            dbf.setExpandEntityReferences(false);

            DocumentBuilder db = dbf.newDocumentBuilder();

            StringReader sr = new StringReader(mingwen);
            InputSource is = new InputSource(sr);
            Document document = db.parse(is);

            Element root = document.getDocumentElement();
            NodeList nodelist1 = root.getElementsByTagName("Encrypt");
            NodeList nodelist2 = root.getElementsByTagName("MsgSignature");

            String encrypt = nodelist1.item(0).getTextContent();
            String msgSignature = nodelist2.item(0).getTextContent();

            String format = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><Encrypt><![CDATA[%1$s]]></Encrypt></xml>";
            String fromXML = String.format(format, encrypt);

            /**
             * 第三方收到公众号平台发送的消息
             * 检验消息的真实性，并且获取解密后的明文
             */
//            String result2 = pc.decryptMsg(signature, timestamp, nonce, echoStr);
            String result2 = pc.decryptMsg(signature, timestamp, nonce, fromXML);
            System.out.println("解密后明文: " + result2);
//            return ResultGenerator.genSuccessResult("aaa");
            logger.info("====wechatMessageVerify. out write ======== " + echostr);

            /**
             * 原样返回echostr，不能有双引号
             */
            BufferedOutputStream out = null;
            try {
                out = new BufferedOutputStream(response.getOutputStream());
                out.write(echostr.getBytes());
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        catch (AesException e) {
            e.printStackTrace();
            logger.info("====wechatMessageVerify AesException ======== " + e.toString());
        } catch (ParserConfigurationException e) {
            logger.info("====wechatMessageVerify. ParserConfigurationException ======== " + e.toString());
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
            logger.info("====wechatMessageVerify. SAXException ======== " + e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            logger.info("====wechatMessageVerify. IOException ======== " + e.toString());
        }
    }

//    /**
//     * 通过code换取网页授权access_token
//     * 链接中带有公众号的secret参数，以及获取到的access_token安全级别都非常高，必须只保存在服务器，不允许传给客户端。
//     * @param gzhAppid
//     * @param gzhAppSecret
//     * @param code
//     * @param grantType
//     * @param response
//     * @return
//     *
//     * 后端会根据你传过去的code给前端返回对应的uid（用户id），以及openid等
//     */
//    @GetMapping("/wechatAuthAccessTokenByCode_getUserInfo")
//    public String wechatAuthAccessTokenByCode(@RequestParam String gzhAppid,
//                                    @RequestParam String gzhAppSecret,
//                                    @RequestParam String code,
//                                    @RequestParam String grantType, HttpServletResponse response ) {
//
//        logger.info("=== gzhAppid is " + gzhAppid);
//        logger.info("=== gzhAppSecret is " + gzhAppSecret);
//        logger.info("=== code is " + code);
//
//        /**
//         * 获取code后，请求以下链接获取access_token：
//         *  https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
//         */
//        Map<String, String> requestUrlParam = new HashMap<String, String>();
//        requestUrlParam.put("appid",gzhAppid);
//        requestUrlParam.put("secret",gzhAppSecret);
//        requestUrlParam.put("code", code);
//        requestUrlParam.put("grant_type", grantType);
//        String respondStr = commonService.sendPost(wxRequestUrl, requestUrlParam);
//
//        JSONObject jsonObject = JSON.parseObject(respondStr);
//
//        if(jsonObject == null){
//            return  "jsonObj is null";
//        }
//        System.out.print("respondStr: " + respondStr );
//
//        String unionId = (String) jsonObject.get("access_token");
//        if(unionId != null){
//            logger.info("respondStr is " + respondStr);
//            System.out.println("respondStr is " + respondStr);
//        } else {
//            return  "no access_token found" ;
//        }
//
//        // TODO 查看token是否有效，刷新access_token（如果需要）
//
//        // 拉取用户信息(需scope为 snsapi_userinfo)
//
//    }

//
//    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
//    public void authorizationWeixin(
//            @RequestParam String code,
//            HttpServletRequest request,
//            HttpServletResponse response) throws IOException{
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//
//        PrintWriter out = response.getWriter();
//        LOGGER.info("RestFul of authorization parameters code:{}",code);
//
//        try {
//            String rs = getOauthAccessToken(code);
//            out.write(rs);
//            LOGGER.info("RestFul of authorization is successful.",rs);
//        } catch (Exception e) {
//            LOGGER.error("RestFul of authorization is error.",e);
//        }finally{
//            out.close();
//        }
//    }
//
//    /**
//     * 根据code 获取授权的token 仅限授权时使用，与全局的access_token不同
//     * @param code
//     * @return
//     * @throws IOException
//     * @throws ClientProtocolException
//     */
//    public String getOauthAccessToken(String code) throws ClientProtocolException, IOException{
//
////        String data = redisService.get("WEIXIN_SQ_ACCESS_TOKEN");
//        String data = "WEIXIN_SQ_ACCESS_TOKEN";
//
//        String rs_access_token = null;
//        String rs_openid = null;
//        String url = WX_OAUTH_ACCESS_TOKEN_URL + "?appid=" + wxspAppid + "&secret=" + wxspSecret + "&code=" + code + "&grant_type=authorization_code";
//        if (StringUtils.isEmpty(data)) {
//            synchronized (this) {
//                //已过期，需要刷新
//                String hs = apiService.doGet(url);
//                JSONObject json = JSONObject.parseObject(hs);
//                String refresh_token = json.getString("refresh_token");
//
//                String refresh_url = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+WX_APPID+"&grant_type=refresh_token&refresh_token="+refresh_token;
//                String r_hs = apiService.doGet(refresh_url);
//                JSONObject r_json = JSONObject.parseObject(r_hs);
//                String r_access_token = r_json.getString("access_token");
//                String r_expires_in = r_json.getString("expires_in");
//                rs_openid = r_json.getString("openid");
//
//                rs_access_token = r_access_token;
//                redisService.set("WEIXIN_SQ_ACCESS_TOKEN", r_access_token, Integer.parseInt(r_expires_in) - 3600);
//                LOGGER.info("Set sq access_token to redis is successful.parameters time:{},realtime",Integer.parseInt(r_expires_in), Integer.parseInt(r_expires_in) - 3600);
//            }
//        }else{
//            //还没有过期
//            String hs = apiService.doGet(url);
//            JSONObject json = JSONObject.parseObject(hs);
//            rs_access_token = json.getString("access_token");
//            rs_openid = json.getString("openid");
//            LOGGER.info("Get sq access_token from redis is successful.rs_access_token:{},rs_openid:{}",rs_access_token,rs_openid);
//        }
//
//        return getOauthUserInfo(rs_access_token,rs_openid);
//    }
//
//    /**
//     * 根据授权token获取用户信息
//     * @param access_token
//     * @param openid
//     * @return
//     */
//    public String getOauthUserInfo(String access_token,String openid){
//        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+ access_token +"&openid="+ openid +"&lang=zh_CN";
//        try {
//            String hs = apiService.doGet(url);
//
//            //保存用户信息
//            saveWeixinUser(hs);
//
//            return hs;
//        } catch (IOException e) {
//            LOGGER.error("RestFul of authorization is error.",e);
//        }
//        return null;
//    }

    /**
     * 2、前端把这个code作为数据调用后端登录接口，后端拿到code，加上appid和secret参数
     * 请求 https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code 
     * 来获取access_token, ///
     * 同时拿到openid。
     *
     * 3.后端会先判断数据库里有没有该openid，
     * 如果有的话，后端将数据（用户信息）返回到前端；
     * 如果没有，
     * 请求 /// https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code 
     * 应该是 https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
     * 获取用户信息，返回给前端并存在数据库里。
     */

    /**
     * 引导用户进入微信授权页面,记得需要在微信开发平台修改网页授权页面域名
     * 微信只弹出一次授权,第二次的时候就直接跳转到 redirect_uri 页面去了
     *
     * @return
     */
//    @RequestMapping("/authorization.action")
//    public String authorization() {
//        String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";
//        String APPID = "wx24cdc78e365daee0";
//        String REDIRECT_URI = baseUrl + "/wechat/getAccessToken.action"; // 回调地址
//
//        String SCOPE = "snsapi_userinfo"; // 采用网页授权的方式
//        // 引导用户进入微信用户授权的页面的url
//        String url = String.format(URL, APPID, REDIRECT_URI, SCOPE);
//
//        return "redirect:" + url;
//    }


    /**
     * 引导用户进入微信授权页面,记得需要在微信开发平台修改网页授权页面域名
     * 微信只弹出一次授权,第二次的时候就直接跳转到 redirect_uri 页面去了
     * @return
     */
    @GetMapping("/wechatRedirect")
    public String  wechatRedirect() {
        String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=%s&state=STATE#wechat_redirect";
        String REDIRECT_URI = eserviceBaseUrl + "/wechat/getAccessToken"; // 回调地址

        // 引导用户进入微信用户授权的页面的url
        String url = String.format(URL, wxGzhAppid, REDIRECT_URI, scope);

        logger.info("url is :" + url);
        return "redirect:" + url;
    }

    /**
     * 用户点击页面上的"确认登录"以后,回调的地址
     *
     * @param code  授权码,有了授权码以后才能获取用户的openID, 再保存到数据库
     * @return
     */
    @RequestMapping("/getAccessToken")
    public String getAccessToken(String code,  HttpSession httpSession) throws IOException {

        String accessURL = String.format(urlGetAccessToken, wxGzhAppid, gzhSecret, code);
        String result = commonService.getHttpsResponse(accessURL, "GET");
        logger.info("getAccessToke in: " + result);
        /*
        若请求正确,返回的结果集应如下:
        {
            "access_token":"ACCESS_TOKEN",
            "expires_in":7200,
            "refresh_token":"REFRESH_TOKEN",
            "openid":"OPENID",
            "scope":"SCOPE"
        } */

        Gson gson = new Gson();
        wxWebAccessTokenInfo tokenInfo = gson.fromJson(result, wxWebAccessTokenInfo.class);

        // 通过获取到的access_token和openid获取用户的信息
        WechatUserInfo wechatUserInfo = getUserinfo(tokenInfo);


        // 将用户的信息存进数据库
        wechatUserInfoService.update(wechatUserInfo);/////

//        /**
//         * 这个返回方式，返回jsp文件，设置其中的userinfo
//         */
//        // 获取到ACCESS_TOKEN和openid以后,将它们存进session中去 /// ?
        httpSession.setAttribute("userinfo", wechatUserInfo);
        return "/jsp/userinfo.jsp";
//        return "回调地址是哪？";


    }

    /**
     * 通过Token中的ACCESS_TOKEN和openID获取用户信息
     */
    private WechatUserInfo getUserinfo(wxWebAccessTokenInfo tokenInfo) throws UnsupportedEncodingException {

        String realUrl = String.format(urlGetUserinfo, tokenInfo.getAccess_token(), tokenInfo.getOpenid());
        String jsonResult = commonService.getHttpsResponse(realUrl, "GET");

        // 微信采用ISO-8859-1编码,需要对获取的结果进行编码
        String utfResult = new String(jsonResult.getBytes("ISO-8859-1"), "UTF-8");
        System.out.println("get response is:" + utfResult);
        logger.info("get response userinfo is:" + utfResult);

        JSONObject jsonObject = JSON.parseObject(utfResult);
        if(jsonObject == null){
//       todo
        }
        /**
         * 把授权的用户的信息保存,目前只保存unionId和openId,nickname
         */
        String openId = (String) jsonObject.get("openid");
        String unionId = (String) jsonObject.get("unionid");
        String nickname = (String) jsonObject.get("nickname");

        WechatUserInfo wechatUserInfo = null;
        if(openId != null){
            wechatUserInfo.setOpenId(openId);
            wechatUserInfo.setUnionD(unionId);
            wechatUserInfo.setNickname(nickname);
            wechatUserInfoService.save(wechatUserInfo);

            logger.info("get wechatUserInfo is:" + wechatUserInfo.toString());
            return wechatUserInfo;
        } else {
            logger.info("get openid null" );
            return null;
        }
    }
}
