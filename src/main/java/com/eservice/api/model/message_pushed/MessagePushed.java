package com.eservice.api.model.message_pushed;

import java.util.Date;
import javax.persistence.*;

@Table(name = "message_pushed")
public class MessagePushed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 推送的时间
     */
    @Column(name = "pushed_time")
    private Date pushedTime;

    /**
     * 安装/保养/维修的记录ID，空则表示只是让客户自己保养。
     */
    @Column(name = "record_id")
    private Integer recordId;

    /**
     * 1: 安装派单时(给安装工和客户)； 2：保养派单（给保养工和客户）； 3：维修派单（给维修工和客户）； 4：保养提醒 （仅仅客户，没有上门保养） 
     */
    private String type;

    /**
     * 推送的消息内容，由后台组装完成。
     */
    @Column(name = "message_content")
    private String messageContent;

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
     * 获取推送的时间
     *
     * @return pushed_time - 推送的时间
     */
    public Date getPushedTime() {
        return pushedTime;
    }

    /**
     * 设置推送的时间
     *
     * @param pushedTime 推送的时间
     */
    public void setPushedTime(Date pushedTime) {
        this.pushedTime = pushedTime;
    }

    /**
     * 获取安装/保养/维修的记录ID，空则表示只是让客户自己保养。
     *
     * @return record_id - 安装/保养/维修的记录ID，空则表示只是让客户自己保养。
     */
    public Integer getRecordId() {
        return recordId;
    }

    /**
     * 设置安装/保养/维修的记录ID，空则表示只是让客户自己保养。
     *
     * @param recordId 安装/保养/维修的记录ID，空则表示只是让客户自己保养。
     */
    public void setRecordId(Integer recordId) {
        this.recordId = recordId;
    }

    /**
     * 获取1: 安装派单时(给安装工和客户)； 2：保养派单（给保养工和客户）； 3：维修派单（给维修工和客户）； 4：保养提醒 （仅仅客户，没有上门保养） 
     *
     * @return type - 1: 安装派单时(给安装工和客户)； 2：保养派单（给保养工和客户）； 3：维修派单（给维修工和客户）； 4：保养提醒 （仅仅客户，没有上门保养） 
     */
    public String getType() {
        return type;
    }

    /**
     * 设置1: 安装派单时(给安装工和客户)； 2：保养派单（给保养工和客户）； 3：维修派单（给维修工和客户）； 4：保养提醒 （仅仅客户，没有上门保养） 
     *
     * @param type 1: 安装派单时(给安装工和客户)； 2：保养派单（给保养工和客户）； 3：维修派单（给维修工和客户）； 4：保养提醒 （仅仅客户，没有上门保养） 
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 获取推送的消息内容，由后台组装完成。
     *
     * @return message_content - 推送的消息内容，由后台组装完成。
     */
    public String getMessageContent() {
        return messageContent;
    }

    /**
     * 设置推送的消息内容，由后台组装完成。
     *
     * @param messageContent 推送的消息内容，由后台组装完成。
     */
    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}