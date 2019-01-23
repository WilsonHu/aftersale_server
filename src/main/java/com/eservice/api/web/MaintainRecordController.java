package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.maintain_lib.MaintainLib;
import com.eservice.api.model.maintain_members.MaintainMembers;
import com.eservice.api.model.maintain_record.MaintainRecord;
import com.eservice.api.model.maintain_record.MaintainRecordInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.MaintainContentData;
import com.eservice.api.service.common.MaintainData;
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
 * @date 2018/07/10.
 */
@RestController
@RequestMapping("/maintain/record")
public class MaintainRecordController {
    @Resource
    private MaintainRecordServiceImpl maintainRecordService;
    @Resource
    private MaintainMembersServiceImpl maintainMembersService;
    @Resource
    private MaintainLibServiceImpl maintainLibService;
    @Resource
    private MachineServiceImpl machineService;
    @Resource
    private UserServiceImpl userService;
    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;
    private Logger logger = Logger.getLogger(MaintainRecordController.class);

    @Value("${debug.flag}")
    private String debugFlag;

    @Value("${WX_TEMPLATE_3_TASK_ACCEPTED}")
    private String WX_TEMPLATE_3_TASK_ACCEPTED;

    @Value("${WX_TEMPLATE_4_TASK_DONE}")
    private String WX_TEMPLATE_4_TASK_DONE;

    @Value("${WX_TEMPLATE_2_TASK_COMMING}")
    private String WX_TEMPLATE_2_TASK_COMMING;

