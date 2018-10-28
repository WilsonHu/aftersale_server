package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_lib.InstallLib;
import com.eservice.api.model.install_members.InstallMembers;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.install_record.InstallRecordInfo;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.user.User;
import com.eservice.api.service.common.CommonUtils;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.InstallInfoJsonData;
import com.eservice.api.service.impl.InstallLibServiceImpl;
import com.eservice.api.service.impl.InstallMembersServiceImpl;
import com.eservice.api.service.impl.InstallRecordServiceImpl;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class Description: xxx
 *
 * @author Wilson Hu
 * @date 2018/08/04.
 */
@RestController
@RequestMapping("/install/record")
public class InstallRecordController {
    @Resource
    private InstallRecordServiceImpl installRecordService;
    @Resource
    private InstallMembersServiceImpl installMembersService;

    @Resource
    private InstallLibServiceImpl installLibService;

    @Resource
    private MachineServiceImpl machineService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull InstallRecord installRecord) {
        installRecord.setInstallRecordNum(CommonUtils.generateSequenceNo());
        installRecordService.save(installRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        installRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull InstallRecord installRecord) {
        installRecord.setInstallActualTime(new Date());
        installRecordService.update(installRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
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
     * 根据条件查询安装信息
     *
     * @param page
     * @param size
     * @param nameplate
     * @param orderNum
     * @param machineType
     * @param agent                            代理商名称, 不同代理商可以传入不同名字，配合isAgent代理商只看到自己的数据。
     * @param installStatus                    安装状态，0：已分配安装(但未接单）；1：已接受任务（不用驳回，如果有异议，电话沟通后可以重新派单）； 2：安装OK(客户未确认); 3：安装NG(如果用不到这个就不用）4：客户已确认
     * @param installRecordCustomerName        记录中的customerName，不是machine的customerName
     * @param installChargePersonName          安装工负责人名字
     * @param query_start_install_plan_date
     * @param query_finish_install_plan_date
     * @param query_start_facory_date
     * @param query_finish_facory_date
     * @param query_start_install_actual_time
     * @param query_finish_install_actual_time
     * @param machineCustomerName              机器的客户的名称
     * @param isAgent                  传入true表示是代理商只查询: 精确查询agent （即使isFuzzy为true）
     *                                 （代理商在查询时指定isFuzzy为false是可以精确查询,但是代理商模糊查询时需要此参数）
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
                                           String installRecordId,
                                           String machineCustomerName,
                                           boolean isAgent,
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
                installRecordId,
                machineCustomerName,
                isAgent,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户(无论是负责人还是安装成员)返回他的安装待处理机器,如果用户为空则返回所有待处理的安装相关的机器。
     * TODO: 确认是否重复数据问题 ---应该是假数据本身问题，待后面真实数据再确认一次。
     */
    @PostMapping("/selectInstallTaskByUser")
    public Result selectInstallTaskByUser(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                          @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<InstallRecordInfo> list = installRecordService.selectInstallTaskByUser(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/AssignTask")
    public Result AssignTask(String installRecord, String installMembers) {
        try {
            InstallRecord model = JSON.parseObject(installRecord, InstallRecord.class);
            List<InstallMembers> members = JSONObject.parseArray(installMembers, InstallMembers.class);

            if (model == null || members == null || members.size() < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setUpdateTime(new Date());
            model.setInstallStatus(com.eservice.api.service.common.Constant.INSTALL_STATUS_ASSIGNED);
            installRecordService.update(model);
            List<User> list = installMembersService.getMembersByInstallRecordId(model.getId().toString());
            for (User u : list) {
                for (InstallMembers m : members) {
                    if (m.getUserId() == u.getId()) {
                        members.remove(m);
                        break;
                    }
                }
            }
            if (members.size() > 0) {
                installMembersService.save(members);
            }

            /**
             * 也更新机器状态,web未传入铭牌号，需要重新获取一下
             */
            InstallRecord installRecord1 = installRecordService.findById(model.getId());
            Machine machine = machineService.findBy("nameplate",installRecord1.getMachineNameplate());
            machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_INSTALL);
            machineService.update(machine);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updateInstallInfo")
    public Result updateInstallInfo(String installRecord, String installLibList) {
        InstallRecord model = JSON.parseObject(installRecord, InstallRecord.class);
        if (model == null || installLibList == null || installLibList.length() == 0) {
            return ResultGenerator.genFailResult("参数错误！");
        }
        try {
            List<InstallLib> list = JSON.parseArray(installLibList, InstallLib.class);
            List<InstallInfoJsonData> installInfoList = new ArrayList<InstallInfoJsonData>();
            for (InstallLib item : list) {
                InstallInfoJsonData info = new InstallInfoJsonData();
                if (info.getIs_base_lib() == "0") {
                    continue;
                }
                info.setInstall_content(item.getInstallContent());
                info.setInstall_lib_name(item.getInstallLibName());
                info.setIs_base_lib(item.getIsBaseLib());
                info.setInstall_value("");
                installInfoList.add(info);
            }
            list.clear();
            list = null;
            model.setInstallInfo(JSONArray.toJSONString(installInfoList));
            installRecordService.update(model);
        } catch (Exception e) {
            return ResultGenerator.genFailResult("设置安装项失败！" + e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

}
