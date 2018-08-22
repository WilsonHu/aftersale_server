package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_members.InstallMembers;
import com.eservice.api.model.user.User;
import com.eservice.api.service.InstallMembersService;
import com.eservice.api.service.impl.InstallMembersServiceImpl;
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
@RequestMapping("/install/members")
public class InstallMembersController {
    @Resource
    private InstallMembersServiceImpl installMembersService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull InstallMembers installMembers) {
        installMembersService.save(installMembers);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        installMembersService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull InstallMembers installMembers) {
        installMembersService.update(installMembers);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        InstallMembers installMembers = installMembersService.findById(id);
        return ResultGenerator.genSuccessResult(installMembers);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<InstallMembers> list = installMembersService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据RecordId查询安装成员
     */
    @PostMapping("/getMembersByInstallRecordId")
    public Result getMembersByInstallRecordId(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                               @RequestParam String installRecordId) {
        PageHelper.startPage(page, size);
        List<User> list = installMembersService.getMembersByInstallRecordId(installRecordId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
