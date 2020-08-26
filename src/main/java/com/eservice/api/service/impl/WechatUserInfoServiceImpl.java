package com.eservice.api.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.dao.WechatUserInfoMapper;
import com.eservice.api.model.user.User;
import com.eservice.api.model.wechat_user_info.WechatUserInfo;
import com.eservice.api.service.WechatUserInfoService;
import com.eservice.api.core.AbstractService;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.WxMessageTemplateJsonData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/10/15.
*/
@Service
@Transactional
public class WechatUserInfoServiceImpl extends AbstractService<WechatUserInfo> implements WechatUserInfoService {

    private Logger logger = Logger.getLogger(WechatUserInfoServiceImpl.class);

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    @Resource
    private UserServiceImpl userService;

    @Resource
    private WechatUserInfoMapper wechatUserInfoMapper;

    @Resource
    private CommonService commonService;

    @Value("${debug.flag}")
    private String debugFlag;

    /**
     * 公众号
     */
    @Value("${wx.gzhAppid}")
    private String wxGzhAppid;

    @Value("${wx.gzhSecret}")
    private String wxGzhSecret;

    @Value("${wx.wxspAppidKehuduan}")
    private String wxspAppidKehuduan;

    @Value("${wx.wxspAppidYuangongduan}")
    private String wxspAppidYuangongduan;

    @Value("${specialAccount1ToReceiveRepairRequest}")
    private String specialAccount1ToReceiveRepairRequest;

    @Value("${specialAccount2ToReceiveRepairRequest}")
    private String specialAccount2ToReceiveRepairRequest;

    /**
     * 该url获取模板
     */
    @Value("${wx.urlGetAccessToken}")
    private String urlGetAccessToken;

    public WechatUserInfo getWechatUserInfoByUnionId(String unionId) {
        return wechatUserInfoMapper.getWechatUserInfoByUnionId(unionId);
    }

    public WechatUserInfo getWechatUserInfoByAccount(String account){
        return wechatUserInfoMapper.getWechatUserInfoByAccount(account);
    }


