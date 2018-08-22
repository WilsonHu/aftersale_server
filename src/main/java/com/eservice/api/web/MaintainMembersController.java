package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.maintain_members.MaintainMembers;
import com.eservice.api.model.user.User;
import com.eservice.api.service.MaintainMembersService;
import com.eservice.api.service.impl.MaintainMembersServiceImpl;
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
@RequestMapping("/maintain/members")
public class MaintainMembersController {
    @Resource
    private MaintainMembersServiceImpl maintainMembersService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull MaintainMembers maintainMembers) {
        maintainMembersService.save(maintainMembers);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainMembersService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull MaintainMembers maintainMembers) {
        maintainMembersService.update(maintainMembers);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        MaintainMembers maintainMembers = maintainMembersService.findById(id);
        return ResultGenerator.genSuccessResult(maintainMembers);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MaintainMembers> list = maintainMembersService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    
    /**
     * 根据RecordId查询保养成员
     */
    @PostMapping("/getMembersByMaintainRecordId")
    public Result getMembersByMaintainRecordId(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                               @RequestParam String maintainRecordId) {
        PageHelper.startPage(page, size);
        List<User> list = maintainMembersService.getMembersByMaintainRecordId(maintainRecordId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
