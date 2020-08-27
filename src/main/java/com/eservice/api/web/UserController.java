package com.eservice.api.web;

import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.user.StaffInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.model.user.UserInfo;
import com.eservice.api.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserServiceImpl userService;
    private Logger logger = Logger.getLogger(UserController.class);

    @PostMapping("/addStaff")
    public Result addStaff(@RequestBody @NotNull User user) {
        if(userService.selectByAccount(user.getAccount()) != null) {
            return ResultGenerator.genFailResult("账号已存在！");
        }
        user.setPassword("sinsim");
        user.setValid("1");
        user.setCreateTime(new Date());
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 更新用户密码
     */
    @PostMapping("/updatePassword")
    public Result updatePassword(@RequestParam String account, @RequestParam String oldPassword,@RequestParam String newPassword) {

        User user  = userService.requestLogin(account, oldPassword,null);
        if(user == null) {

            logger.error("账号(" + account + ") 密码(" + oldPassword + ")不正确！" );
            return ResultGenerator.genFailResult("账号/密码 不正确！");
        }else {
            user.setPassword(newPassword);
            userService.update(user);
            return ResultGenerator.genSuccessResult("密码更新成功");
        }
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull User user) {
//        if(userService.selectByAccount(user.getAccount()) != null) {
//            return ResultGenerator.genFailResult("账号已存在！");
//        } 需要前端把账号设为不可改。
        if(user.getAgent() == null) {
            user.setAgent(0);
        }

        userService.update(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        User user = userService.findById(id);
        return ResultGenerator.genSuccessResult(user);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<User> list = userService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectUsers")
    public Result selectUsers(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                              Integer currentUserAgent,
                              String account,
                              String name,
                              Integer roleId,
                              Boolean isAgent,
                              Integer agent,
                              String valid,
                              Integer userType) {
        PageHelper.startPage(page, size);
        List<User> list = userService.selectUsers(currentUserAgent,account, name, roleId, isAgent, agent, valid, userType);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping("/requestLogin")
    public Result requestLogin(@RequestParam String account, @RequestParam String password,
                               @RequestParam String unionid) {
        boolean result = true;

        if(account == null || "".equals(account)) {
            return ResultGenerator.genFailResult("账号不能为空！");
        } else if(password == null || "".equals(password)) {
            return ResultGenerator.genFailResult("密码不能为空！");
        }else {
            User user  = userService.requestLogin(account, password,unionid);
            if(user == null) {
                logger.info("账号/密码/unionid 不正确！ " + account + "/" +password + "/" + unionid);
                return ResultGenerator.genFailResult("账号/密码/unionid 不正确！");
            }else {
                return ResultGenerator.genSuccessResult(user);
            }
        }
    }

    /**
     * 根据参数返回该类型的用户
     * 不带参数，则不按该参数过滤
     */
    @PostMapping("/getUsersByType")
    public Result getUsersByType(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                 String roleId,
                                 String agentId,
                                 String customerCompany) {
        PageHelper.startPage(page, size);
        List<UserInfo> list = userService.getUsersByType(roleId, agentId, customerCompany);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据参数返回该类型的用户
     * 不带参数，则不按该参数过滤
     */
    @PostMapping("/getStaffByParam")
    public Result getStaffByParam(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                 String agentId) {
        PageHelper.startPage(page, size);
        List<StaffInfo> list = userService.getStaffByParam(agentId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
