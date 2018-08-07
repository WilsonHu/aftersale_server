package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.user.User;
import com.eservice.api.service.impl.UserServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @PostMapping("/addStaff")
    public Result addStaff(@RequestBody @NotNull User user) {
        if(userService.selectByAccount(user.getAccount()) != null) {
            return ResultGenerator.genFailResult("用户名已存在！");
        }
        user.setPassword("password");
        user.setCreateTime(new Date());
        userService.save(user);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        userService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull User user) {

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
                              Integer currentUserAgent, String account, String name, Integer roleId, Boolean isAgent, Integer agent, String valid, Integer userType) {
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
                return ResultGenerator.genFailResult("账号/密码/unionid 不正确！");
            }else {
                return ResultGenerator.genSuccessResult(user);
            }
        }
    }

    /**
     * 根据参数返回该类型的用户
     * 比如，
     * 5:返回属性为客户的user
     * 6:返回属性为客户的联系人的user
     */
    @PostMapping("/getUsersByType")
    public Result getUsersByType(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                               @RequestParam( ) String type) {
        PageHelper.startPage(page, size);
        List<User> list = userService.getUsersByType(type);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
