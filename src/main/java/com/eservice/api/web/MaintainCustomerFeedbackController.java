package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.maintain_customer_feedback.MaintainCustomerFeedback;
import com.eservice.api.service.MaintainCustomerFeedbackService;
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
@RequestMapping("/maintain/customer/feedback")
public class MaintainCustomerFeedbackController {
    @Resource
    private MaintainCustomerFeedbackService maintainCustomerFeedbackService;

    @PostMapping("/add")
    public Result add(MaintainCustomerFeedback maintainCustomerFeedback) {
        maintainCustomerFeedbackService.save(maintainCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainCustomerFeedbackService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(MaintainCustomerFeedback maintainCustomerFeedback) {
        maintainCustomerFeedbackService.update(maintainCustomerFeedback);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
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
