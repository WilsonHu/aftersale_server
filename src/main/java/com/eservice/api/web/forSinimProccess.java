package com.eservice.api.web;

import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineInfosInProcessDb;
import com.eservice.api.model.sinsim_process.contact_form.ContactFormDetail;
import com.eservice.api.model.user.StaffInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.model.user.UserInfo;
import com.eservice.api.service.common.PrepareUnAssignedMachineService;
import com.eservice.api.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* Class Description: 给生产部用，生产部调用这边的接口。
* @author Wilson Hu
* @date 2020/08/11.
*/

@RestController
@RequestMapping("/for/sinimproccess")
public class forSinimProccess {
    @Resource
    private UserServiceImpl userService;
    private Logger logger = Logger.getLogger(forSinimProccess.class);

    /**
     * 给生产部用，轮到下一步签核时，让售后系统去查生产部的数据，然后由售后系统来发送微信消息推送
     * 避免了在生产部也搞一套微信的东西。
     * @return
     */

    private Connection sinsimMysqlConn;

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";

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
     *  SELECT
     *
     FROM
     contact_form cf
     LEFT JOIN contact_sign cs ON cs.contact_form_id = cf.id
     WHERE
     1 = 1
     AND cf.`status` = '联系单审核中'
     */
    @PostMapping("/timeToCheckProcess")
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



}
