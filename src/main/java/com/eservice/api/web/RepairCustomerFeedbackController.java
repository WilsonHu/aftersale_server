package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.repair_customer_feedback.RepairCustomerFeedback;
import com.eservice.api.service.RepairCustomerFeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/repair/customer/feedback")
public class RepairCustomerFeedbackController {
    @Resource
    private RepairCustomerFeedbackService repairCustomerFeedbackService;

    @PostMapping("/add")
    public Result add(RepairCustomerFeedback repairCustomerFeedback) {
        repairCustomerFeedbackService.save(repairCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairCustomerFeedbackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(RepairCustomerFeedback repairCustomerFeedback) {
        repairCustomerFeedbackService.update(repairCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
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
