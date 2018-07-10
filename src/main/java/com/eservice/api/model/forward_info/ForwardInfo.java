package com.eservice.api.model.forward_info;

import java.util.Date;
import javax.persistence.*;

@Table(name = "forward_info")
public class ForwardInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 转派时间
     */
    @Column(name = "forword_time")
    private Date forwordTime;

    /**
     * 可以附带一些信息，告知原厂
     */
    private String comment;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取转派时间
     *
     * @return forword_time - 转派时间
     */
    public Date getForwordTime() {
        return forwordTime;
    }

    /**
     * 设置转派时间
     *
     * @param forwordTime 转派时间
     */
    public void setForwordTime(Date forwordTime) {
        this.forwordTime = forwordTime;
    }

    /**
     * 获取可以附带一些信息，告知原厂
     *
     * @return comment - 可以附带一些信息，告知原厂
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置可以附带一些信息，告知原厂
     *
     * @param comment 可以附带一些信息，告知原厂
     */
    public void setComment(String comment) {
        this.comment = comment;
    }
}