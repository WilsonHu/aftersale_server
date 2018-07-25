package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_lib.InstallLib;
import com.eservice.api.model.maintain_type.MaintainType;
import com.eservice.api.service.MaintainTypeService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.MaintainTypeServiceImpl;
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
 *
 * @author Wilson Hu
 * @date 2018/07/10.
 */
@RestController
@RequestMapping("/maintain/type")
public class MaintainTypeController {
    @Resource
    private MaintainTypeServiceImpl maintainTypeService;

    @PostMapping("/add")
    public Result add(String maintainType) {
        MaintainType model = JSON.parseObject(maintainType, MaintainType.class);
        List<MaintainType> list = maintainTypeService.getListByParam(model.getMaintainTypeName());
        if (list.size() > 0) {
            return ResultGenerator.genFailResult("不能添加重复的保养类型!");
        }
        maintainTypeService.save(model);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainTypeService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(String maintainType) {
        MaintainType model = JSON.parseObject(maintainType, MaintainType.class);

        List<MaintainType> list = maintainTypeService.getListByParam(model.getMaintainTypeName());
        if (list.size() > 0) {
            return ResultGenerator.genFailResult("不能修改为重复保养类型!");
        }
        maintainTypeService.update(model);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        MaintainType maintainType = maintainTypeService.findById(id);
        return ResultGenerator.genSuccessResult(maintainType);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MaintainType> list = maintainTypeService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/getListByParam")
    public Result getListByParam(@RequestParam String name) {
        List<MaintainType> list = maintainTypeService.getListByParam(name);
        return ResultGenerator.genSuccessResult(list);
    }
}
