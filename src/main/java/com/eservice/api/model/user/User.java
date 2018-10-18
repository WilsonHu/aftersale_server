package com.eservice.api.model.user;

import java.util.Date;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String account;

    private String name;

    /**
     * 微信unionId，在没授权前是空的。字段名称有下划线时 findBy(fieldName,xxx)会失败。
     */
    @Column(name = "wechatUnionId")
    private String wechatUnionId;

    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 代理商,如果是空表示是信胜自己的员工
     */
    private Integer agent;

    private String password;

    /**
     * 是否在职 ， “1”:在职 “0”:离职
     */
    private String valid;

    /**
     * 电话
     */
    private String phone;

    @Column(name = "create_time")
    private Date createTime;

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
     * @return role_id
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取代理商,如果是空表示是信胜自己的员工
     *
     * @return agent - 代理商,如果是空表示是信胜自己的员工
     */
    public Integer getAgent(){
        return agent;
    }

//    public String getPassword() {
//        return password;
//    }

    /**
     * 设置代理商,如果是空表示是信胜自己的员工
     *
     * @param agent 代理商,如果是空表示是信胜自己的员工
     */
    public void setAgent(Integer agent) {
        this.agent = agent;
    }

    /**
     * @return password
     */
//    public String getPassword() {
//        return password;
//    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取是否在职 ， “1”:在职 “0”:离职
     *
     * @return valid - 是否在职 ， “1”:在职 “0”:离职
     */
    public String getValid() {
        return valid;
    }

    /**
     * 设置是否在职 ， “1”:在职 “0”:离职
     *
     * @param valid 是否在职 ， “1”:在职 “0”:离职
     */
    public void setValid(String valid) {
        this.valid = valid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
//        List<SysRole> roles = this.getRoles();
//        for (SysRole role : roles) {
//            auths.add(new SimpleGrantedAuthority(role.getName()));
//        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.account;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getPhone() {
        return phone;
    }

    /**
     * 设置电话
     *
     * @param phone 电话
     */
    public void setPhone(String phone) {
        this.phone = phone;
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
     * 该用户所属公司名称，对于没有公司的用户，可以直接用用户自己名称。
     */
    private String customerCompany;

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}