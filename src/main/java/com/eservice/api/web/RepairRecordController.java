package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.repair_members.RepairMembers;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.repair_record.RepairRecordInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.service.common.CommonUtils;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.WxMessageTemplateJsonData;
import com.eservice.api.service.impl.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
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
@RequestMapping("/repair/record")
public class RepairRecordController {
    @Resource
    private RepairRecordServiceImpl repairRecordService;

    @Resource
    private RepairMembersServiceImpl repairMembersService;

    @Resource
    private MachineServiceImpl machineService;

    @Resource
    private UserServiceImpl userService;

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    private Logger logger = Logger.getLogger(RepairRecordController.class);

    @Value("${debug.flag}")
    private String debugFlag;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull RepairRecord repairRecord) {
        repairRecord.setRepairRecordNum(CommonUtils.generateSequenceNo());
        repairRecordService.save(repairRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull RepairRecord repairRecord) {

        /**
         * 维修任务的接单，需要在这里update时发信息推送
         * 维修任务的完成，在RepairAcutalInfoController.update
         */
        boolean repairTaskAccept = false;

        RepairRecord repairRecordOld = repairRecordService.findById(repairRecord.getId());
        if((!repairRecordOld.getStatus().equals(Constant.REPAIR_STATUS_REPAIRER_ACCEPTED)) && (repairRecord.getStatus().equals(Constant.REPAIR_STATUS_REPAIRER_ACCEPTED))) {
            repairTaskAccept = true;
        }
        repairRecordService.update(repairRecord);
        repairRecord.setUpdateTime(new Date());

        if(debugFlag.equalsIgnoreCase("true")) {
            logger.info(" get old repairRecord " + repairRecordOld.getStatus());
            logger.info("get new repairRecord " + repairRecord.getStatus());
            logger.info("get repairTaskAccept :" + repairTaskAccept);
        }
        User customer = userService.findById(repairRecordOld.getCustomer());
        if(customer == null) {
            logger.info("找不到对应的客户，请检查！ customerId： " + repairRecordOld.getCustomer() );
            return ResultGenerator.genFailResult("找不到对应的客户，请检查！");
        }
        User repairCharger = userService.findById(repairRecordOld.getRepairChargePerson());
        if(repairCharger == null) {
            logger.info("找不到对应的维修负责人，请检查！ getRepairChargePerson： " + repairRecordOld.getRepairChargePerson() );
            return ResultGenerator.genFailResult("找不到对应的维修负责人，请检查！");
        }
        WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
        try {
            /**
             * 安装负责人接单时, 发送消息给客户
             */
            if(repairTaskAccept) {
                //            {{first.DATA}}
                //            订单号：{{keyword1.DATA}}
                //            工程师姓名：{{keyword2.DATA}}
                //            工程师电话：{{keyword3.DATA}}
                //            上门时间：{{keyword4.DATA}}
                //            {{remark.DATA}}
                wxMessageTemplateJsonData.setCustomerName(customer.getName());
                wxMessageTemplateJsonData.setMachineNameplate(repairRecordOld.getMachineNameplate()+ "已安排维修");
                wxMessageTemplateJsonData.setRepairChargePerson(repairCharger.getName());
                wxMessageTemplateJsonData.setRepairChargePersonPhone(repairCharger.getPhone());
                wxMessageTemplateJsonData.setRepairPlanDate(repairRecordOld.getRepairPlanDate());
                wxMessageTemplateJsonData.setRepairTaskMessage("请知悉");
                wechatUserInfoService.sendMsgTemplate(customer.getAccount(),
                        Constant.WX_TEMPLATE_3_TASK_ACCEPTED,
                        Constant.WX_MSG_7_REPAIR_ACCEPT_TO_CUSTOMER,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));

            }
        } catch ( Exception e) {
            logger.info("发送消息给客户失败 " + e.toString());
            e.printStackTrace();
        }

            return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
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
     *
     * @param nameplate
     * @param orderNum
     * @param repairStatus
     * @param repairRecordCustomerName    -- 维修记录中的客户联系人，不是machine的customerName
     * @param agent                       -- 机器的代理商的名称，不同代理商可以传入不同名字，配合isAgent代理商只看到自己的数据。
     * @param repairChargePersonName      -- 维修员负责人
     * @param issuePositionId             -- 问题部位ID,只要包含有该ID号的记录就会被返回
     * @param inWarrantyPeriod
     * @param queryStartRepairCreateTime  -- 报修时间 查询起点
     * @param queryFinishRepairCreateTime --报修时间 结束点
     * @param queryStartRepairEndTime     -- 维修完成时间 查询起点
     * @param queryFinishRepairEndTime    -- 维修完成时间 查询终点
     * @param repairRecordId
     * @param isAgent                  传入true表示是代理商只查询: 精确查询agent （即使isFuzzy为true）
     *                                 （代理商在查询时指定isFuzzy为false是可以精确查询,但是代理商模糊查询时需要此参数）
     * @param isFuzzy
     */
    @PostMapping("/getRepairRecordInfoList")
    public Result getRepairRecordInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                          String nameplate,
                                          String orderNum,
                                          String repairStatus,
//                                           String partsStatus,
                                          String repairRecordCustomerName,
                                          String agent,
                                          String repairChargePersonName,
                                          String issuePositionId,
                                          String inWarrantyPeriod,
                                          String queryStartRepairCreateTime,
                                          String queryFinishRepairCreateTime,
                                          String queryStartRepairEndTime,
                                          String queryFinishRepairEndTime,
                                          String repairRecordId,
                                          String repairActualInfoId,
                                          boolean isAgent,
                                          boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<RepairRecordInfo> list = repairRecordService.getRepairRecordInfoList(
                nameplate,
                orderNum,
                repairStatus,
//                partsStatus,
                repairRecordCustomerName,
                agent,
                repairChargePersonName,
                issuePositionId,
                inWarrantyPeriod,
                queryStartRepairCreateTime,
                queryFinishRepairCreateTime,
                queryStartRepairEndTime,
                queryFinishRepairEndTime,
                repairRecordId,
                repairActualInfoId,
                isAgent,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户(无论是负责人还是维修成员)返回他的维修待处理 任务
     * 确实返回“重复”数据， 是因为没有用到repairMember，算上repairMember是不重复的。
     * 维修成员也必须看到维修任务，由前端对“重复”数据进行去重。
     */
    @PostMapping("/selectRepairTaskByUser")
    public Result selectRepairTaskByUser(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<RepairRecordInfo> list = repairRecordService.selectRepairTaskByUser(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/AssignTask")
    public Result AssignTask(String repairRecord, String repairMembers) {
        try {
            RepairRecord model = JSON.parseObject(repairRecord, RepairRecord.class);
            List<RepairMembers> membersNewAdd = JSONObject.parseArray(repairMembers, RepairMembers.class);
            if (model == null || membersNewAdd == null || membersNewAdd.size() < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setUpdateTime(new Date());
            model.setStatus(com.eservice.api.service.common.Constant.REPAIR_STATUS_SIGNED_TO_REPAIRER);
            repairRecordService.update(model);
            List<User> list = repairMembersService.getMembersByRepairRecordId(model.getId().toString());
            for (User u : list) {
                for (RepairMembers m : membersNewAdd) {
                    if (m.getUserId() == u.getId()) {
                        membersNewAdd.remove(m);
                        break;
                    }
                }
            }
            if (membersNewAdd.size() > 0) {
                repairMembersService.save(membersNewAdd);
            }
            /**
             * 也更新机器状态
             */
            RepairRecord repairRecord1 = repairRecordService.findById(model.getId());
            Machine machine = machineService.findBy("nameplate",repairRecord1.getMachineNameplate());
            machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_REPAIR);
            machineService.update(machine);
            /**
             * 维修任务 发送消息给负责人和其他成员
             */
            User repairCharger = userService.findById(model.getRepairChargePerson());
            if(repairCharger == null) {
                logger.info("找不到对应的员工，请检查！ customerId： " + model.getRepairChargePerson() );
                return ResultGenerator.genFailResult("找不到对应的员工，请检查！");
            }
            User customer = userService.findById(model.getCustomer());
            if(customer == null) {
                logger.info("找不到对应的客户，请检查！ customerId： " + model.getCustomer() );
                return ResultGenerator.genFailResult("找不到对应的客户，请检查！");
            }
            //            {{first.DATA}}
            //            任务号：{{keyword1.DATA}}
            //            任务类型：{{keyword2.DATA}}
            //            执行人：{{keyword3.DATA}}
            //            分派人：{{keyword4.DATA}}
            //            分派时间：{{keyword5.DATA}}
            //            {{remark.DATA}}
            WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
            wxMessageTemplateJsonData.setMachineNameplate(machine.getNameplate());//任务号
            wxMessageTemplateJsonData.setRepairTaskMessage("维修机器");//任务类型
            wxMessageTemplateJsonData.setRepairChargePerson(repairCharger.getName() + "团队");//执行人
            wxMessageTemplateJsonData.setRepairPlanDate(model.getRepairPlanDate());//分配时间
            List<User> userOfMembersNewAdd = new ArrayList<>();
            for(RepairMembers rm :membersNewAdd){
                User u = userService.findById(rm.getUserId());
                userOfMembersNewAdd.add(u);
            }

            for (User u : userOfMembersNewAdd) {
                wechatUserInfoService.sendMsgTemplate(u.getAccount(),
                        Constant.WX_TEMPLATE_2_TASK_COMMING,
                        Constant.WX_MSG_4_REPAIR_TASK_TO_EMPLOYEE,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));
            }

        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/AssignTaskAgain")
    public Result AssignTaskAgain(Integer oldId, String repairRecord, String repairMembers) {
        try {
            RepairRecord model = JSON.parseObject(repairRecord, RepairRecord.class);
            List<RepairMembers> membersNewAdd = JSONObject.parseArray(repairMembers, RepairMembers.class);
            if (model == null || membersNewAdd == null || membersNewAdd.size() < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setCreateTime(new Date());
            model.setForwardInfo(com.eservice.api.service.common.Constant.REPAIR_IS_FORWARD_NO);
            model.setStatus(com.eservice.api.service.common.Constant.REPAIR_STATUS_SIGNED_TO_REPAIRER);
            model.setRepairRecordNum(CommonUtils.generateSequenceNo());
            repairRecordService.save(model);// add a new record
            List<User> list = repairMembersService.getMembersByRepairRecordId(model.getId().toString());
            for (User u : list) {
                for (RepairMembers m : membersNewAdd) {
                    if (m.getUserId() == u.getId()) {
                        membersNewAdd.remove(m);
                        break;
                    }
                }
            }
            if (membersNewAdd.size() > 0) {
                repairMembersService.save(membersNewAdd);
            }
            //update the old record
            RepairRecord oldRecord = new RepairRecord();
            oldRecord.setId(oldId);
            oldRecord.setStatus(com.eservice.api.service.common.Constant.REPAIR_STATUS_REPAIRER_REASSIGN);
            oldRecord.setUpdateTime(new Date());
            repairRecordService.update(oldRecord);

            /**
             * 也更新机器状态
             */
            RepairRecord repairRecord1 = repairRecordService.findById(model.getId());
            Machine machine = machineService.findBy("nameplate",repairRecord1.getMachineNameplate());
            machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_REPAIR);
            machineService.update(machine);

            /**
             * 维修任务 发送消息给负责人和其他成员
             */
            User repairCharger = userService.findById(model.getRepairChargePerson());
            if(repairCharger == null) {
                logger.info("找不到对应的员工，请检查！ customerId： " + model.getRepairChargePerson() );
                return ResultGenerator.genFailResult("找不到对应的员工，请检查！");
            }
            User customer = userService.findById(model.getCustomer());
            if(customer == null) {
                logger.info("找不到对应的客户，请检查！ customerId： " + model.getCustomer() );
                return ResultGenerator.genFailResult("找不到对应的客户，请检查！");
            }
            //            {{first.DATA}}
            //            任务号：{{keyword1.DATA}}
            //            任务类型：{{keyword2.DATA}}
            //            执行人：{{keyword3.DATA}}
            //            分派人：{{keyword4.DATA}}
            //            分派时间：{{keyword5.DATA}}
            //            {{remark.DATA}}
            WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
            wxMessageTemplateJsonData.setMachineNameplate(machine.getNameplate());//任务号
            wxMessageTemplateJsonData.setRepairTaskMessage("维修机器");//任务类型
            wxMessageTemplateJsonData.setRepairChargePerson(repairCharger.getName() + "团队");//执行人
            wxMessageTemplateJsonData.setRepairPlanDate(model.getRepairPlanDate());//分配时间
            List<User> userOfMembersNewAdd = new ArrayList<>();
            for(RepairMembers rm :membersNewAdd){
                User u = userService.findById(rm.getUserId());
                userOfMembersNewAdd.add(u);
            }
            for (User u : userOfMembersNewAdd) {
                wechatUserInfoService.sendMsgTemplate(u.getAccount(),
                        Constant.WX_TEMPLATE_2_TASK_COMMING,
                        Constant.WX_MSG_4_REPAIR_TASK_TO_EMPLOYEE,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));
            }

        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/AssignTaskForward")
    public Result AssignTaskForward(Integer oldId, String repairRecord) {
        try {
            RepairRecord model = JSON.parseObject(repairRecord, RepairRecord.class);
            if (model == null || oldId < 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setCreateTime(new Date());
            model.setForwardInfo(com.eservice.api.service.common.Constant.REPAIR_IS_FORWARD_YES);
            model.setStatus(com.eservice.api.service.common.Constant.REPAIR_STATUS_UNSIGNED_TO_REPAIRER);
            model.setRepairRecordNum(CommonUtils.generateSequenceNo());
            repairRecordService.save(model); // add a new record

            //update the old record
            RepairRecord oldRecord = new RepairRecord();
            oldRecord.setId(oldId);
            oldRecord.setStatus(com.eservice.api.service.common.Constant.REPAIR_STATUS_REPAIRER_FORWARD);
            oldRecord.setUpdateTime(new Date());
            repairRecordService.update(oldRecord);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 根据报修记录id返回维修记录
     */
    @PostMapping("/selectRepairRecordByRepairRequestId")
    public Result selectRepairRecordByRepairRequestId(@RequestParam(defaultValue = "0") Integer page,
                                                      @RequestParam(defaultValue = "0") Integer size,
                                                      @RequestParam String repairRequestInfoId) {
        PageHelper.startPage(page, size);
        List<RepairRecord> list = repairRecordService.selectRepairRecordByRepairRequestId(repairRequestInfoId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据报修记录id返回状态为"提交报修中"的维修记录
     * 每次添加报修时，都会删除旧的报修记录，一个报修id只会有一个"提交报修中"的维修记录
     */
    @PostMapping("/selectRepairRecordInRequesting")
    public Result selectRepairRecordInRequesting(@RequestParam String repairRequestInfoId) {
        RepairRecord repairRecord1 = repairRecordService.selectRepairRecordInRequesting(repairRequestInfoId);
        return ResultGenerator.genSuccessResult(repairRecord1);
    }

    /**
     * 根据配件信息ID，查询维修记录
     */
    @PostMapping("/selectRepairRecordByPartsInfoId")
    public Result selectRepairRecordByPartsInfoId(@RequestParam String partsInfoId) {
        RepairRecord repairRecord1 = repairRecordService.selectRepairRecordByPartsInfoId(partsInfoId);
        return ResultGenerator.genSuccessResult(repairRecord1);
    }

}
