package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_customer_feedback.InstallCustomerFeedback;
import com.eservice.api.service.InstallCustomerFeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
    private InstallCustomerFeedbackService installCustomerFeedbackService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull InstallCustomerFeedback installCustomerFeedback) {
        installCustomerFeedbackService.save(installCustomerFeedback);
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
