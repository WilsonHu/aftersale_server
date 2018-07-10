package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.maintain_abnormal_record.MaintainAbnormalRecord;
import com.eservice.api.service.MaintainAbnormalRecordService;
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
@RequestMapping("/maintain/abnormal/record")
public class MaintainAbnormalRecordController {
    @Resource
    private MaintainAbnormalRecordService maintainAbnormalRecordService;

    @PostMapping("/add")
    public Result add(MaintainAbnormalRecord maintainAbnormalRecord) {
        maintainAbnormalRecordService.save(maintainAbnormalRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainAbnormalRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(MaintainAbnormalRecord maintainAbnormalRecord) {
        maintainAbnormalRecordService.update(maintainAbnormalRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        MaintainAbnormalRecord maintainAbnormalRecord = maintainAbnormalRecordService.findById(id);
        return ResultGenerator.genSuccessResult(maintainAbnormalRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MaintainAbnormalRecord> list = maintainAbnormalRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
