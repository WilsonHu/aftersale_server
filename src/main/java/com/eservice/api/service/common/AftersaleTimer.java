package com.eservice.api.service.common;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;
import java.util.Date;
import java.util.TimerTask;

public class AftersaleTimer extends TimerTask {

    private Connection mysqlConn;

    private String JDBC_DRIVER = "com.mysql.jdbc.Driver";

    /**
     * 售后数据库
     */
//    @Value("${spring.datasource.url}")
    private String aftersale_db_url = "jdbc:mysql://aftersale_mysql_1:3306/aftersale_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull";
    //"jdbc:mysql://localhost:3306/sinsim_db?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&failOverReadOnly=false";

    @Value("${spring.datasource.username}")
    private String user_aftersale_db = "root";

    @Value("${spring.datasource.password}")
    private String password_aftersale_db = "hello123!";

    /**
     * 生产部数据库 目前不需要，TODO, 后续加上
     */

    private Date now = new Date();//Sun Oct 14 19:56:25 CST 2018

    private java.util.Timer itimer = new java.util.Timer();

    /**
     * 每 60min 发一次
     */
    private int periodMilliSecond = 60*60*1000;

    private Logger logger = Logger.getLogger(CommonService.class);
    public void start(){
        logger.info("==== now " + now.toString() );
        itimer.schedule(this, now, periodMilliSecond);
    }

    public void run() {
        try {
            logger.info("==== timer.run() =====" );
            System.out.println("abc");
            Class.forName(JDBC_DRIVER);
            mysqlConn = DriverManager.getConnection(aftersale_db_url,user_aftersale_db,password_aftersale_db);

            String sql = "select * from role limit ?";
//            String sql = "INSERT into role VALUES (null,'roleName1', ?, null);";
            PreparedStatement rs = null;
            rs = mysqlConn.prepareStatement(sql);
            rs.setInt(1,12);

//            rs.executeUpdate();
            ResultSet ret = rs.executeQuery();
            logger.info("==== timer.run()  get: " + sql);
            rs.close();
            mysqlConn.close();

            /**
             * 生产部数据库先不用发
             */

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            logger.info("==== timer.run()  ex: " + e.toString());
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("==== timer.run()  ex: " + e.toString());
        }
    }
}
