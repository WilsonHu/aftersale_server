package com.eservice.api.web;

import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineBaseRecordInfo;
import com.eservice.api.model.machine.MachineInfo;
import com.eservice.api.service.common.CommonUtils;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.InstallRecordServiceImpl;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Class Description: xxx
 *
 * @author Wilson Hu
 * @date 2018/08/04.
 */
@RestController
@RequestMapping("/machine")
public class MachineController {
    @Resource
    private MachineServiceImpl machineService;

    @Resource
    private InstallRecordServiceImpl installRecordService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull Machine machine) {
        Machine dbMachine = machineService.findBy("nameplate", machine.getNameplate());
        if (dbMachine != null) {
            return ResultGenerator.genFailResult(machine.getNameplate() + "机器已存在，不能重复添加！");
        }
        machineService.save(machine);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/addList")
    @Transactional(rollbackFor = Exception.class)
    public Result addList(String machineList) {
        try {
            List<Machine> modelList = JSONObject.parseArray(machineList, Machine.class);
            if (modelList == null || modelList.size() == 0) {
                return ResultGenerator.genFailResult("提交列表没有可保存的数据，请检查！");
            }
            for (Machine item : modelList) {
                try {
                    Machine machine = machineService.findBy("nameplate", item.getNameplate());
                    if (machine != null) {
                        int index = modelList.indexOf(item) + 1;
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ResultGenerator.genFailResult("第" + index + "个机器已存在，不能重复绑定！");
                    }
                    item.setStatus(com.eservice.api.service.common.Constant.MACHINE_STATUS_BOUND_TO_CUSTOMER);//machine status
                    machineService.save(item);

                    InstallRecord ir = new InstallRecord();
                    ir.setInstallRecordNum(CommonUtils.generateSequenceNo());
                    ir.setMachineNameplate(item.getNameplate());
                    ir.setInstallStatus(Constant.INSTALL_STATUS_NOT_ASSIGN);//install status
                    ir.setCreateTime(new Date());
                    installRecordService.save(ir);
                } catch (Exception ex) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
                }
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        machineService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull Machine machine) {
        List<MachineInfo> machineList = machineService.getSaledMachineInfoList(machine.getNameplate(), null,
                null, null, null, null, null, null, null, false);
        for (Machine info : machineList) {
            if (info.getId() != machine.getId()) {
                return ResultGenerator.genFailResult(machine.getNameplate() + "机器已存在,不能修改！");
            }
        }
        machineService.update(machine);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
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
     *根据铭牌号返回机器，如果是老机器则提示老机器
     */
    @PostMapping("/selectByNameplate")
    public Result selectByNameplate(@RequestParam String nameplate) {
        Machine machine = machineService.findBy("nameplate", nameplate);
        if( machine == null){
            return ResultGenerator.genFailResult("can not find machine by the nameplate " + nameplate);
        }
        if( machine.getIsOldMachine().equals(Constant.MACHINE_TYPE_OLD) ) {
            return ResultGenerator.genSuccessResult(nameplate + "is old machine");
        } else {
            return ResultGenerator.genSuccessResult(machine);
        }
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
     * 根据条件查询机器及其相关记录信息。
     *
     * @param page
     * @param size
     * @param nameplate
     * @param orderNum
     * @param machineType
     * @param agent
     * @param status
     * @param customerName              下面都是查询实际发生的时间/日期
     * @param query_start_time_install
     * @param query_finish_time_install
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
                                          String machineWhereFrom,
//                                          String installChargePerson,
                                          boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<MachineInfo> list = machineService.getSaledMachineInfoList(nameplate,
                orderNum,
                machineType,
                agent,
                status,
                customerName,
                query_start_time_install,
                query_finish_time_install,
                machineWhereFrom,
//                installChargePerson,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据铭牌号返回机器的
     * 基本信息，
     * 安装信息：时间，人员，状态
     * 保养信息：时间，人员，状态
     * 维修信息：时间，人员，状态
     */
    @PostMapping("/selectBaseRecordByNameplate")
    public Result selectBaseRecordByNameplate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                              @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<MachineBaseRecordInfo> list = machineService.selectBaseRecordByNameplate(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 客户端的要获取的待处理机器列表
     * 获取该客户的安装、保养、维修、配件寄回等任务信息（包括机器信息+任务信息）
     */
    @PostMapping("/selectBaseRecordByUser")
    public Result selectBaseRecordByUser(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<MachineBaseRecordInfo> list = machineService.selectBaseRecordByUser(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
