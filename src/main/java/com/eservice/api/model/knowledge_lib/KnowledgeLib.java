package com.eservice.api.model.knowledge_lib;

import java.util.Date;
import javax.persistence.*;

@Table(name = "knowledge_lib")
public class KnowledgeLib {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 文章作者
     */
    private String author;

    /**
     * 阅读次数
     */
    @Column(name = "read_times")
    private String readTimes;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String html;

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
     * 获取文章作者
     *
     * @return author - 文章作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置文章作者
     *
     * @param author 文章作者
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取阅读次数
     *
     * @return read_times - 阅读次数
     */
    public String getReadTimes() {
        return readTimes;
    }

    /**
     * 设置阅读次数
     *
     * @param readTimes 阅读次数
     */
    public void setReadTimes(String readTimes) {
        this.readTimes = readTimes;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return html
     */
    public String getHtml() {
        return html;
    }

    /**
     * @param html
     */
    public void setHtml(String html) {
        this.html = html;
    }
}