    /**
     * 发送模板消息给openId对应的用户
     *
     * @param account       推送对象的账号
     * @param templateId    消息模板ID
     * @param messageId     具体消息ID
     * @param jsonMsgData
     */
    public String sendMsgTemplate(@RequestParam String account,
                                  @RequestParam String templateId,
                                  @RequestParam String messageId,
                                  @RequestParam String jsonMsgData ) {

        String msg;
        /**
         * 根据 account 找openId
         */
        logger.info("sendMsgTemplate get param code:" + account);
        User user = userService.findBy("account",account);
        if(user == null){
            msg = "推送发送失败，该account不存在！" + account;
            logger.info(msg);
            return msg;
        }
        String wechatUionId = user.getWechatUnionId();

        if(wechatUionId == null || "".equals(account) ){
            msg = "推送发送失败，请" + account + "先关注公众号";
            logger.info(msg);
            return msg;
        }
        logger.info("sendMsgTemplate wechatUionId is " + wechatUionId);
        WechatUserInfo wechatUserInfo = wechatUserInfoService.getWechatUserInfoByUnionId(wechatUionId);
        if( wechatUserInfo == null){
            msg = "根据 wechatUionId 获取用户为空，推送发送失败（）";
            logger.info(msg);
            return msg;
        }
        String openId = wechatUserInfo.getOpenId();
        if(openId == null || openId.isEmpty()){
            msg = "推送发送失败，找不到openId?";
            logger.info(msg);
            return msg;
        }
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObjectDeatailMsg = new JSONObject();
        jsonObject.put("touser",openId);
        jsonObject.put("template_id",templateId);
        // 小程序跳转
        //role=2（管理员）和特别某2个账号，有报修信息时通知一下，但是不要跳转
        if(user.getRoleId() != 2
                && user.getValid().equals("1")
                && !user.getAccount().equals(specialAccount1ToReceiveRepairRequest)
                && !user.getAccount().equals(specialAccount2ToReceiveRepairRequest)) {
            JSONObject jsonMiniProgram = new JSONObject();
            if (user.getRoleId() == Constant.ROLE_ID_CUSTOMER) {
                jsonMiniProgram.put("appid", wxspAppidKehuduan);
            } else if (user.getRoleId() == Constant.ROLE_ID_EMPLOYEE) {
                jsonMiniProgram.put("appid", wxspAppidYuangongduan);
            }
            //jsonMiniProgram.put("pagepath","pages/authorize");
            jsonMiniProgram.put("path", "pages/authorize");
            jsonObject.put("miniprogram", jsonMiniProgram);
        }
        jsonObject.put("topcolor", "#FF0000");

        WxMessageTemplateJsonData wxMessageTemplateJsonData = JSONObject.parseObject(jsonMsgData,WxMessageTemplateJsonData.class);
        if(wxMessageTemplateJsonData == null){
            return "推送发送失败，获取json数据失败";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        switch (messageId){
            case Constant.WX_MSG_1_MACHINE_BIND_TO_CUSTOMER:
                //            {{first.DATA}}
                //            订单号：{{keyword1.DATA}}
                //            产品：{{keyword2.DATA}}
                //            {{remark.DATA}}
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getMachineType()));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getMessageOfMachineBind()));
                break;
            case Constant.WX_MSG_2_INSTALL_TASK_TO_EMPLOYEE:
                //            {{first.DATA}}
                //            任务号：{{keyword1.DATA}}
                //            任务类型：{{keyword2.DATA}}
                //            执行人：{{keyword3.DATA}}
                //            分派人：{{keyword4.DATA}}
                //            分派时间：{{keyword5.DATA}}
                //            {{remark.DATA}}
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getInstallTaskMessage()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getInstallChargePerson()));
                jsonObjectDeatailMsg.put("keyword4", toJson("信胜"));
                jsonObjectDeatailMsg.put("keyword5", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getInstallPlanDate())));
                break;
            case Constant.WX_MSG_3_MAINTAIN_TASK_TO_EMPLOYEE:
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getMaintainTaskMessage()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getMaintainChargePerson()));
                jsonObjectDeatailMsg.put("keyword4", toJson("信胜"));
                jsonObjectDeatailMsg.put("keyword5", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getMaintainPlanDate())));
                break;
            case Constant.WX_MSG_4_REPAIR_TASK_TO_EMPLOYEE:
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getRepairTaskMessage()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getRepairChargePerson()));
                jsonObjectDeatailMsg.put("keyword4", toJson("信胜"));;
                jsonObjectDeatailMsg.put("keyword5", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getRepairPlanDate())));
                break;
            case Constant.WX_MSG_5_INSTALLER_ACCEPT_TO_CUSTOMER:
                //            {{first.DATA}}
                //            订单号：{{keyword1.DATA}}
                //            工程师姓名：{{keyword2.DATA}}
                //            工程师电话：{{keyword3.DATA}}
                //            上门时间：{{keyword4.DATA}}
                //            {{remark.DATA}}"
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getInstallChargePerson()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getInstallChargePersonPhone()));
                jsonObjectDeatailMsg.put("keyword4", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getInstallPlanDate())));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getInstallTaskAcceptedMessage()));
                break;
            case Constant.WX_MSG_6_MAINTAIN_ACCEPT_TO_CUSTOMER:
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getMaintainChargePerson()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getMaintainChargePersonPhone()));
                jsonObjectDeatailMsg.put("keyword4", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getMaintainPlanDate())));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getMaintainTaskAcceptedMessage()));
                break;
            case Constant.WX_MSG_7_REPAIR_ACCEPT_TO_CUSTOMER:
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getRepairChargePerson()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getRepairChargePersonPhone()));
                jsonObjectDeatailMsg.put("keyword4", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getRepairPlanDate())));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getRepairTaskAcceptedMessage()));
                break;

            case Constant.WX_MSG_8_INSTALL_DONE_TO_CUSTOMER:
                //            {{first.DATA}}
                //            任务名称：{{keyword1.DATA}}
                //            负责人：{{keyword2.DATA}}
                //            提交时间：{{keyword3.DATA}}
                //            {{remark.DATA}}
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getInstallTaskName()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getInstallChargePerson()));
                jsonObjectDeatailMsg.put("keyword3", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getInstallActualTime())));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getInstallTaskDoneMessage()));
                break;
            case Constant.WX_MSG_9_MAINTAIN_DONE_TO_CUSTOMER:
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMaintainTaskName()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getMaintainChargePerson()));
                jsonObjectDeatailMsg.put("keyword3", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getMaintainActualTime())));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getMaintainTaskDoneMessage()));
                break;
            case Constant.WX_MSG_10_REPAIR_DONE_TO_CUSTOMER:
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getRepairTaskName()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getRepairChargePerson()));
                jsonObjectDeatailMsg.put("keyword3", toJson(simpleDateFormat.format(wxMessageTemplateJsonData.getRepairActualTime())));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getRepairTaskDoneMessage()));
                break;
            case Constant.WX_MSG_11_REPAIR_REQUEST_TO_ADMIN:
                jsonObjectDeatailMsg.put("first", toJson(wxMessageTemplateJsonData.getRepairRequestGot()));
                jsonObjectDeatailMsg.put("keyword1", toJson(wxMessageTemplateJsonData.getMachineNameplate()));
                jsonObjectDeatailMsg.put("keyword2", toJson(wxMessageTemplateJsonData.getCustomerName()));
                jsonObjectDeatailMsg.put("keyword3", toJson(wxMessageTemplateJsonData.getMachineType()));
                jsonObjectDeatailMsg.put("remark", toJson(wxMessageTemplateJsonData.getRepairRequestBornMessage()));
                break;
            default:
                return "推送发送失败，未定义的消息 " + messageId;
        }
        jsonObject.put("data", jsonObjectDeatailMsg);
        if( sendTemplate(jsonObject) == true){
            return Constant.WX_MSG_SEND_SUCCESS; //"模板发送成功！";
        } else {
            return "推送发送失败！";
        }
    }

    /**
     * 发送模板消息
     * @param json
     * @return
     */
    public boolean sendTemplate( JSONObject json){

        boolean flag = false;
//        String requestUrl="https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
        String token = this.getAccessToken();
        requestUrl=requestUrl.replace("ACCESS_TOKEN", token);

        if(debugFlag.equalsIgnoreCase("true")){
            logger.info("sendTemplate send as toJsonString: " + json.toJSONString());
            logger.info("sendTemplate send as toString:  " + json.toString());
        }
        String  result = commonService.httpsRequest(requestUrl, "POST", json.toJSONString());
        if(result!=null){
            JSONObject jsonResult = JSON.parseObject(result);
            int errorCode=jsonResult.getInteger("errcode");
            String errorMessage=jsonResult.getString("errmsg");
            if(errorCode==0){
                logger.info("模板消息发送success");
                flag = true;
            }else{
                logger.info("模板消息发送失败: " + errorCode + "," + errorMessage);
                flag = false;
            }
        }
        return flag;
    }

    public  String getAccessToken(){

        String url = urlGetAccessToken.replace("APPID", wxGzhAppid);
        url = url.replace("APPSECRET", wxGzhSecret);
        JSONObject resultJson =null;
        String result = commonService.httpsRequest(url, "POST", null);
        try {
            if(result == null){
                logger.info("httpsRequest 请求 " + url + "，返回 为null");
                return "httpsRequest 请求 " + url + "，返回 为null";
            } else {
                logger.info("httpsRequest 请求 " + url + "，返回 为 " + result );
            }
            resultJson = JSON.parseObject(result);
            String errmsg = (String) resultJson.get("errmsg");
            if(!"".equals(errmsg) && errmsg != null){  //如果为errmsg为ok，则代表发送成功，公众号推送信息给用户了。
                logger.error("获取access_token失败："+errmsg);
                return "获取access_token失败";
            }
        } catch (JSONException e) {
            logger.error("获取access_token失败：" + e.toString());
            e.printStackTrace();
            return "获取access_token失败" + e.toString();
        }
        System.err.println((String) resultJson.get("access_token"));
        logger.info("getAccessToken() : " + resultJson.get("access_token"));
        return (String) resultJson.get("access_token");
    }

    /**
     * 把value转为json
     * @param value
     * @return
     */
    public JSONObject toJson(String value){
        JSONObject json = new JSONObject();
        json.put("value", value);
        json.put("color", "#173177");//消息字体颜色
        return json;
    }
}
