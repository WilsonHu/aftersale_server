package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.repair_request_info.RepairRequestInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.model.user.UserInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.common.WxMessageTemplateJsonData;
import com.eservice.api.service.impl.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.Date;
import java.util.List;


/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/repair/request/info")
public class RepairRequestInfoController {
    @Resource
    private RepairRequestInfoServiceImpl repairRequestInfoService;

    @Resource
    private RepairRecordServiceImpl repairRecordService;

    @Resource
    private MachineServiceImpl machineService;

    @Resource
    private CommonService commonService;

    @Value("${repair_req_nameplate_img}")
    private String repairReqNameplateImgDir;

    @Value("${repair_req_voice}")
    private String repairReqVoiceDir;

    @Value("${repair_req_img}")
    private String repaiReqImgDir;

    @Value("${WX_TEMPLATE_5_REPAIR_TASK}")
    private String WX_TEMPLATE_5_REPAIR_TASK;

    @Value("${specialAccount1ToReceiveRepairRequest}")
    private String specialAccount1ToReceiveRepairRequest;

    @Value("${specialAccount2ToReceiveRepairRequest}")
    private String specialAccount2ToReceiveRepairRequest;

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    @Resource
    private UserServiceImpl userService;

    private Logger logger = Logger.getLogger(RepairRequestInfoController.class);

    /**
     * 因小程序端一次只能上传一个文件，所以各个文件只能分别单独上传（文件上传还要分类型，就不在add上传了，统一放update了）。
     * 报修时add生成简单的报修记录。
     * 每次新增报修时，都查一下报修的铭牌号，如果有有报修记录未完成，则删除该报修记录及对应的状态为报修中的维修记录，才新建报修记录。
     * （文件则在update里上传，如果上传文件失败，小程序端用户再次点击提交报修只调update，不调add）
     *  isOldMachine: true表示是老机器，否则是新机器（库里存在记录）
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@RequestParam String repairRequestInfo,@RequestParam String isOldMachine) {

        RepairRequestInfo repairRequestInfo1 = JSON.parseObject(repairRequestInfo, RepairRequestInfo.class);
        repairRequestInfo1.setCreateTime(new Date());
        if(repairRequestInfo1.getNameplate() == null || repairRequestInfo1.getNameplate().equals("")){
            return ResultGenerator.genFailResult("铭牌不能为空");
        }

        if(repairRequestInfo1.getCustomer() == null || repairRequestInfo1.getCustomer().equals("")){
            return ResultGenerator.genFailResult("客户不能为空");
        }

        /**
         * 如果该铭牌号有进行中的报修（当作报修失败）则删除该报修记录，然后全新增加记录。
         */
        List<RepairRequestInfo> list = repairRequestInfoService.getRecordsInRequesting(repairRequestInfo1.getNameplate());
        if (list != null) {
            for (RepairRequestInfo item : list) {

                /**
                 * 先删除该报修对应的repair_record（处于REPAIR_STATUS_IN_REQUESTING状态）
                 */
                List<RepairRecord> repairRecordList = repairRecordService.selectRepairRecordByRepairRequestId(item.getId().toString());
                for (RepairRecord itemOfRepairRecord : repairRecordList) {
                    if (itemOfRepairRecord.getStatus().equals(Constant.REPAIR_STATUS_IN_REQUESTING)) {
                        repairRecordService.deleteById(itemOfRepairRecord.getId());
                        System.out.println("repairRecord " + itemOfRepairRecord.getId() + " was deleted!");
                    } else {
                        System.out.println("repairRecord " + itemOfRepairRecord.getId() + " status is not in requesting!");
                    }
                }

                /**
                 * 再删除报修记录
                 */
                repairRequestInfoService.deleteById(item.getId());
            }
        }
        try {
            repairRequestInfoService.saveAndGetID(repairRequestInfo1);

            /**
             * 添加报修记录之后，生成最基本的维修记录
             */
            RepairRecord repairRecord1 = new RepairRecord();
            repairRecord1.setCustomer(repairRequestInfo1.getCustomer());
            repairRecord1.setMachineNameplate(repairRequestInfo1.getNameplate());
            /**
             * 状态为报修进行中
             */
            repairRecord1.setStatus(Constant.REPAIR_STATUS_IN_REQUESTING);
            repairRecord1.setRepairRequestInfo(repairRequestInfo1.getId());
            repairRecord1.setCreateTime(new Date());

            /**
             * 老机器报修时 需要建立老机器信息
             */
            if (isOldMachine.equalsIgnoreCase("true")) {
                /**
                 * 确认该铭牌号不存在
                 */
                if (machineService.findBy("nameplate", repairRequestInfo1.getNameplate()) != null) {
                    return ResultGenerator.genFailResult("该机器已经存在！");
                }

                Machine machine = new Machine();
                machine.setNameplate(repairRequestInfo1.getNameplate());
                machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_CHECK);
                machine.setOldMachineCheck(Constant.OLD_MACHINE_CHECK_UNKNOWN);
                machine.setIsOldMachine(Constant.MACHINE_TYPE_OLD);
                /**
                 * 目前是谁报修就把机器挂着谁名下。
                 */
                machine.setCustomer(repairRequestInfo1.getCustomer());
                machineService.save(machine);
                logger.info("add old machine " + repairRequestInfo1.getNameplate());
            } else {
                logger.info("add machine" + repairRequestInfo1.getNameplate());
                List<Machine> machineList = machineService.selectMachineByNameplate(repairRequestInfo1.getNameplate());
                if(machineList == null){
                    return ResultGenerator.genFailResult("根据该铭牌号找不到机器");
                } else {
                    // 一个铭牌号应该只有一个机器
                    Machine machine = machineList.get(0);
                    machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_REPAIR);
                    machineService.update(machine);
                }


            }
            repairRecordService.save(repairRecord1);

            /**
             * 收到报修时, 发送消息给管理员等
             */
            User customerReuested = userService.findById(repairRequestInfo1.getCustomer());
            Machine machine = machineService.findBy("nameplate", repairRequestInfo1.getNameplate());
            WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
            JSONObject jsonObjectDeatailMsg = new JSONObject();
