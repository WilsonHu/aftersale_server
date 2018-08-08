package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.maintain_customer_feedback.MaintainCustomerFeedback;
import com.eservice.api.model.maintain_record.MaintainRecord;
import com.eservice.api.service.MaintainRecordService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.eservice.api.service.impl.MaintainCustomerFeedbackServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
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
@RequestMapping("/maintain/customer/feedback")
public class MaintainCustomerFeedbackController {
    @Resource
    private MaintainCustomerFeedbackServiceImpl maintainCustomerFeedbackService;
    @Resource
    private MaintainRecordService maintainRecordService;
    @Resource
    private MachineServiceImpl machineService;

    /**
     * 上传保养的用户评价
     * 更新对应的保养记录，更新对应的机器状态
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@RequestParam String maintainCustomerFeedback1,
                      @RequestParam String maintainRecordId){
        try {
            MaintainCustomerFeedback maintainCustomerFeedback = JSON.parseObject(maintainCustomerFeedback1, MaintainCustomerFeedback.class);
            maintainCustomerFeedbackService.saveAndGetID(maintainCustomerFeedback);

            /**
             * 更新对应的保养记录
             */
            MaintainRecord maintainRecord = maintainRecordService.findById(Integer.parseInt(maintainRecordId));
            if (maintainRecord == null) {
                return ResultGenerator.genFailResult("获取保养记录失败");
            }
            maintainRecord.setUpdateTime(new Date());
            maintainRecord.setMaintainStatus(Constant.MAINTAIN_STATUS_FINISHED);
            maintainRecord.setCustomerFeedback(maintainCustomerFeedback.getId());
            maintainRecordService.update(maintainRecord);

            /**
             * 更新对应的机器状态
             */
            Machine machine = machineService.findBy("nameplate", maintainRecord.getMachineNameplate());
            machine.setStatus(Constant.MACHINE_STATUS_IN_NORMAL);
            machineService.update(machine);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据更新出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainCustomerFeedbackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull MaintainCustomerFeedback maintainCustomerFeedback) {
        maintainCustomerFeedbackService.update(maintainCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        MaintainCustomerFeedback maintainCustomerFeedback = maintainCustomerFeedbackService.findById(id);
        return ResultGenerator.genSuccessResult(maintainCustomerFeedback);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MaintainCustomerFeedback> list = maintainCustomerFeedbackService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
