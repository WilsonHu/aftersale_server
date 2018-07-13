package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.service.InstallRecordService;
import com.eservice.api.service.impl.InstallRecordServiceImpl;
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
@RequestMapping("/install/record")
public class InstallRecordController {
    @Resource
    private InstallRecordServiceImpl installRecordService;

    @PostMapping("/add")
    public Result add(InstallRecord installRecord) {
        installRecordService.save(installRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        installRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(InstallRecord installRecord) {
        installRecordService.update(installRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        InstallRecord installRecord = installRecordService.findById(id);
        return ResultGenerator.genSuccessResult(installRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InstallRecord> list = installRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 等待处理的安装任务
     * @param page
     * @param size
     * @return
     */
    @PostMapping("/selectWaitForProcess")
    public Result selectWaitForProcess(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InstallRecord> list = installRecordService.selectWaitForProcess();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectByNameplate")
    public Result selectByNameplate(@RequestParam String nameplate) {
        InstallRecord installRecord = installRecordService.selectByNameplate(nameplate);
        return ResultGenerator.genSuccessResult(installRecord);
    }
}