//            {{first.DATA}}
//            服务单号：{{keyword1.DATA}}
//            客户信息：{{keyword2.DATA}}
//            机器信息：{{keyword3.DATA}}
//            {{remark.DATA}}
            wxMessageTemplateJsonData.setRepairRequestGot("收到报修");
            wxMessageTemplateJsonData.setMachineNameplate("机器编号" + repairRequestInfo1.getNameplate());
            if(customerReuested !=null) {
                wxMessageTemplateJsonData.setCustomerName(customerReuested.getName());
            }
            if(machine != null) {
                wxMessageTemplateJsonData.setMachineType(machine.getMachineType());
            }
            wxMessageTemplateJsonData.setRepairRequestBornMessage(repairRequestInfo1.getRepairTitle());

            List<UserInfo> userList = userService.getUsersByType("2",null,null);
            for (UserInfo u:userList) {
                wechatUserInfoService.sendMsgTemplate(u.getAccount(),
                        WX_TEMPLATE_5_REPAIR_TASK,
                        Constant.WX_MSG_11_REPAIR_REQUEST_TO_ADMIN,
                        JSONObject.toJSONString(wxMessageTemplateJsonData));
            }
            User msgReceiver1 = userService.selectByAccount(specialAccount1ToReceiveRepairRequest);
            User msgReceiver2 = userService.selectByAccount(specialAccount2ToReceiveRepairRequest);
            wechatUserInfoService.sendMsgTemplate(msgReceiver1.getAccount(),
                    WX_TEMPLATE_5_REPAIR_TASK,
                    Constant.WX_MSG_11_REPAIR_REQUEST_TO_ADMIN,
                    JSONObject.toJSONString(wxMessageTemplateJsonData));

            wechatUserInfoService.sendMsgTemplate(msgReceiver2.getAccount(),
                    WX_TEMPLATE_5_REPAIR_TASK,
                    Constant.WX_MSG_11_REPAIR_REQUEST_TO_ADMIN,
                    JSONObject.toJSONString(wxMessageTemplateJsonData));
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("添加报修信息出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult(repairRequestInfo1.getId());
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairRequestInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 报修时生成简单的记录，然后在update里把文件一一上传。
     *
     * @param repairRequestInfo ：注意上传的数据要正确，比如每次更新时，原先有的数据不要丢掉，因为这里是更新不是add。
     * @param fileType:上传的文件类型（铭牌号图片，报修语音，报修图片）
     * @param file:上传的单个文件
     * @param fileNumber:在上传3张报修图片时，用于区分是第几个图片，其他文件可以默认0。
     *
     * 2020-0726更新：采用一键报修之后，不需要上传各种文件了。
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/update")
    public Result update(@RequestParam String repairRequestInfo,
                         @RequestParam String fileType,
                         @RequestParam MultipartFile file,
                         @RequestParam(defaultValue = "0")Integer fileNumber) {
        String message = null;
        RepairRequestInfo repairRequestInfo1 = null;
        try {
            File dir = new File(repairReqNameplateImgDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            dir = new File(repairReqVoiceDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            dir = new File(repaiReqImgDir);
            if(!dir.exists()){
                dir.mkdir();
            }
            repairRequestInfo1 = JSON.parseObject(repairRequestInfo,RepairRequestInfo.class);
            if(Integer.parseInt(repairRequestInfo1.getAlreadyUploadedFilesNumber()) >= Integer.parseInt(repairRequestInfo1.getUploadFilesAmount())){
                message = "已上传文件数>= 应上传文件总数";
                throw new RuntimeException();
            }
            String nameplate = repairRequestInfo1.getNameplate();
            if (nameplate == null){
                message = "Error: no nameplate found by the repairRequestInfo, no records saved！";
                throw new RuntimeException();
            }
            if( repairRequestInfoService.findById(repairRequestInfo1.getId()) == null){
                message = "传入的报修信息实际不存在！";
                throw new RuntimeException();
            }

            String fileNameWithPath;
            String resultPathRequestNameplateImage = null;
            String resultPathRequestVoice = null;
            String resultPathRequestImage =  null;
            if(fileType.equals(Constant.FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE)){
                //报修铭牌号图片:
                fileNameWithPath = commonService.saveFile(repairReqNameplateImgDir, file, nameplate, Constant.FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE,0 );
                if(fileNameWithPath != null){
                    resultPathRequestNameplateImage = fileNameWithPath;
                    repairRequestInfo1.setNameplatePicture(resultPathRequestNameplateImage);
                } else {
                    message = "failed to save file fileOfNameplate, no records saved";
                    throw new RuntimeException();
                }
            } else if(fileType.equals(Constant.FILE_TYPE_REPAIR_REQUEST_VOICE)){
                //报修语音:
                fileNameWithPath = commonService.saveFile(repairReqVoiceDir, file, nameplate, Constant.FILE_TYPE_REPAIR_REQUEST_VOICE, 0 );
                if(fileNameWithPath != null){
                    resultPathRequestVoice = fileNameWithPath;
                    repairRequestInfo1.setVoice(resultPathRequestVoice);
                } else {
                    message = "failed to save file fileOfVoice, no records saved";
                    throw new RuntimeException();
                }

            }else if(fileType.equals(Constant.FILE_TYPE_REPAIR_REQUEST_IMAGE)){
                //报修图片:
                fileNameWithPath = commonService.saveFile(repaiReqImgDir, file, nameplate,Constant.FILE_TYPE_REPAIR_REQUEST_IMAGE, fileNumber );
                if(fileNameWithPath != null){
                    resultPathRequestImage = fileNameWithPath;
                    /**
                     * 多张报修图片
                     */
                    String oldPicturesPath = repairRequestInfo1.getPictures();
                    if(oldPicturesPath.isEmpty()){
                        repairRequestInfo1.setPictures(resultPathRequestImage);
                    } else {
                        repairRequestInfo1.setPictures(oldPicturesPath +"," + resultPathRequestImage);
                    }
                } else {
                    message = "failed to save file filesOfRepairRequestPicture, no records saved";
                    throw new RuntimeException();
                }
            } else {
                message = "未知的文件类型";
                throw new RuntimeException();
            }

            /**
             * 已上传的文件数增一
             */
            Integer oldAlreadyUploadedFilesNumber;
            Integer newAlreadyUploadedFilesNumber;
            oldAlreadyUploadedFilesNumber = Integer.parseInt(repairRequestInfo1.getAlreadyUploadedFilesNumber());
            newAlreadyUploadedFilesNumber = oldAlreadyUploadedFilesNumber + 1;
            repairRequestInfo1.setAlreadyUploadedFilesNumber(newAlreadyUploadedFilesNumber.toString());
            repairRequestInfoService.update(repairRequestInfo1);

            /**
             * 如果文件上传全部完成，更新record状态为待分配
             */
            if(repairRequestInfo1.getAlreadyUploadedFilesNumber().equals(repairRequestInfo1.getUploadFilesAmount())){
                RepairRecord repairRecord1 = repairRecordService.selectRepairRecordInRequesting(repairRequestInfo1.getId().toString());
                if(repairRecord1 != null){
                    repairRecord1.setStatus(Constant.REPAIR_STATUS_UNSIGNED_TO_REPAIRER);
                } else {
                    message = "找不到该进行中的报修记录";
                    throw new RuntimeException();
                }
                repairRecordService.update(repairRecord1);
            }

            /**
             * 如果文件上传全部完成，更新machine状态为待修理
             */
            if(repairRequestInfo1.getAlreadyUploadedFilesNumber().equals(repairRequestInfo1.getUploadFilesAmount())){
                Machine machine = machineService.findBy("nameplate",repairRequestInfo1.getNameplate());
                if(machine != null) {
                    machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_REPAIR);
                    machineService.update(machine);
                } else {
                    message = "can not find machine by the nameplate: " + repairRequestInfo1.getNameplate();
                    throw new RuntimeException();
                }
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult(ex.getMessage() +"," + message);
        }
        return ResultGenerator.genSuccessResult(repairRequestInfo1);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        RepairRequestInfo repairRequestInfo = repairRequestInfoService.findById(id);
        return ResultGenerator.genSuccessResult(repairRequestInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RepairRequestInfo> list = repairRequestInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据铭牌号查询该机器进行中的报修记录(比如报修时文件上传失败后的记录)
     */
    @PostMapping("/getRecordsInRequesting")
    public Result getRecordsInRequesting(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String nameplate) {
        PageHelper.startPage(page, size);
        List<RepairRequestInfo> list = repairRequestInfoService.getRecordsInRequesting(nameplate);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
