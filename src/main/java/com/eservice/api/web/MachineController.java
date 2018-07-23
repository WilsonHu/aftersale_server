package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineInfo;
import com.eservice.api.service.MachineService;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Class Description: xxx
 *
 * @author Wilson Hu
 * @date 2018/07/10.
 */
@RestController
@RequestMapping("/machine")
public class MachineController {
    @Resource
    private MachineServiceImpl machineService;

    @PostMapping("/add")
    public Result add(String machine) {
        Machine model = JSON.parseObject(machine, Machine.class);
        machineService.save(model);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/addList")
    public Result addList(String machineList) {
        List<Machine> modelList = JSONObject.parseArray(machineList, Machine.class);
        if(modelList==null||modelList.size()==0)
        {
            return ResultGenerator.genFailResult("没有可保存的数据！");
        }
        for (Machine item : modelList) {
            try {
                machineService.save(item);
            } catch (Exception ex) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        machineService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String machine) {
        Machine model = JSON.parseObject(machine, Machine.class);
        machineService.update(model);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        Machine machine = machineService.findById(id);
        return ResultGenerator.genSuccessResult(machine);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Machine> list = machineService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /*
     *根据铭牌号返回机器
     */
    @PostMapping("/selectByNameplate")
    public Result selectByNameplate(@RequestParam String nameplate) {
        Machine machine = machineService.findBy("nameplate", nameplate);
        return ResultGenerator.genSuccessResult(machine);
    }

    /*
     * 根据账号返回该账号所属客户的机器列表
     */
    @PostMapping("/selectByAccount")
    public Result selectByCustomer(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                   @RequestParam String account) {
        PageHelper.startPage(page, size);
        List<Machine> machineList = machineService.selectByAccount(account);
        PageInfo pageInfo = new PageInfo(machineList);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据条件查询机器及其相关记录信息,时间查询待完成。
     *
     * @param page
     * @param size
     * @param nameplate
     * @param orderNum
     * @param machineType
     * @param agent
     * @param status
     * @param customerName
     * @param query_start_time_install
     * @param query_finish_time_install
     * @param query_start_time_maintain
     * @param query_finish_time_maintain
     * @param query_start_time_repair
     * @param query_finish_time_repair
     * @return
     */
    @PostMapping("/getSaledMachineInfoList")
    public Result getSaledMachineInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                          String nameplate,
                                          String orderNum,
                                          String machineType,
                                          String agent,
                                          String status,
                                          String customerName,
                                          String query_start_time_install,
                                          String query_finish_time_install,
                                          String query_start_time_maintain,
                                          String query_finish_time_maintain,
                                          String query_start_time_repair,
                                          String query_finish_time_repair) {
        PageHelper.startPage(page, size);
        List<MachineInfo> list = machineService.getSaledMachineInfoList(nameplate,
                orderNum,
                machineType,
                agent,
                status,
                customerName,
                query_start_time_install,
                query_finish_time_install,
                query_start_time_maintain,
                query_finish_time_maintain,
                query_start_time_repair,
                query_finish_time_repair);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
