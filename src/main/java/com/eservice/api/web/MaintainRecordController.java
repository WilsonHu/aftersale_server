package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_members.InstallMembers;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.maintain_record.MaintainRecord;
import com.eservice.api.model.maintain_record.MaintainRecordInfo;
import com.eservice.api.service.impl.InstallMembersServiceImpl;
import com.eservice.api.service.impl.MaintainRecordServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/maintain/record")
public class MaintainRecordController {
    @Resource
    private MaintainRecordServiceImpl maintainRecordService;
    @Resource
    private InstallMembersServiceImpl installMembersService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull MaintainRecord maintainRecord) {
        maintainRecordService.save(maintainRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull MaintainRecord maintainRecord) {
        maintainRecordService.update(maintainRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        MaintainRecord maintainRecord = maintainRecordService.findById(id);
        return ResultGenerator.genSuccessResult(maintainRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<MaintainRecord> list = maintainRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectByNameplate")
    public Result selectByNameplate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                    @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<MaintainRecord> list = maintainRecordService.selectByNameplate(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据条件查询保养信息
     *
     * @param nameplate
     * @param orderNum
     * @param agent                      -- 代理商名称
     * @param maintainStatus
     * @param customerName               保养的客户联系人名称
     * @param machineType
     * @param maintainChargePerson       保养员负责人
     * @param query_start_time_maintain
     * @param query_finish_time_maintain
     * @param isFuzzy
     * @return
     */
    @PostMapping("/getMaintainRecordInfoList")
    public Result getMaintainRecordInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                            String nameplate,
                                            String orderNum,
                                            String agent,
                                            String maintainStatus,
                                            String customerName,
                                            String machineType,
                                            String maintainChargePerson,
                                            String query_start_time_maintain,
                                            String query_finish_time_maintain,
                                            boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<MaintainRecordInfo> list = maintainRecordService.getMaintainRecordInfoList(
                nameplate,
                orderNum,
                agent,
                maintainStatus,
                customerName,
                machineType,
                maintainChargePerson,
                query_start_time_maintain,
                query_finish_time_maintain,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户(无论是负责人还是保养成员)返回他的保养待处理机器,如果用户为空则返回所有待处理的保养相关的机器。
     */
    @PostMapping("/selectMaintainTaskMachine")
    public Result selectMaintainTaskMachine(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                            @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<Machine> list = maintainRecordService.selectMaintainTaskMachine(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/AssignTask")
    public Result AssignTask(String maintainRecord, String installMembers) {
        try {
            MaintainRecord model = JSON.parseObject(maintainRecord, MaintainRecord.class);
            List<InstallMembers> members = JSONObject.parseArray(installMembers, InstallMembers.class);

            if (model == null || members == null || members.size() < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setCreateTime(new Date());
            model.setMaintainStatus(com.eservice.api.service.common.Constant.MAINTAIN_STATUS_ASSIGNED);
            maintainRecordService.save(model);
            for (InstallMembers member : members) {
                member.setInstallRecordId(model.getId());//set record Id
            }
            installMembersService.save(members);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
}
