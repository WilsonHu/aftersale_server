package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.repair_customer_feedback.RepairCustomerFeedback;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.service.RepairCustomerFeedbackService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.eservice.api.service.impl.RepairCustomerFeedbackServiceImpl;
import com.eservice.api.service.impl.RepairRecordServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/repair/customer/feedback")
public class RepairCustomerFeedbackController {
    @Resource
    private RepairCustomerFeedbackServiceImpl repairCustomerFeedbackService;

    @Resource
    private MachineServiceImpl machineService;

    @Resource
    private RepairRecordServiceImpl repairRecordService;

    /**
     * 上传维修的用户评价
     * 更新对应的维修记录，更新对应的机器状态
     */
    @PostMapping("/add")
    public Result add(String repairCustomerFeedback,
                      String repairRecordId) {
        RepairCustomerFeedback repairCustomerFeedback1 = JSON.parseObject(repairCustomerFeedback,RepairCustomerFeedback.class);
        repairCustomerFeedbackService.saveAndGetID(repairCustomerFeedback1);

        /**
         * 更新对应的维修记录
         */
        RepairRecord repairRecord = repairRecordService.findById(Integer.parseInt(repairRecordId));
        if(repairRecord == null){
            return ResultGenerator.genFailResult("获取维修记录失败");
        }
        repairRecord.setUpdateTime(new Date());
        repairRecord.setStatus(Constant.REPAIR_STATUS_REPAIR_CUSTOMER_CONFIRMED);
        repairRecord.setCustomerFeedback(repairCustomerFeedback1.getId());
        repairRecordService.update(repairRecord);
        
        /**
         * 更新对应的机器状态
         */
        Machine machine = machineService.findBy("nameplate",repairRecord.getMachineNameplate());
        machine.setStatus(Constant.MACHINE_STATUS_IN_NORMAL);
        machineService.update(machine);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairCustomerFeedbackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull RepairCustomerFeedback repairCustomerFeedback) {
        repairCustomerFeedbackService.update(repairCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        RepairCustomerFeedback repairCustomerFeedback = repairCustomerFeedbackService.findById(id);
        return ResultGenerator.genSuccessResult(repairCustomerFeedback);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RepairCustomerFeedback> list = repairCustomerFeedbackService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
