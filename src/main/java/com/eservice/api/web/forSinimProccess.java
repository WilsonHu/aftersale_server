package com.eservice.api.web;

import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.MachineInfosInProcessDb;
import com.eservice.api.model.sinsim_process.contact_form.ContactFormDetail;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.WxMessageTemplateJsonData;
import com.eservice.api.service.impl.UserServiceImpl;
import com.eservice.api.service.impl.WechatUserInfoServiceImpl;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
* Class Description: 给生产部用，生产部调用这边的接口。
 * 比如，轮到下一步签核时，由售后系统来发送微信消息推送
 * 避免了在生产部也搞一套微信的东西。
* @date 2020/08/11.
*/

@RestController
@RequestMapping("/for/sinimproccess")
public class forSinimProccess {
    @Resource
    private UserServiceImpl userService;
    private Logger logger = Logger.getLogger(forSinimProccess.class);

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    @Value("${WX_TEMPLATE_4_TASK_DONE}")
    private String WX_TEMPLATE_4_TASK_DONE;
	
    @Value("${WX_TEMPLATE_6_SIGN_TASK}")
    private String WX_TEMPLATE_6_SIGN_TASK;

    private Connection sinsimMysqlConn;

    /**
     * 售后数据库
     */
    @Value("${spring.datasource.url}")
    private String aftersale_db_url;

    @Value("${spring.datasource.username}")
    private String user_aftersale_db;

    @Value("${spring.datasource.password}")
    private String password_aftersale_db = "hello123!";

    /**
     * 生产部数据库
     */
    @Value("${spring.datasource_sinsim_db.url}")
    private String sinsim_db_url;

    @Value("${spring.datasource_sinsim_db.username}")
    private String user_sinsim_db;

    @Value("${spring.datasource_sinsim_db.password}")
    private String password_sinsim_db;

    private List<MachineInfosInProcessDb> installedNotBoundedMachineList = new ArrayList<>();

    /**
     *  让生产部每次轮到下一个签核时 调用该接口，来通知售后去查生产部的数据
     *  避免在生产部也要要搞微信公众号一套
     *  --> 还是让生产部系统直接把要通知的人和订单/联系单号发给售后系统
     */
    @PostMapping("/timeToCheckProcess") //暂未用
    public Result timeToCheckProcess() {
        try {

            sinsimMysqlConn = DriverManager.getConnection(sinsim_db_url,user_sinsim_db,password_sinsim_db);
            ResultSet rs2 = sinsimMysqlConn.prepareStatement(" select * "  +
                    "from contact_form cf " +
                    "LEFT JOIN contact_sign cs ON cs.contact_form_id = cf.id " +
                    "where cf.`status` ='联系单审核中' ").executeQuery();

            List<ContactFormDetail> list = new ArrayList<>();
            while(rs2.next()) {
                ContactFormDetail contactFormDetail = new ContactFormDetail();
                contactFormDetail.setOrderNum(rs2.getString("order_num"));
                contactFormDetail.setCurrentStep(rs2.getString("current_step"));
                contactFormDetail.setUpdateTime(rs2.getTime("update_time"));
                contactFormDetail.setApplicantDepartment(rs2.getString("applicant_department"));
                contactFormDetail.setApplicantPerson(rs2.getString("applicant_department"));
                contactFormDetail.setAttachedDuringSign(rs2.getString("attached_during_sign"));
                contactFormDetail.setAttachedDuringSignMan(rs2.getString("attached_during_sign_man"));
                contactFormDetail.setAttachedFile(rs2.getString("attached_file"));
                contactFormDetail.setContactContent(rs2.getString("contact_content"));
                contactFormDetail.setContactContentElse(rs2.getString("contact_content_else"));
                contactFormDetail.setContactTitle(rs2.getString("contact_title"));
                contactFormDetail.setContactType(rs2.getString("contact_type"));
                contactFormDetail.setCreateDate(rs2.getDate("create_date"));
                contactFormDetail.setHopeDate(rs2.getDate("hope_date"));
                contactFormDetail.setNum(rs2.getString("num"));
                contactFormDetail.setStatus(rs2.getString("status"));
                contactFormDetail.setUpdateDate(rs2.getDate("update_date"));
                list.add(contactFormDetail);
            }
            sinsimMysqlConn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("Query data exception: " + e.toString());
        }

        return ResultGenerator.genSuccessResult();
    }

    /**
     * 生产部签核轮到时，发消息给售后系统
     * @param account 轮到谁签核
     * @param machineOrderNum
     * @param lxdNum
     * @param msgInfo
     *
     * eg. curl -X POST localhost/api/for/sinimproccess/sendRemind?account=neimao&&machineOrderNumX=mo111&&msgInfo=msg222
     */
// todo  待推送功能ok后，删除一部分log
    @PostMapping("/sendRemind")
    public Result sendRemind(@RequestParam String account,
                             @RequestParam(defaultValue = "") String machineOrderNum,
                             @RequestParam(defaultValue = "") String lxdNum,
                             @RequestParam(defaultValue = "") String msgInfo) {
        String result;
        String decodedAccount = null;
        String decodedMachineOrderNum = null;
        String decodedLxdNum = null;
        logger.info("From sinsim_process: " + account + ",orderNum: " + machineOrderNum + ", lxdNum: " + lxdNum);
        try {
            decodedAccount = new URLCodec().decode(account);
            decodedMachineOrderNum = new URLCodec().decode(machineOrderNum);
            decodedLxdNum = new URLCodec().decode(lxdNum);
        } catch (DecoderException e) {
            e.printStackTrace();
        }
        logger.info("From sinsim_process: " + decodedAccount + ",decodedMachineOrderNum: " + decodedMachineOrderNum + ", decodedLxdNum: " + decodedLxdNum);
        WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
        try {
            //                {{first.DATA}}
            //                任务名称：{{keyword1.DATA}}
            //                负责人：{{keyword2.DATA}}
            //                提交时间：{{keyword3.DATA}}
            //                {{remark.DATA}}
            if(decodedMachineOrderNum.isEmpty() || decodedMachineOrderNum.equals("")) {
                wxMessageTemplateJsonData.setSignType("联系单签核");
                wxMessageTemplateJsonData.setSignPerson(decodedAccount);
                wxMessageTemplateJsonData.setLxdNumber(decodedLxdNum);
                wxMessageTemplateJsonData.setMsgInfo(msgInfo);
            } else {
                wxMessageTemplateJsonData.setSignType("订单签核");
                wxMessageTemplateJsonData.setSignPerson(decodedAccount);
                wxMessageTemplateJsonData.setMachineOrderNumber(decodedMachineOrderNum);
                wxMessageTemplateJsonData.setMsgInfo(msgInfo);
            }
            logger.info("going to send sendMsgTemplate" );
            result = wechatUserInfoService.sendMsgTemplate(decodedAccount,
                    WX_TEMPLATE_6_SIGN_TASK,
                    Constant.WX_MSG_12_LXD_PUSH_MSG,
                    JSONObject.toJSONString(wxMessageTemplateJsonData));
            logger.info("发送消息给签核人结果：" + result);
            logger.info("sendMsgTemplate result：" + result);
            System.out.println("发送消息给签核人结果 " + result);
        } catch (Exception e) {
            System.out.println("发送消息给签核人失败 " + e.toString());
            e.printStackTrace();
            return ResultGenerator.genFailResult(e.toString());
        }
        return ResultGenerator.genSuccessResult(result);
    }

}
