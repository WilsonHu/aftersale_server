package com.eservice.api.model.contacts;

import javax.persistence.*;

public class Contacts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;

    private String name;

    /**
     * 微信unionId，在没授权前是空的。
     */
    @Column(name = "wechat_union_id")
    private String wechatUnionId;

    private String password;

    private String phone;

    /**
     * 该联系人所属的客户,一个客户可以有多个联系人
     */
    private Integer customer;

    private String address;

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
     * @return account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取微信unionId，在没授权前是空的。
     *
     * @return wechat_union_id - 微信unionId，在没授权前是空的。
     */
    public String getWechatUnionId() {
        return wechatUnionId;
    }

    /**
     * 设置微信unionId，在没授权前是空的。
     *
     * @param wechatUnionId 微信unionId，在没授权前是空的。
     */
    public void setWechatUnionId(String wechatUnionId) {
        this.wechatUnionId = wechatUnionId;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取该联系人所属的客户,一个客户可以有多个联系人
     *
     * @return customer - 该联系人所属的客户,一个客户可以有多个联系人
     */
    public Integer getCustomer() {
        return customer;
    }

    /**
     * 设置该联系人所属的客户,一个客户可以有多个联系人
     *
     * @param customer 该联系人所属的客户,一个客户可以有多个联系人
     */
    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    /**
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
}