    private String generateMaintainInfo(String libName) {
        String result = "";
        List<MaintainData> maintainDataList = new ArrayList<MaintainData>();
        List<MaintainLib> libList = maintainLibService.selectLibList("1", libName);//查出所有子项内容
        for (MaintainLib libData : libList) {
            boolean isFound = false;
            MaintainContentData contentData = new MaintainContentData();
            contentData.setContent(libData.getMaintainContent());
            for (MaintainData item : maintainDataList) {
                if (item.getMaintainType() == libData.getMaintainType()) {
                    if (item.getContentList() != null) {
                        item.getContentList().add(contentData);
                    }
                    isFound = true;
                }
            }
            if (!isFound) {
                MaintainData data = new MaintainData();
                data.setMaintainType(libData.getMaintainType());
                data.setContentList(new ArrayList<MaintainContentData>());
                data.getContentList().add(contentData);
                maintainDataList.add(data);
            }
        }
        result = JSONArray.toJSONString(maintainDataList);
        return result;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull MaintainRecord maintainRecord) {
        //maintainRecord.setMaintainInfo(generateMaintainInfo(maintainRecord.getMaintainLibName()));
        maintainRecordService.save(maintainRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/addList")
    public Result addList(String maintainRecordList) {
        List<MaintainRecord> recordList = JSONObject.parseArray(maintainRecordList, MaintainRecord.class);
        for (MaintainRecord mr : recordList) {
            mr.setCreateTime(new Date());
        }
        maintainRecordService.save(recordList);
        return ResultGenerator.genSuccessResult();
    }


    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull MaintainRecord maintainRecord) {

        boolean maintainTaskAccept = false;
        boolean maintainTaskDone = false;
        MaintainRecord maintainRecordOld = maintainRecordService.findById(maintainRecord.getId());
        if((!maintainRecordOld.getMaintainStatus().equals(Constant.MAINTAIN_STATUS_TASK_DOING)) && (maintainRecord.getMaintainStatus().equals(Constant.MAINTAIN_STATUS_TASK_DOING))) {
            maintainTaskAccept = true;
        }
        if((!maintainRecordOld.getMaintainStatus().equals(Constant.MAINTAIN_STATUS_FINISHED)) && (maintainRecord.getMaintainStatus().equals(Constant.MAINTAIN_STATUS_FINISHED))){
            maintainTaskDone = true;
        }
        maintainRecord.setMaintainDateActual(new Date());
        maintainRecordService.update(maintainRecord);

        if(debugFlag.equalsIgnoreCase("true")) {
            logger.info(" get old MaintainStatus " + maintainRecordOld.getMaintainStatus());
            logger.info("get new MaintainStatus " + maintainRecord.getMaintainStatus());
            logger.info("get maintainTaskAccept/maintainTaskDone :" + maintainTaskAccept + " / " + maintainTaskDone);
        }
        User customer = userService.findById(maintainRecordOld.getCustomer());
        if(customer == null) {
            logger.info("找不到对应的客户，请检查！ customerId： " + maintainRecordOld.getCustomer() );
            return ResultGenerator.genFailResult("找不到对应的客户，请检查！");
        }
        User maintainCharger = userService.findById(maintainRecordOld.getMaintainChargePerson());
        if(maintainCharger == null) {
            logger.info("找不到对应的保养负责人，请检查！  " + maintainRecordOld.getMaintainChargePerson() );
            return ResultGenerator.genFailResult("找不到对应的保养负责人，请检查！");
        }
        WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
        try {
            /**
             * 保养负责人接单时, 发送消息给客户
             */
            if(maintainTaskAccept) {
                //            {{first.DATA}}
                //            订单号：{{keyword1.DATA}}
                //            工程师姓名：{{keyword2.DATA}}
                //            工程师电话：{{keyword3.DATA}}
                //            上门时间：{{keyword4.DATA}}
                //            {{remark.DATA}}
                wxMessageTemplateJsonData.setCustomerName(customer.getName());
                wxMessageTemplateJsonData.setMachineNameplate(maintainRecordOld.getMachineNameplate() + "已安排保养");
                wxMessageTemplateJsonData.setMaintainChargePerson(maintainCharger.getName());
                wxMessageTemplateJsonData.setMaintainChargePersonPhone(maintainCharger.getPhone());
                wxMessageTemplateJsonData.setMaintainPlanDate(maintainRecordOld.getMaintainDatePlan());
                wxMessageTemplateJsonData.setMaintainTaskMessage("请知悉");
                wechatUserInfoService.sendMsgTemplate(customer.getAccount(),
                        WX_TEMPLATE_3_TASK_ACCEPTED,
                        Constant.WX_MSG_6_MAINTAIN_ACCEPT_TO_CUSTOMER,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));

            }
            /**
             * 保养负责人提交结果时, 发送消息给客户
             */
            if(maintainTaskDone) {
                //                {{first.DATA}}
                //                任务名称：{{keyword1.DATA}}
                //                负责人：{{keyword2.DATA}}
                //                提交时间：{{keyword3.DATA}}
                //                {{remark.DATA}}
                wxMessageTemplateJsonData.setCustomerName(customer.getName());
                wxMessageTemplateJsonData.setMaintainTaskName("机器保养");
                wxMessageTemplateJsonData.setMaintainChargePerson(maintainCharger.getName());
                wxMessageTemplateJsonData.setMaintainActualTime(maintainRecordOld.getMaintainDatePlan());
                wxMessageTemplateJsonData.setMaintainTaskDoneMessage("请知悉");
                wechatUserInfoService.sendMsgTemplate(customer.getAccount(),
                        WX_TEMPLATE_4_TASK_DONE,
                        Constant.WX_MSG_9_MAINTAIN_DONE_TO_CUSTOMER,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));
            }

        } catch ( Exception e) {
            logger.info("发送消息给客户失败 " + e.toString());
            e.printStackTrace();
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/updateMaintainInfo")
    public Result updateMaintainInfo(@RequestBody @NotNull MaintainRecord maintainRecord) {
        maintainRecord.setMaintainInfo(generateMaintainInfo(maintainRecord.getMaintainLibName()));
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
     * @param agent                      -- 代理商名称，不同代理商可以传入不同名字，配合isAgent代理商只看到自己的数据。
     * @param maintainStatus
     * @param customerName               保养的客户联系人名称
     * @param machineType
     * @param maintainChargePerson       保养员负责人
     * @param query_start_time_maintain
     * @param query_finish_time_maintain
     * @param isAgent                  传入true表示是代理商只查询: 精确查询agent （即使isFuzzy为true）
     *                                 （代理商在查询时指定isFuzzy为false是可以精确查询,但是代理商模糊查询时需要此参数）
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
                                            String maintainRecordId,
                                            boolean isAgent,
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
                maintainRecordId,
                isAgent,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户(无论是负责人还是保养成员)返回他的保养待处理 任务,如果用户为空则返回所有待处理的保养任务。
     *  2019-01-23： 其实是根据account来查询，不是name来查询。接口名称先不改，调用者也可以不改。
     */
    @PostMapping("/selectMaintainTaskByUser")
    public Result selectMaintainTaskByUser(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                           @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<MaintainRecordInfo> list = maintainRecordService.selectMaintainTaskByUser(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/AssignTask")
    public Result AssignTask(String maintainRecord, String maintainMembers) {
        try {
            MaintainRecord model = JSON.parseObject(maintainRecord, MaintainRecord.class);
            List<MaintainMembers> membersNewAdd = JSONObject.parseArray(maintainMembers, MaintainMembers.class);

            if (model == null || membersNewAdd == null || membersNewAdd.size() < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setUpdateTime(new Date());
            model.setMaintainStatus(com.eservice.api.service.common.Constant.MAINTAIN_STATUS_ASSIGNED);
            maintainRecordService.update(model);
            List<User> list = maintainMembersService.getMembersByMaintainRecordId(model.getId().toString());
            for (User u : list) {
                for (MaintainMembers m : membersNewAdd) {
                    if (m.getUserId() == u.getId()) {
                        membersNewAdd.remove(m);
                        break;
                    }
                }
            }
            if (membersNewAdd.size() > 0) {
                maintainMembersService.save(membersNewAdd);
            }

            /**
             * 也更新机器状态
             */
            MaintainRecord maintainRecord1 = maintainRecordService.findById(model.getId());
            Machine machine = machineService.findBy("nameplate",maintainRecord1.getMachineNameplate());
            machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_MAINTAIN);
            machineService.update(machine);

            /**
             * 保养任务 发送消息给安装负责人和其他成员
             */
            User maintainCharger = userService.findById(model.getMaintainChargePerson());
            if(maintainCharger == null) {
                logger.info("找不到对应的员工，请检查！ customerId： " + model.getMaintainChargePerson() );
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
            wxMessageTemplateJsonData.setMaintainTaskMessage("保养机器");//任务类型
            wxMessageTemplateJsonData.setMaintainChargePerson(maintainCharger.getName() + "团队");//执行人
            wxMessageTemplateJsonData.setMaintainPlanDate(model.getMaintainDatePlan());//分配时间
            List<User> userOfMembersNewAdd = new ArrayList<>();
            for(MaintainMembers mm :membersNewAdd){
                User u = userService.findById(mm.getUserId());
                userOfMembersNewAdd.add(u);
            }

            for (User u : userOfMembersNewAdd) {
                wechatUserInfoService.sendMsgTemplate(u.getAccount(),
                        WX_TEMPLATE_2_TASK_COMMING,
                        Constant.WX_MSG_3_MAINTAIN_TASK_TO_EMPLOYEE,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
}
