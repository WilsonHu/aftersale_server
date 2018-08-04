package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_lib.InstallLib;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.InstallLibServiceImpl;
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
@RequestMapping("/install/lib")
public class InstallLibController {
    @Resource
    private InstallLibServiceImpl installLibService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull InstallLib installLib) {
        if (installLib.getIsBaseLib().equals(Constant.IsBaseLibEnum.BASE_LIB_ENUM.getValue().toString())) {
            List<InstallLib> list = installLibService.selectLibList(Constant.IsBaseLibEnum.BASE_LIB_ENUM.getValue().toString(), installLib.getInstallLibName());
            if (list.size() > 0) {
                return ResultGenerator.genFailResult("不能添加重复的调试项!");
            }
        }
        installLibService.save(installLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        installLibService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/deleteByName")
    public Result deleteByName(@RequestParam String isBaseLib, @RequestParam String installLibName) {
        int deleteCount = installLibService.deleteByName(isBaseLib, installLibName);
        return ResultGenerator.genSuccessResult(deleteCount);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull InstallLib installLib) {
        if (installLib.getIsBaseLib().equals(Constant.IsBaseLibEnum.BASE_LIB_ENUM.getValue().toString())) {
            List<InstallLib> list = installLibService.selectLibList(Constant.IsBaseLibEnum.BASE_LIB_ENUM.getValue().toString(), installLib.getInstallLibName());
            if (list.size() > 0) {
                return ResultGenerator.genFailResult("不能添加重复的调试项!");
            }
        }
        installLibService.update(installLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        InstallLib installLib = installLibService.findById(id);
        return ResultGenerator.genSuccessResult(installLib);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InstallLib> list = installLibService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectLibList")//install_lib_name
    public Result selectLibList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, @RequestParam String isBaseLib, @RequestParam String installLibName) {
        PageHelper.startPage(page, size);
        List<InstallLib> list = installLibService.selectLibList(isBaseLib, installLibName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
