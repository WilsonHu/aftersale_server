package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.maintain_lib.MaintainLib;
import com.eservice.api.service.impl.MaintainLibServiceImpl;
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
@RequestMapping("/maintain/lib")
public class MaintainLibController {
    @Resource
    private MaintainLibServiceImpl maintainLibService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull MaintainLib maintainLib) {
        if (maintainLib.getMaintainType() == 0) {//大项
            List<MaintainLib> list = maintainLibService.selectLibList(maintainLib.getMaintainType().toString(), maintainLib.getMaintainLibName());
            if (list.size() > 0) {
                return ResultGenerator.genFailResult("不能添加重复的保养项!");
            }
        }
        maintainLibService.save(maintainLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainLibService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull MaintainLib maintainLib) {
        if (maintainLib.getMaintainType() == 0) {//大项
            List<MaintainLib> list = maintainLibService.selectLibList(maintainLib.getMaintainType().toString(), maintainLib.getMaintainLibName());
            if (list.size() > 0) {
                return ResultGenerator.genFailResult("不能修改为重复的保养项!");
            }
        }
        maintainLibService.update(maintainLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        MaintainLib maintainLib = maintainLibService.findById(id);
        return ResultGenerator.genSuccessResult(maintainLib);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MaintainLib> list = maintainLibService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectLibList")
    public Result selectLibList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestParam String maintainType, @RequestParam String maintainLibName) {
        PageHelper.startPage(page, size);
        List<MaintainLib> list = maintainLibService.selectLibList(maintainType, maintainLibName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/deleteByName")
    public Result deleteByName(@RequestParam String maintainLibName) {
        int deleteCount = maintainLibService.deleteByName(maintainLibName);
        return ResultGenerator.genSuccessResult(deleteCount);
    }
}
