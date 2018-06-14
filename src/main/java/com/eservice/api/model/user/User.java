package com.eservice.api.model.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class User implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 账号
     */
    private String account;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 角色ID
     */
    @Column(name = "role_id")
    private Integer roleId;

    /**
     * 密码
     */
    private String password;

    /**
     * 所在安装组group ID，可以为空(其他部门人员)
     */
    @Column(name = "group_id")
    private Integer groupId;

    /**
     * 销售部信息（区分外贸、内贸一部、内贸二部）
     */
    @Column(name = "market_group_name")
    private String marketGroupName;

    /**
     * 员工是否在职，“1”==>在职, “0”==>离职
     */
    private Byte valid;

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
     * 获取账号
     *
     * @return account - 账号
     */
    public String getAccount() {
        return account;
    }

    /**
     * 设置账号
     *
     * @param account 账号
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * 获取用户姓名
     *
     * @return name - 用户姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户姓名
     *
     * @param name 用户姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取角色ID
     *
     * @return role_id - 角色ID
     */
    public Integer getRoleId() {
        return roleId;
    }

    /**
     * 设置角色ID
     *
     * @param roleId 角色ID
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
//    public String getPassword() {
//        return password;
//    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取所在安装组group ID，可以为空(其他部门人员)
     *
     * @return group_id - 所在安装组group ID，可以为空(其他部门人员)
     */
    public Integer getGroupId() {
        return groupId;
    }

    /**
     * 设置所在安装组group ID，可以为空(其他部门人员)
     *
     * @param groupId 所在安装组group ID，可以为空(其他部门人员)
     */
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getMarketGroupName() {
        return marketGroupName;
    }

    public void setMarketGroupName(String marketGroupName) {
        this.marketGroupName = marketGroupName;
    }

    /**
     * 获取员工是否在职，“1”==>在职, “0”==>离职
     *
     * @return valid - 员工是否在职，“1”==>在职, “0”==>离职
     */
    public Byte getValid() {
        return valid;
    }

    /**
     * 设置员工是否在职，“1”==>在职, “0”==>离职
     *
     * @param valid 员工是否在职，“1”==>在职, “0”==>离职
     */
    public void setValid(Byte valid) {
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
}