package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.repair_record.RepairRecordInfo;
import com.eservice.api.service.RepairRecordService;
import com.eservice.api.service.impl.RepairRecordServiceImpl;
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
@RequestMapping("/repair/record")
public class RepairRecordController {
    @Resource
    private RepairRecordServiceImpl repairRecordService;

    @PostMapping("/add")
    public Result add(RepairRecord repairRecord) {
        repairRecordService.save(repairRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(RepairRecord repairRecord) {
        repairRecordService.update(repairRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        RepairRecord repairRecord = repairRecordService.findById(id);
        return ResultGenerator.genSuccessResult(repairRecord);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RepairRecord> list = repairRecordService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectByNameplate")
    public Result selectByNameplate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                    @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<RepairRecord> list = repairRecordService.selectByNameplate(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据条件查询维修信息
     * @param nameplate
     * @param orderNum
     * @param repairStatus
     * @param repairRecordCustomerName -- 维修记录中的客户联系人，不是machine的customerName
     * @param agent -- 机器的代理商的名称
     * @param repairChargePersonName -- 维修员负责人
     * @param issuePositionName
     * @param inWarrantyPeriod
     * @param queryStartRepairCreateTime -- 报修时间 查询起点
     * @param queryFinishRepairCreateTime --报修时间 结束点
     * @param queryStartRepairEndTime -- 维修完成时间 查询起点
     * @param queryFinishRepairEndTime -- 维修完成时间 查询终点
     * @param isFuzzy
     */
    @PostMapping("/getRepairRecordInfoList")
    public Result getRepairRecordInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                           String nameplate,
                                           String orderNum,
                                           String repairStatus,
                                           String repairRecordCustomerName,
                                           String agent,
                                           String repairChargePersonName,
                                           String issuePositionName,
                                           String inWarrantyPeriod,
                                           String queryStartRepairCreateTime,
                                           String queryFinishRepairCreateTime,
                                           String queryStartRepairEndTime,
                                           String queryFinishRepairEndTime,
                                           boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<RepairRecordInfo> list = repairRecordService.getRepairRecordInfoList(
                nameplate,
                orderNum,
                repairStatus,
                repairRecordCustomerName,
                agent,
                repairChargePersonName,
                issuePositionName,
                inWarrantyPeriod,
                queryStartRepairCreateTime,
                queryFinishRepairCreateTime,
                queryStartRepairEndTime,
                queryFinishRepairEndTime,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户(无论是负责人还是维修成员)返回他的维修待处理机器
     */
    @PostMapping("/selectRepairTaskMachine")
    public Result selectRepairTaskMachine(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                    @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<Machine> list = repairRecordService.selectRepairTaskMachine(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
