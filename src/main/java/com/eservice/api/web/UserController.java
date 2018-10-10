package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.user.StaffInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.model.user.UserInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qq.weixin.mp.aes.AesException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import javax.xml.parsers.*;
import java.io.IOException;
import java.io.StringReader;
import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.xml.sax.SAXException;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/user")
public class UserController {
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

    @Resource
    private CommonService commonService;

    String token = "tycljccToken";
    // 需要加密的明文 TBD
    String encodingAesKey = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFG";

    @PostMapping("/addStaff")
    public Result addStaff(@RequestBody @NotNull User user) {
        if(userService.selectByAccount(user.getAccount()) != null) {
            return ResultGenerator.genFailResult("用户名已存在！");
        }
        user.setPassword("password");
        user.setValid("1");
        user.setCreateTime(new Date());
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull User user) {

        if(user.getAgent() == null) {
            user.setAgent(0);
        }
        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectUsers")
    public Result selectUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                              Integer currentUserAgent, String account, String name, Integer roleId, Boolean isAgent, Integer agent, String valid, Integer userType) {
        PageHelper.startPage(page, size);
        List<User> list = userService.selectUsers(currentUserAgent,account, name, roleId, isAgent, agent, valid, userType);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/requestLogin")
    public Result requestLogin(@RequestParam String account, @RequestParam String password,
                               @RequestParam String unionid) {
        boolean result = true;

        if(account == null || "".equals(account)) {
            return ResultGenerator.genFailResult("账号不能为空！");
        } else if(password == null || "".equals(password)) {
            return ResultGenerator.genFailResult("密码不能为空！");
        }else {
            User user  = userService.requestLogin(account, password,unionid);
            if(user == null) {
                return ResultGenerator.genFailResult("账号/密码/unionid 不正确！");
            }else {
                return ResultGenerator.genSuccessResult(user);
            }
        }
    }

    /**
     * 根据参数返回该类型的用户
     * 不带参数，则不按该参数过滤
     */
    @PostMapping("/getUsersByType")
    public Result getUsersByType(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                 String roleId,
                                 String agentId,
                                 String customerCompany) {
        PageHelper.startPage(page, size);
        List<UserInfo> list = userService.getUsersByType(roleId, agentId, customerCompany);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据参数返回该类型的用户
     * 不带参数，则不按该参数过滤
     */
    @PostMapping("/getStaffByParam")
    public Result getStaffByParam(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                 String agentId) {
        PageHelper.startPage(page, size);
        List<StaffInfo> list = userService.getStaffByParam(agentId);
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

    /// TODO: 待完成 ...
    @GetMapping("/wechatMessageVerify")
    public Result wechatMessageVerify(//@RequestParam String signature,
                                      @RequestParam String timestamp,
                                      @RequestParam String nonce, //随机数
                                       String echostr) {
        String replyMsg = " 中文<xml><ToUserName><![CDATA[oia2TjjewbmiOUlr6X-1crbLOvLw]]></ToUserName><FromUserName><![CDATA[gh_7f083739789a]]></FromUserName><CreateTime>1407743423</CreateTime><MsgType><![CDATA[video]]></MsgType><Video><MediaId><![CDATA[eYJ1MbwPRJtOvIEabaxHs7TX2D-HV71s79GUxqdUkjm6Gs2Ed1KF3ulAOA9H1xG0]]></MediaId><Title><![CDATA[testCallBackReplyVideo]]></Title><Description><![CDATA[testCallBackReplyVideo]]></Description></Video></xml>";

        try {
            /**
             * 提供接收和推送给公众平台消息的加解密接口
             */
            WXBizMsgCrypt pc = new WXBizMsgCrypt(token, encodingAesKey, wxspAppid);

            /**
             * 将公众平台回复用户的消息加密打包
             */
            String mingwen = pc.encryptMsg(replyMsg, timestamp, nonce);

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

            //
            // 公众平台发送消息给第三方，第三方处理
            //

            // 第三方收到公众号平台发送的消息
            String result2 = pc.decryptMsg(msgSignature, timestamp, nonce, fromXML);
            System.out.println("解密后明文: " + result2);
            return ResultGenerator.genSuccessResult("解密后明文: " + result2);

        } catch (AesException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResultGenerator.genFailResult("fail");
    }
}
