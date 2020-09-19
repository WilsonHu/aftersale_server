package com.eservice.api.web;

import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.install_record.InstallRecord;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineBaseRecordInfo;
import com.eservice.api.model.machine.MachineInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.service.common.CommonUtils;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.PrepareUnAssignedMachineService;
import com.eservice.api.service.common.WxMessageTemplateJsonData;
import com.eservice.api.service.impl.InstallRecordServiceImpl;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.eservice.api.service.impl.UserServiceImpl;
import com.eservice.api.service.impl.WechatUserInfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Class Description: xxx
 *
 * @author Wilson Hu
 * @date 2018/08/04.
 */
@RestController
@RequestMapping("/machine")
public class MachineController {
    @Resource
    private MachineServiceImpl machineService;
    
    @Resource
    private UserServiceImpl userService;

    @Resource
    private InstallRecordServiceImpl installRecordService;

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    private Logger logger = Logger.getLogger(Machine.class);

    @Value("${debug.flag}")
    private String debugFlag;
    
    @Value("${WX_TEMPLATE_1_BOOK_SUCCESS}")
    private String WX_TEMPLATE_1_BOOK_SUCCESS;

    @Resource
    private PrepareUnAssignedMachineService prepareUnAssignedMachineService;
    @PostMapping("/add")
    public Result add(@RequestBody @NotNull Machine machine) {
        Machine dbMachine = machineService.findBy("nameplate", machine.getNameplate());
        if (dbMachine != null) {
            return ResultGenerator.genFailResult(machine.getNameplate() + "机器已存在，不能重复添加！");
        }
        machineService.save(machine);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 绑定机器到客户
     */
    @PostMapping("/addList")
    @Transactional(rollbackFor = Exception.class)
    public Result addList(String machineList) {

        String message = null;
        try {
            List<Machine> modelList = JSONObject.parseArray(machineList, Machine.class);
            if (modelList == null || modelList.size() == 0) {
                return ResultGenerator.genFailResult("提交列表没有可保存的数据，请检查！");
            }
            for (Machine item : modelList) {
                try {
                    Machine machine = machineService.findBy("nameplate", item.getNameplate());
                    if (machine != null) {
                        int index = modelList.indexOf(item) + 1;
                        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                        return ResultGenerator.genFailResult("第" + index + "个机器已存在，不能重复绑定！");
                    }
                    item.setStatus(com.eservice.api.service.common.Constant.MACHINE_STATUS_BOUND_TO_CUSTOMER);//machine status
                    machineService.save(item);

                    InstallRecord ir = new InstallRecord();
                    ir.setInstallRecordNum(CommonUtils.generateSequenceNo());
                    ir.setMachineNameplate(item.getNameplate());
                    ir.setInstallStatus(Constant.INSTALL_STATUS_NOT_ASSIGN);//install status
                    ir.setCreateTime(new Date());
                    installRecordService.save(ir);

                    if(debugFlag.equalsIgnoreCase("true")) {
                        logger.info("=========绑定机器=======" + item.getNameplate());
                    }
                    /**
                     * 机器绑定, 发送消息给客户
                     */
                    User customer = userService.findById(item.getCustomer());
                    if(customer == null) {
                        logger.info("找不到对应的客户，请检查！ customerId： " + item.getCustomer() );
                        return ResultGenerator.genFailResult("找不到对应的客户，请检查！");
                    }
                    //            {{first.DATA}}
                    //            订单号：{{keyword1.DATA}}
                    //            产品：{{keyword2.DATA}}
                    //            {{remark.DATA}}
                    WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
                    wxMessageTemplateJsonData.setCustomerName(customer.getName());
                    wxMessageTemplateJsonData.setMachineNameplate( item.getNameplate() + "(铭牌号)" );
                    wxMessageTemplateJsonData.setMachineType(item.getMachineType());
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                    String dateStr = formatter.format(item.getFacoryDate());
                    String msg = Constant.WX_MSG_1.replace("FactoryDate", dateStr);
                    wxMessageTemplateJsonData.setMessageOfMachineBind(msg);
                    message = wechatUserInfoService.sendMsgTemplate(customer.getAccount(),
                            WX_TEMPLATE_1_BOOK_SUCCESS,
                            Constant.WX_MSG_1_MACHINE_BIND_TO_CUSTOMER,
                            JSONObject.toJSONString(wxMessageTemplateJsonData));

                } catch (Exception ex) {
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                    logger.info("数据保存出错 " + message + ex.toString() );
                    return ResultGenerator.genFailResult("数据保存出错！" + message + ex.toString());
                }
            }
            //Refresh list
            prepareUnAssignedMachineService.prepareUnAssignedMachine();
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            logger.info("绑定失败 " + ex.toString() );
            return ResultGenerator.genFailResult(ex.toString());
        }
        return ResultGenerator.genSuccessResult(message);
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        List<InstallRecord> list = installRecordService.selectByNameplate(machineService.findById(id).getNameplate());
        for (int i = 0; i < list.size(); i++) {
            installRecordService.deleteById(list.get(i).getId());
            logger.info(list.get(i).getId() + " install record is deleted");
        }
        machineService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull Machine machine) {
        List<MachineInfo> machineList = machineService.getSaledMachineInfoList(machine.getNameplate(), null,
                null, null, null, null, null,null, null, false, false);
        for (Machine info : machineList) {
            if (! info.getId().equals( machine.getId())) {
                logger.info(machine.getNameplate() + "铭牌号已存在,不能修改！");
                return ResultGenerator.genFailResult(machine.getNameplate() + "铭牌号已存在,不能修改！");
            }
        }
        machineService.update(machine);
        return ResultGenerator.genSuccessResult();
    }

    //给 小程序 维修员在现场，更新老机器的信息，和 machine/update区别开了
    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody @NotNull Machine machine) {
        machineService.update(machine);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        Machine machine = machineService.findById(id);
        return ResultGenerator.genSuccessResult(machine);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Machine> list = machineService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /*
     *根据铭牌号返回该用户名下的机器，如果是老机器则提示老机器
     */
    @PostMapping("/selectByNameplateAndUserAccount")
    public Result selectByNameplateAndUserAccount(@RequestParam String nameplate,
                                    @RequestParam String userAccount) {
        Machine machine = machineService.findBy("nameplate", nameplate);
        /**
         * 找不到这个铭牌号，当作老机器。
         */
        if( machine == null){
            return ResultGenerator.genFailResult("oldMachine");
        }

        /**
         * 只能查询自己归属的公司的机器
         */
        User customerOfMachine = userService.findById(machine.getCustomer());

        /**
         * 防止老机器可能没有设置客户
         */
        if(customerOfMachine == null){
            return ResultGenerator.genFailResult("The machine has no customer");
        }

        if(! customerOfMachine.getAccount().equals(userAccount)){
            return ResultGenerator.genFailResult("notYourMachine");
        }

        /**
         *  已经标识过的老机器，需要返回机器信息，用户不需要重新申报老机器
         */
//        if( machine.getIsOldMachine().equals(Constant.MACHINE_TYPE_OLD) ) {
//            return ResultGenerator.genSuccessResult( "oldMachine");
//        } else {
            return ResultGenerator.genSuccessResult(machine);
//        }
    }

    /*
     * 根据账号返回该账号所属客户的机器列表
     */
    @PostMapping("/selectByAccount")
    public Result selectByCustomer(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                   @RequestParam String account) {
        PageHelper.startPage(page, size);
        List<Machine> machineList = machineService.selectByAccount(account);
        PageInfo pageInfo = new PageInfo(machineList);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据条件查询机器及其相关记录信息。
     *
     * @param page
     * @param size
     * @param nameplate
     * @param orderNum
     * @param machineType
     * @param agent                     不同代理商可以传入不同名字，代理商只看到自己的数据。
     * @param status
     * @param customerName              下面都是查询实际发生的时间/日期
     * @param query_start_time_install
     * @param query_finish_time_install
     * @param isAgent
     * @return
     */
    @PostMapping("/getSaledMachineInfoList")
    public Result getSaledMachineInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                          String nameplate,
                                          String orderNum,
                                          String machineType,
                                          String agent,
                                          String status,
                                          String customerName,
                                          String query_start_time_install,
                                          String query_finish_time_install,
                                          String machineWhereFrom,
//                                          String installChargePerson,
                                          boolean isAgent,
                                          boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<MachineInfo> list = machineService.getSaledMachineInfoList(nameplate,
                orderNum,
                machineType,
                agent,
                status,
                customerName,
                query_start_time_install,
                query_finish_time_install,
                machineWhereFrom,
//                installChargePerson,
                isAgent,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据铭牌号返回机器的
     * 基本信息，
     * 安装信息：时间，人员，状态
     * 保养信息：时间，人员，状态
     * 维修信息：时间，人员，状态
     */
    @PostMapping("/selectBaseRecordByNameplate")
    public Result selectBaseRecordByNameplate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                              @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<MachineBaseRecordInfo> list = machineService.selectBaseRecordByNameplate(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 客户端的要获取的待处理机器列表
     * 获取该客户的安装、保养、维修、配件寄回等任务信息（包括机器信息+任务信息）
     */
    @PostMapping("/selectBaseRecordByUser")
    public Result selectBaseRecordByUser(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<MachineBaseRecordInfo> list = machineService.selectBaseRecordByUser(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     *  根据铭牌号查询机器
     *  （考虑要到效率所以不使用 getSaledMachineInfoList，暂时未用到，先保留着）
     */
    @PostMapping("/selectMachineByNameplate")
    public Result selectMachineByNameplate(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<Machine> list = machineService.selectMachineByNameplate(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
