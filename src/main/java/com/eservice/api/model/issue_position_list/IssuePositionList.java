package com.eservice.api.model.issue_position_list;

import javax.persistence.*;

@Table(name = "issue_position_list")
public class IssuePositionList {
    /**
     * 故障部位列表，由信胜提供
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 故障部位名称
     */
    private String name;

    /**
     * 获取故障部位列表，由信胜提供
     *
     * @return id - 故障部位列表，由信胜提供
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置故障部位列表，由信胜提供
     *
     * @param id 故障部位列表，由信胜提供
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取故障部位名称
     *
     * @return name - 故障部位名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置故障部位名称
     *
     * @param name 故障部位名称
     */
    public void setName(String name) {
        this.name = name;
    }
}