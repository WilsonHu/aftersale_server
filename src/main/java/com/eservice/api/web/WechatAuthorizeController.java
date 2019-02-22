package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.WxWebAccessTokenInfo;
import com.eservice.api.service.impl.UserServiceImpl;
import com.eservice.api.service.impl.WechatUserInfoServiceImpl;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/10/15.
*/
@Controller
@RequestMapping("/wechat/authorize")
public class WechatAuthorizeController {

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
     * 这个获取的token是 网页授权access_token
     */
    @Value("${wx.urlGetOauth2AccessToken}")
    private String urlGetOauth2AccessToken;

    /**
     * 该rul在网页授权时 根据toekn（包含unionId,openId）获取用户信息
     */
    @Value("${wx.urlGetUserinfo}")
    private String urlGetUserinfo;

    @Resource
    private CommonService commonService;

    private Logger logger = Logger.getLogger(WechatAuthorizeController.class);

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    @Value("${debug.flag}")
    private String debugFlag;

    private String globalMsg = null;

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
//    public String wechatRedirect(HttpServletRequest request) {
//        request.setAttribute("key", "hello world");
//        return "success";
//    }
    public String wechatRedirect(@RequestParam String code, HttpServletRequest request) throws IOException {

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
                request.setAttribute("key", "授权成功！");
                return "success";
            } else {
                request.setAttribute("key", "授权失败，请重试！");
                return "success";            }
        } else {
            if(globalMsg == null){
                request.setAttribute("key", "授权失败，获取用户信息为空！");
                return "success";
            } else {
                request.setAttribute("key", "该用户已经授过权！");
                return "success";
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
        }else {
            return nick_name;
        }
    }
}