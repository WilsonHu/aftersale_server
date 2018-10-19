package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_customer_feedback.InstallCustomerFeedback;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.service.InstallRecordService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.InstallCustomerFeedbackServiceImpl;
import com.eservice.api.service.impl.MachineServiceImpl;
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
@RequestMapping("/install/customer/feedback")
public class InstallCustomerFeedbackController {
    @Resource
    private InstallCustomerFeedbackServiceImpl installCustomerFeedbackService;
    @Resource
    private InstallRecordService installRecordService;
    @Resource
    private MachineServiceImpl machineService;

    /**
     * 上传安装的用户评价
     * 更新对应的安装记录，更新对应的机器状态
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@RequestParam String installCustomerFeedback1,
                      @RequestParam String installRecordId){
        try {
            InstallCustomerFeedback installCustomerFeedback = JSON.parseObject(installCustomerFeedback1, InstallCustomerFeedback.class);
            installCustomerFeedbackService.saveAndGetID(installCustomerFeedback);

            /**
             * 更新对应的安装记录
             */
            InstallRecord installRecord = installRecordService.findById(Integer.parseInt(installRecordId));
            if (installRecord == null) {
                return ResultGenerator.genFailResult("获取安装记录失败");
            }
            installRecord.setUpdateTime(new Date());
            installRecord.setInstallStatus(Constant.INSTALL_STATUS_CONFIRMED);
            installRecord.setCustomerFeedback(installCustomerFeedback.getId());
            installRecord.setInstallActualTime(new Date());
            installRecordService.update(installRecord);

            /**
             * 更新对应的机器状态
             */
            Machine machine = machineService.findBy("nameplate", installRecord.getMachineNameplate());
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
        installCustomerFeedbackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull InstallCustomerFeedback installCustomerFeedback) {
        installCustomerFeedbackService.update(installCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        InstallCustomerFeedback installCustomerFeedback = installCustomerFeedbackService.findById(id);
        return ResultGenerator.genSuccessResult(installCustomerFeedback);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InstallCustomerFeedback> list = installCustomerFeedbackService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
