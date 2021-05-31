package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.user.User;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.WxMessageTemplateJsonData;
import com.eservice.api.service.impl.UserServiceImpl;
import com.eservice.api.service.impl.WechatUserInfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Value("${WX_TEMPLATE_1_BOOK_SUCCESS}")
    private String WX_TEMPLATE_1_BOOK_SUCCESS;

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
                logger.error("账号(" + account + ") 密码(" + password + ")不正确！" );
                return ResultGenerator.genFailResult("账号/密码 不正确！");
            }else {
                //这个return 是为了方便本地调试
//                return ResultGenerator.genSuccessResult("aaa");
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
                logger.info("respondStr: " + respondStr );
                String unionId = (String) jsonObject.get("unionid");
                String openId = (String) jsonObject.get("openid");
                if(unionId != null){
                    /**
                     * 需要已授权才允许登陆
                     */
                    WechatUserInfo wechatUserInfo = null;
                    wechatUserInfo = wechatUserInfoService.getWechatUserInfoByUnionId(unionId);
                    if( wechatUserInfo== null){
                        message = "该账号未授权，请先到公众号授权（用于接受公众号消息）";
                        logger.info(message);
                        return ResultGenerator.genFailResult(message);
                    } else {
                        logger.info("已授权, 因为根据该unionId找到了WechatUserInfo用户, nickname为:" + wechatUserInfo.getNickname()
                        + ", openId为：" + wechatUserInfo.getOpenId());
                    }
                    /**
                     * 账号和微信一一绑定
                     * 比如账号A在微信W1上登陆绑定后，A就无法再在其他微信比如微信W2上登陆。
                     */
                    Condition condition = new Condition(User.class);
                    condition.createCriteria().andCondition("wechat_union_id = ", unionId);
                    logger.info("根据unionId " + unionId + " 来查找用户");
                    List<User> userList = null;
                    userList= userService.findByCondition(condition);
                    if (userList != null && userList.size() != 0) {
                        logger.info("找到的用户个数(真实情况下应该是最多1个)：" + userList.size());
                        for(int u=0; u<userList.size(); u++){
                            logger.info(u + "用户:" + userList.get(u).getAccount());
                        }
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
                         *  该unionId不存在于user表中，且登陆的账号没有和微信绑定过，则保存该unionId到该账号
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
                        user.setWechatUnionId(unionId); ///关联
                        userService.update(user);
                        logger.info("loginGetUnionIdAndSave(), user update unionId:" + unionId);
                        JSONObject userJsonObject = new JSONObject();
                        userJsonObject.put("account",user.getAccount());
                        userJsonObject.put("name", user.getName());
                        userJsonObject.put("id",user.getId());
                        return ResultGenerator.genSuccessResult(userJsonObject);

                    }

                } else if(openId != null){
                    logger.info("腾讯直接返回了openId， 说明已经关注且绑定");
                    JSONObject userJsonObject = new JSONObject();
                    userJsonObject.put("account",user.getAccount());
                    userJsonObject.put("name", user.getName());
                    userJsonObject.put("id",user.getId());
                    return ResultGenerator.genSuccessResult(userJsonObject);
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

        if (jsonObject == null) {
            return ResultGenerator.genFailResult("jsonObj null, " + respondStr);
        }
        System.out.print("respondStr: " + respondStr);

        logger.info("getUsersByJsCode() respondStr: " + respondStr);
        String unionId = (String) jsonObject.get("unionid");
        if (unionId != null) {
            logger.info("try to find user by unionId: " + unionId);
            /**
             * 实际真实数据 wechatUnionId是唯一的，
             * 为了调试方便，各个账号都设了相同的wechatUnionId，免去要多个微信账号来调试。取第一个User来推送消息。
             */
//            User user = userService.findBy("wechatUnionId", unionId);
            Condition condition = new Condition(User.class);
            condition.createCriteria().andCondition("wechat_union_id = ", unionId);
            List<User> userList = userService.findByCondition(condition);
            if (userList != null) {
                User user = userList.get(0); // 只取第一个User来推送消息。
                // 员工账号 不能登陆 客户小程序，反之也不能。
                String msg = checkCallerIValid(user, roleOfCaller);
                if (msg.equals(msgCallerIsValid)) {
                    JSONObject userJsonObject = new JSONObject();
                    userJsonObject.put("account", user.getAccount());
                    userJsonObject.put("name", user.getName());
                    userJsonObject.put("id", user.getId());
                    return ResultGenerator.genSuccessResult(userJsonObject);
                } else {
                    return ResultGenerator.genFailResult(msg);
                }
            } else {
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
     * 测试发送公众号消息
     * @param account
     * @return
     */

    @PostMapping("/testMsg")
    public Result testMsg(@RequestParam String account) {
//        WechatUserInfo wechatUserInfo = wechatUserInfoService.getWechatUserInfoByAccount(account);

        WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
        wxMessageTemplateJsonData.setCustomerName("xxxx");
        wxMessageTemplateJsonData.setMachineNameplate( "aaabbb" + "(铭牌号)" );
        wxMessageTemplateJsonData.setMachineType("yyyy");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateStr = formatter.format(new Date());
        String msg = Constant.WX_MSG_1.replace("FactoryDate", dateStr);

        wxMessageTemplateJsonData.setMessageOfMachineBind(msg);
        String message = wechatUserInfoService.sendMsgTemplate(account,
                WX_TEMPLATE_1_BOOK_SUCCESS,
                Constant.WX_MSG_1_MACHINE_BIND_TO_CUSTOMER,
                JSONObject.toJSONString(wxMessageTemplateJsonData));
        return ResultGenerator.genSuccessResult(message);
    }
}