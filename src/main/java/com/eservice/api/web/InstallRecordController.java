package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.install_record.InstallRecordInfo;
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
     * 等待某联系人处理的安装任务
     */
    @PostMapping("/selectWaitProcessForGuest")
    public Result selectWaitProcessForGuest(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                            @RequestParam String contacter) {
        PageHelper.startPage(page, size);
        List<InstallRecord> list = installRecordService.selectWaitProcessForGuest(contacter);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @PostMapping("/selectByNameplate")
    public Result selectByNameplate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                    @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<InstallRecord> list = installRecordService.selectByNameplate(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据铭牌号查询安装信息详情
     */
    @PostMapping("/getInstallDetail")
    public Result getInstallDetail(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                            @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<InstallRecordInfo> list = installRecordService.getInstallDetail(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     *根据条件查询安装信息
     * @param page
     * @param size
     * @param nameplate
     * @param orderNum
     * @param machineType
     * @param agent 代理商名称
     * @param installStatus 安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     * @param installRecordCustomerName 记录中的customerName，不是machine的customerName
     * @param installChargePersonName 安装工负责人名字
     * @param query_start_install_plan_date
     * @param query_finish_install_plan_date
     * @param query_start_facory_date
     * @param query_finish_facory_date
     * @param query_start_install_actual_time
     * @param query_finish_install_actual_time
     * @param isFuzzy
     * @return
     */
    @PostMapping("/getInstallRecordInfoList")
    public Result getInstallRecordInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                           String nameplate,
                                           String orderNum,
                                           String machineType,
                                           String agent,
                                           String installStatus,
                                           String installRecordCustomerName,
                                           String installChargePersonName,
                                           String query_start_install_plan_date,
                                           String query_finish_install_plan_date,
                                           String query_start_facory_date,
                                           String query_finish_facory_date,
                                           String query_start_install_actual_time,
                                           String query_finish_install_actual_time,
                                           boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<InstallRecordInfo> list = installRecordService.getInstallRecordInfoList(
                nameplate,
                orderNum,
                machineType,
                agent,
                installStatus,
                installRecordCustomerName,
                installChargePersonName,
                query_start_install_plan_date,
                query_finish_install_plan_date,
                query_start_facory_date,
                query_finish_facory_date,
                query_start_install_actual_time,
                query_finish_install_actual_time,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
