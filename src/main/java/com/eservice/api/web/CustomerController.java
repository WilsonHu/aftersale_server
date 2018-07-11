package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.customer.Customer;
import com.eservice.api.model.user.User;
import com.eservice.api.service.CustomerService;
import com.eservice.api.service.impl.CustomerServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Resource
    private CustomerServiceImpl customerService;

    @PostMapping("/add")
    public Result add(Customer customer) {
        customerService.save(customer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        customerService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(Customer customer) {
        customerService.update(customer);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Customer customer = customerService.findById(id);
        return ResultGenerator.genSuccessResult(customer);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Customer> list = customerService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /*
    *第一次登陆时未授权，account、password、unionid三个都需要。
    * 授权以后，不需要unionid了。
    */
    @PostMapping("/requestLogin")
    public Result requestLogin(@RequestParam String account, @RequestParam String password,
                              String unionid) {
        boolean result = true;

        if(account == null || "".equals(account)) {
            return ResultGenerator.genFailResult("账号不能为空！");
        } else if(password == null || "".equals(password)) {
            return ResultGenerator.genFailResult("密码不能为空！");
        }else {
            Customer customer  = customerService.requestLogin(account, password,unionid);
            if(customer == null) {
                return ResultGenerator.genFailResult("账号/密码/unionid 不正确！");
            }else {
                return ResultGenerator.genSuccessResult(customer);
            }
        }
    }
}
