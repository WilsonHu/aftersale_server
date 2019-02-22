package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.user.User;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.WxWebAccessTokenInfo;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Value("${wx.wxspAppidYuangongduan}")
    private String wxspAppidYuangongduan;

    @Value("${wx.wxspSecretYuangongduan}")
    private String wxspSecretYuangongduan;

    @Value("${wx.wxspAppidKehuduan}")
    private String wxspAppidKehuduan;

    @Value("${wx.wxspSecretKehuduan}")
    private String wxspSecretKehuduan;

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
    private String wxGzhSecret;

    @Value("${wx.token}")
    private String wxToken;

    /**
     *加密所用的秘钥 43位
     */
    @Value("${wx.encodingAesKey}")
    private String wxEncodingAesKey;

    /**
     * 这个获取的token是 网页授权access_token
     */
    @Value("${wx.urlGetOauth2AccessToken}")
    private String urlGetOauth2AccessToken;

    /**
     * 该rul在网页授权时 根据toekn（包含unionId,openId）获取用户信息
     */
    @Value("${wx.urlGetUserinfo}")
    private String urlGetUserinfo;

    /**
     * 该url获取模板
     */
    @Value("${wx.urlGetAccessToken}")
    private String urlGetAccessToken;
    @Resource
    private CommonService commonService;

    private Logger logger = Logger.getLogger(WechatUserInfoController.class);

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    private static final String msgCallerIsValid= "CallerIsValid";

    @Value("${debug.flag}")
    private String debugFlag;

    private String globalMsg = null;

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
     * @param jsCode 微信登录凭证（code）
     * @param roleOfCaller 0:表示员工端，1表示客户端，员工端小程序只能用员工账号登陆，客户端小程序只能用客户账号登陆
     * @return
     */
    @PostMapping("/loginGetUnionIdAndSave")
    public Result loginGetUnionIdAndSave(@RequestParam(defaultValue = "0") String account,
                                         @RequestParam(defaultValue = "0") String password,
                                         @RequestParam(defaultValue = "0") String jsCode,
                                         @RequestParam(defaultValue = "0") String roleOfCaller) {

        String message = null;
        logger.info("loginGetUnionIdAndSave " + account +": " + password + ": " + roleOfCaller);
        if(account == null || "".equals(account)) {
            return ResultGenerator.genFailResult("账号不能为空！");
        } else if(password == null || "".equals(password)) {
            return ResultGenerator.genFailResult("密码不能为空！");
        }else {
            User user  = userService.requestLogin(account, password,null);
            if(user == null) {
                return ResultGenerator.genFailResult("账号/密码 不正确！");
            }else {

                //员工账号 不能登陆 客户小程序，反之也不能。
                String msg = checkCallerIValid(user,roleOfCaller);
                if( msg.equals(msgCallerIsValid) == false){
                    return ResultGenerator.genFailResult(msg);
                }
                Map<String, String> requestUrlParam = setParamAccordCaller(roleOfCaller);

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
                    /**
                     * 需要已授权才允许登陆
                     */
                    if(wechatUserInfoService.getWechatUserInfoByUnionId(unionId) == null){
                        message = "该账号未授权，请先到公众号授权（用于接受公众号消息）";
                        logger.info(message);
                        return ResultGenerator.genFailResult(message);
                    }
                    /**
                     * 账号和微信一一绑定
                     * 比如账号A在微信W1上登陆绑定后，A就无法再在其他微信比如微信W2上登陆。
                     */
                    if(userService.findBy("wechatUnionId",unionId) != null ){
                        logger.info("find user: " + userService.findBy("wechatUnionId",unionId).toString() + " by " + unionId );
                        logger.info("find user: " + userService.findBy("wechatUnionId",unionId).getAccount() + " by " + unionId );
                        /**
                         *  unionId已存在，则要看是否和账号匹配
                         */
                        if( userService.requestLogin(account, password,unionId) == null ) {
                            // A账号已经绑定过A的微信，然后在其他微信上登陆
                            return ResultGenerator.genFailResult("账号密码和微信不匹配！(该账号已经绑定了其他微信或者该微信已经绑定了其他账号)");
                        } else {
                            logger.info("loginGetUnionIdAndSave(), user和unionId匹配");
                            JSONObject userJsonObject = new JSONObject();
                            userJsonObject.put("account",user.getAccount());
                            userJsonObject.put("name", user.getName());
                            userJsonObject.put("id",user.getId());
                            return ResultGenerator.genSuccessResult(userJsonObject);
                        }
                    } else {
                        /**
                         *  该unionId不存在数据库中，且登陆的账号没有和微信绑定过，则保存该unionId到该账号
                         */
                        User user1 = userService.findBy("account",account);
                        if( user1 !=null ){
                            if(user1.getWechatUnionId() !=null && user1.getWechatUnionId() != "" && (!user1.getWechatUnionId().isEmpty())){
                                // B微信没有绑定过，用已经绑定过微信的A账号登陆
                                logger.info("该账号已经绑定其他微信: " + user1.getAccount() + "绑定过微信 " + user1.getWechatUnionId() );
                                return ResultGenerator.genFailResult("该账号已经绑定其他微信: " + user1.getAccount() + "绑定过其他微信 " );
                            }
                        } else {
                            return ResultGenerator.genFailResult("不会发生，account前面验证过");
                        }
                        user.setWechatUnionId(unionId);
                        userService.update(user);
                        logger.info("loginGetUnionIdAndSave(), user update unionId:" + unionId);
                        JSONObject userJsonObject = new JSONObject();
                        userJsonObject.put("account",user.getAccount());
                        userJsonObject.put("name", user.getName());
                        userJsonObject.put("id",user.getId());
                        return ResultGenerator.genSuccessResult(userJsonObject);

                    }

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
     * @param jsCode 微信登录凭证（code）
     * @return
     */
    @PostMapping("/getUsersByJsCode")
    public Result getUsersByJsCode(@RequestParam String jsCode,
                                   @RequestParam(defaultValue = "0") String roleOfCaller ) {
        String message = null;
        Map<String, String> requestUrlParam = setParamAccordCaller(roleOfCaller);

        requestUrlParam.put("js_code", jsCode);
        requestUrlParam.put("grant_type", wxGrant_type);

        String respondStr = commonService.sendPost(wxRequestUrl, requestUrlParam);
        JSONObject jsonObject = JSON.parseObject(respondStr);

        if(jsonObject == null){
            return ResultGenerator.genFailResult("jsonObj null, " + respondStr);
        }
        System.out.print("respondStr: " + respondStr );

        logger.info("getUsersByJsCode() respondStr: " + respondStr );
        String unionId = (String) jsonObject.get("unionid");
        if(unionId != null){
            logger.info("try to find user by unionId: " + unionId);
            User user = userService.findBy("wechatUnionId", unionId);
            if(user != null){
                // 员工账号 不能登陆 客户小程序，反之也不能。
                String msg = checkCallerIValid(user,roleOfCaller);
                if( msg.equals(msgCallerIsValid) ){
                    JSONObject userJsonObject = new JSONObject();
                    userJsonObject.put("account",user.getAccount());
                    userJsonObject.put("name", user.getName());
                    userJsonObject.put("id",user.getId());
                    return ResultGenerator.genSuccessResult(userJsonObject);
                } else {
                    return ResultGenerator.genFailResult(msg);
                }
            } else{
                return ResultGenerator.genFailResult("No user found by the js_code");
            }
        } else {
            message = "no unionId included in respond";
            logger.info(message);
            return ResultGenerator.genFailResult(message);
        }
    }

    private String checkCallerIValid(User user,String roleOfCaller) {
        if ((user.getRoleId() == Constant.ROLE_ID_EMPLOYEE) && (roleOfCaller.equals(Constant.CALLER_IS_KEHUDUAN))) {
            return "员工账号 不能登陆 客户小程序";
        }
        if ((user.getRoleId() == Constant.ROLE_ID_CUSTOMER || user.getRoleId() == Constant.ROLE_ID_CUSTOMER_CONTACT)
                && (roleOfCaller.equals(Constant.CALLER_IS_YUANGONGDUAN))) {
            return "客户账号 不能登陆 员工小程序";
        }
        return msgCallerIsValid;
    }

    /**
     * 根据调用者（哪个小程序）来设置对应的appId等
     * @param roleOfCaller
     * @return
     */

    private Map<String, String> setParamAccordCaller(String roleOfCaller ){

        Map<String, String> requestUrlParam = new HashMap<String, String>();
        if(roleOfCaller.equals(Constant.CALLER_IS_YUANGONGDUAN) ){
            requestUrlParam.put("appid", wxspAppidYuangongduan);
            requestUrlParam.put("secret", wxspSecretYuangongduan);
        } else if(roleOfCaller.equals(Constant.CALLER_IS_KEHUDUAN)){
            requestUrlParam.put("appid", wxspAppidKehuduan);
            requestUrlParam.put("secret", wxspSecretKehuduan);
        }
        return requestUrlParam;
    }
    /**
     * 验证消息来自微信。对比签名：发送来的消息的签名和本地生成的签名。
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

        if(debugFlag.equalsIgnoreCase("true")) {
            logger.info("====wechatMessageVerify.  signature ========" + signature);
            logger.info("====wechatMessageVerify.  timestamp ========" + timestamp);
            logger.info("====wechatMessageVerify.  nonce ========" + nonce);
            logger.info("====wechatMessageVerify.  echostr ========" + echostr);
        }

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
             * 如果签名不相同，则抛出异常
             */
//            String result2 = pc.decryptMsg(signature, timestamp, nonce, echoStr);
            String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
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

    /**
     * 用户授权，"确认登录"以后,回调的地址
     * 配置的跳转地址：
     * https://open.weixin.qq.com/connect/oauth2/authorize?
     * appid=wx930d6e916143e08c
     * &redirect_uri=https%3A%2F%2Feservice-tech.cn%2Fapi%2Fwechat%2Fuser%2Finfo%2FwechatRedirect
     * &response_type=code
     * &scope=snsapi_userinfo
     * &state=eservice
     * &connect_redirect=1#wechat_redirect
     * 即微信跳转到 https://eservice-tech.cn/api/wechat/user/info/wechatRedirect，并带上code参数（微信生成）
     * @param code  授权码,有了授权码以后才能获取用户的openID, 再保存到数据库
     * @return
     */
    @RequestMapping("/wechatRedirect")
    public String wechatRedirect(@RequestParam String code) throws IOException {

        String accessURL = String.format(urlGetOauth2AccessToken, wxGzhAppid, wxGzhSecret, code);
        String result = commonService.getHttpsResponse(accessURL, "GET");

        if(debugFlag.equalsIgnoreCase("true")) {
            logger.info("wechatRedirect get param code:" + code);
            logger.info("getAccessToke in: " + result);
        }

        Gson gson = new Gson();
        WxWebAccessTokenInfo tokenInfo = gson.fromJson(result, WxWebAccessTokenInfo.class);

        // 通过获取到的access_token和openid获取用户的信息
        WechatUserInfo wechatUserInfo = getUserinfo(tokenInfo);
        if(wechatUserInfo != null) {
            // 将用户的信息存进数据库
            wechatUserInfoService.update(wechatUserInfo);

            if (wechatUserInfo.getUnionID() != null) {
                return "授权成功！";
            } else {
                return "授权失败，请重试！";
            }
        } else {
            if(globalMsg == null){
                return "授权失败，获取用户信息为空";
            } else {
                return "该用户已经授过权";
            }
        }
    }


    /**
     * 通过Token中的ACCESS_TOKEN和openID获取用户信息
     */
    private WechatUserInfo getUserinfo(WxWebAccessTokenInfo tokenInfo) throws UnsupportedEncodingException {

        String realUrl = String.format(urlGetUserinfo, tokenInfo.getAccess_token(), tokenInfo.getOpenid());
        String jsonResult = commonService.getHttpsResponse(realUrl, "GET");

        JSONObject jsonObject = JSON.parseObject(jsonResult);
        if(jsonObject == null){
            logger.info("jsonObject parsed is null");
            return null;
        }
        /**
         * 把授权的用户的信息保存,目前只保存unionId和openId,nickname
         */
        String openId = (String) jsonObject.get("openid");
        String unionId = (String) jsonObject.get("unionid");
        String nickname = (String) jsonObject.get("nickname");
        String nickNameWithoutEmoji = filterEmoji(nickname);
        String sex = null;
        if(jsonObject.get("sex") != null){
           sex = jsonObject.get("sex").toString();

        }
        String province = (String) jsonObject.get("province");
        String city = (String) jsonObject.get("city");
        String country = (String) jsonObject.get("country");
        String headimgurl = (String) jsonObject.get("headimgurl");
        String privilege = null;
        if(jsonObject.get("privilege") != null){
            privilege = jsonObject.get("privilege").toString();
        }

        WechatUserInfo wechatUserInfo = new WechatUserInfo();

        /**
         * 主要记录openId,只要openId存在就记录
         */
        if(openId != null){
            if(wechatUserInfoService.getWechatUserInfoByUnionId(unionId) != null){
                globalMsg = "该用户已经授过权" + nickname;
                logger.info( globalMsg);
                return null;
            }
            wechatUserInfo.setOpenId(openId);
            wechatUserInfo.setUnionID(unionId);
            wechatUserInfo.setNickname(nickNameWithoutEmoji);
            wechatUserInfo.setSex(sex);
            wechatUserInfo.setProvince(province);
            wechatUserInfo.setCity(city);
            wechatUserInfo.setCountry(country);
            wechatUserInfo.setHeadimgrul(headimgurl);
            wechatUserInfo.setPrivilege(privilege);
            wechatUserInfoService.save(wechatUserInfo);
            logger.info("get wechatUserInfo nickname is:" + wechatUserInfo.getNickname());
            return wechatUserInfo;
        } else {
            logger.info("get openid null" );
            return null;
        }
    }


    // todo: token 定时更新

    /**
     * 根据unionId查找对应微信用户
     * 因为用 findBy(fieldName,xxxx) 查找一些字段比如有下划线的字段，会遇到 No such filed无法查找的问题，所以用这个接口替代。
     * @param unionId
     * @return
     */
    @PostMapping("/getWechatUserInfoByUnionId")
    public Result getWechatUserInfoByUnionId(@RequestParam String unionId) {
        WechatUserInfo wechatUserInfo = wechatUserInfoService.getWechatUserInfoByUnionId(unionId);
        return ResultGenerator.genSuccessResult(wechatUserInfo);
    }

    /**
     * 根据 account获取 微信账号信息
     * @param account
     * @return
     */
    @PostMapping("/getWechatUserInfoByAccount")
    public Result getWechatUserInfoByAccount(@RequestParam String account) {
        WechatUserInfo wechatUserInfo = wechatUserInfoService.getWechatUserInfoByAccount(account);
        return ResultGenerator.genSuccessResult(wechatUserInfo);
    }

    /**
     * 把emoji处理为星号
     * @param nick_name
     * @return
     */
    private static String filterEmoji(String nick_name) {
        //nick_name 所获取的用户昵称
        if (nick_name == null) {
            return nick_name;
        }
        Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        Matcher emojiMatcher = emoji.matcher(nick_name);
        if (emojiMatcher.find()) {
            //将所获取的表情转换为*
            nick_name = emojiMatcher.replaceAll("*");
            return nick_name;
        }
        return nick_name;
    }
}