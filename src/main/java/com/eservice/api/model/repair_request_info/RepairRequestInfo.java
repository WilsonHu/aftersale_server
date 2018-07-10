package com.eservice.api.model.repair_request_info;

import javax.persistence.*;

@Table(name = "repair_request_info")
public class RepairRequestInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 老机器报修时填写的铭牌号，非老机器自动填写
     */
    private String nameplate;

    /**
     * 老机器报修时拍的铭牌的图片的保存路径，非老机器为空。
     */
    @Column(name = "nameplate_picture")
    private String nameplatePicture;

    /**
     * 报修的语音文件的路径
     */
    private String voice;

    /**
     * 报修的标题
     */
    @Column(name = "repair_title")
    private String repairTitle;

    /**
     * 报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片
     */
    private String pictures;

    /**
     * 联系人
     */
    private Integer contacter;

    /**
     * 报修内容
     */
    private String content;

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
     * 获取老机器报修时填写的铭牌号，非老机器自动填写
     *
     * @return nameplate - 老机器报修时填写的铭牌号，非老机器自动填写
     */
    public String getNameplate() {
        return nameplate;
    }

    /**
     * 设置老机器报修时填写的铭牌号，非老机器自动填写
     *
     * @param nameplate 老机器报修时填写的铭牌号，非老机器自动填写
     */
    public void setNameplate(String nameplate) {
        this.nameplate = nameplate;
    }

    /**
     * 获取老机器报修时拍的铭牌的图片的保存路径，非老机器为空。
     *
     * @return nameplate_picture - 老机器报修时拍的铭牌的图片的保存路径，非老机器为空。
     */
    public String getNameplatePicture() {
        return nameplatePicture;
    }

    /**
     * 设置老机器报修时拍的铭牌的图片的保存路径，非老机器为空。
     *
     * @param nameplatePicture 老机器报修时拍的铭牌的图片的保存路径，非老机器为空。
     */
    public void setNameplatePicture(String nameplatePicture) {
        this.nameplatePicture = nameplatePicture;
    }

    /**
     * 获取报修的语音文件的路径
     *
     * @return voice - 报修的语音文件的路径
     */
    public String getVoice() {
        return voice;
    }

    /**
     * 设置报修的语音文件的路径
     *
     * @param voice 报修的语音文件的路径
     */
    public void setVoice(String voice) {
        this.voice = voice;
    }

    /**
     * 获取报修的标题
     *
     * @return repair_title - 报修的标题
     */
    public String getRepairTitle() {
        return repairTitle;
    }

    /**
     * 设置报修的标题
     *
     * @param repairTitle 报修的标题
     */
    public void setRepairTitle(String repairTitle) {
        this.repairTitle = repairTitle;
    }

    /**
     * 获取报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片
     *
     * @return pictures - 报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片
     */
    public String getPictures() {
        return pictures;
    }

    /**
     * 设置报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片
     *
     * @param pictures 报修图片的路径，--客户报修时上传，可以用于经验库里“解决前”的问题照片
     */
    public void setPictures(String pictures) {
        this.pictures = pictures;
    }

    /**
     * 获取联系人
     *
     * @return contacter - 联系人
     */
    public Integer getContacter() {
        return contacter;
    }

    /**
     * 设置联系人
     *
     * @param contacter 联系人
     */
    public void setContacter(Integer contacter) {
        this.contacter = contacter;
    }

    /**
     * 获取报修内容
     *
     * @return content - 报修内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置报修内容
     *
     * @param content 报修内容
     */
    public void setContent(String content) {
        this.content = content;
    }
}