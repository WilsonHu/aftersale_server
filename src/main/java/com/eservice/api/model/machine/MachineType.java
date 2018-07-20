package com.eservice.api.model.machine;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "machine_type")
public class MachineType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 机器类型
     */
    private String name;

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
     * 获取机器类型
     *
     * @return name - 机器类型
     */
    public String getName() {
        return name;
    }

    /**
     * 设置机器类型
     *
     * @param name 机器类型
     */
    public void setName(String name) {
        this.name = name;
    }
}