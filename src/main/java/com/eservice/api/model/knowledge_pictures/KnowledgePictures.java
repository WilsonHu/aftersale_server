package com.eservice.api.model.knowledge_pictures;

import javax.persistence.*;

@Table(name = "knowledge_pictures")
public class KnowledgePictures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 知识库图片保存路径, 比如 "/opt/aaaa/bbb.jpg"
     */
    private String path;

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
     * 获取知识库图片保存路径, 比如 "/opt/aaaa/bbb.jpg"
     *
     * @return path - 知识库图片保存路径, 比如 "/opt/aaaa/bbb.jpg"
     */
    public String getPath() {
        return path;
    }

    /**
     * 设置知识库图片保存路径, 比如 "/opt/aaaa/bbb.jpg"
     *
     * @param path 知识库图片保存路径, 比如 "/opt/aaaa/bbb.jpg"
     */
    public void setPath(String path) {
        this.path = path;
    }
}