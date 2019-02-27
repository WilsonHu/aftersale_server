package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.parts_info.PartsAllInfo;
import com.eservice.api.model.parts_info.PartsInfo;
import com.eservice.api.model.repair_actual_info.RepairActualInfo;
import com.eservice.api.model.repair_actual_info.RepairActualInfoWithPartsInfo;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.user.User;
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
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/repair/actual/info")
public class RepairActualInfoController {
    @Resource
    private RepairActualInfoServiceImpl repairActualInfoService;

    @Resource
    PartsInfoServiceImpl partsInfoService;

    @Resource
    private RepairRecordServiceImpl repairRecordService;

    @Resource
    private MachineServiceImpl machineService;

    @Resource
    private CommonService commonService;

    @Value("${repair_actual_info_img_dir}")
    private String repairActualInfoImgDir;

    @Value("${WX_TEMPLATE_4_TASK_DONE}")
    private String WX_TEMPLATE_4_TASK_DONE;

    @Resource
    private WechatUserInfoServiceImpl wechatUserInfoService;

    @Resource
    private UserServiceImpl userService;

    private Logger logger = Logger.getLogger(RepairActualInfoController.class);
    /**
     * 在上传（新增）实际维修情况时，也同时上传（新增）了要寄回的配件， record状态则在update里文件传完后再更新。
     * repairActualInfo：实际维修的信息，其中图片信息可以空，在update时上传文件由后端填入。
     * partsInfoList: 配件信息，可以多个配件
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@RequestBody RepairActualInfoWithPartsInfo repairActualInfoWithPartsInfo){
        String message = null;
        RepairActualInfo repairActualInfo1 = repairActualInfoWithPartsInfo.getRepairActualInfo();
        PartsInfo partsInfo1 = null;
        List<PartsInfo> partsInfoList = repairActualInfoWithPartsInfo.getPartsInfoList();

        try {
            if( null == repairActualInfo1) {
                message = " repairActualInfo 获取失败！";
                throw new RuntimeException();
            }
            if( null == partsInfoList) {
                message = " partsInfoList 获取失败！";
                throw new RuntimeException();
            }

            /**
             * 如果有失败的情况，要把对应的配件信息删除，再删除该 repairActualInfo，
             * 然后全新增加repairActualInfo和配件信息。
             */
            List<RepairActualInfo> repairActualInfoInUpdating;
            repairActualInfoService.getRepairActualInfoInUpdating(repairActualInfo1.getRepairRecordId().toString());
            repairActualInfoInUpdating = repairActualInfoService.getRepairActualInfoInUpdating(repairActualInfo1.getRepairRecordId().toString());
            for(RepairActualInfo repairActualInfoInUpdatingX: repairActualInfoInUpdating ){
                if(repairActualInfoInUpdatingX != null){
                    /**
                     * repair_record和repair_actual_info一一对应，找到所有对应的配件信息并删除
                     */
                    List<PartsAllInfo> partsAllInfoList1 = partsInfoService.getPartsInfoByRepairRecordId(repairActualInfoInUpdatingX.getRepairRecordId());
                    for(PartsAllInfo partsAllInfoX: partsAllInfoList1){
                        partsInfoService.deleteById(partsAllInfoX.getId());
                        System.out.println("partsInfo " + partsAllInfoX.getId() + " was deleted!");
                    }

                    /**
                     * 再删除repairActualInfo自身
                     */
                    repairActualInfoService.deleteById(repairActualInfoInUpdatingX.getId());
                    System.out.println("repairActualInfo " + repairActualInfoInUpdatingX.getId() + " was deleted!");
                }
            }

            repairActualInfoService.saveAndGetID(repairActualInfo1);

            for(int i=0; i<partsInfoList.size(); i++){
                partsInfo1 = partsInfoList.get(i);
                if( null == partsInfo1) {
                    message = " partsInfo 出错！" ;
                    throw new RuntimeException();
                }
                partsInfo1.setRepairActualInfoId(repairActualInfo1.getId());
                partsInfoService.save(partsInfo1);
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("添加实际维修信息出错！" + message + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult(repairActualInfo1.getId());
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairActualInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 在update里把文件一一上传。
     *
     * @param repairActualInfo
     * @param file 要上传的单个文件
     * @param fileNumber 第几个文件
     * @param repairResult  维修结果 4(Constant.REPAIR_STATUS_REPAIR_NG)表示NG,7(Constant.REPAIR_STATUS_REPAIR_OK)表示OK
     * @return 返回更新后的 repairActualInfo
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/update")
    public Result update(@RequestParam String repairActualInfo,
                         @RequestParam MultipartFile file,
                         @RequestParam(defaultValue = "0")Integer fileNumber,
                         @RequestParam String repairResult) {
        String message = null;
        RepairActualInfo repairActualInfo1 = null;
        
        try {
            File dir = new File(repairActualInfoImgDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            repairActualInfo1 = JSON.parseObject(repairActualInfo, RepairActualInfo.class);
            if (Integer.parseInt(repairActualInfo1.getAlreadyUploadedFilesNumber()) >= Integer.parseInt(repairActualInfo1.getUploadFilesAmount())) {
                message = "Error: 已上传文件数>= 应上传文件总数";
                throw new RuntimeException();
            }
            String nameplate = repairRecordService.findById(repairActualInfo1.getRepairRecordId()).getMachineNameplate();
            if (nameplate == null) {
                message = "Error: no nameplate found by the repairActualInfo, no records saved！";
                throw new RuntimeException();
            }
            if (repairActualInfoService.findById(repairActualInfo1.getId()) == null) {
                message = "传入的实际维修信息不存在！";
                throw new RuntimeException();
            }

            String fileNameWithPath;
            String resultPathRepairActualImage = null;
            fileNameWithPath = commonService.saveFile(repairActualInfoImgDir, file, nameplate, Constant.FILE_TYPE_REPAIR_ACTUAL_INFO_IMAGE, fileNumber);
            if (fileNameWithPath != null) {
                resultPathRepairActualImage = fileNameWithPath;
                /**
                 * 多张维修图片
                 */
                String oldPicturesPath = repairActualInfo1.getAfterResolvePictures();
                if (oldPicturesPath == null || oldPicturesPath.isEmpty()) {
                    repairActualInfo1.setAfterResolvePictures(resultPathRepairActualImage);
                } else {
                    repairActualInfo1.setAfterResolvePictures(oldPicturesPath + "," + resultPathRepairActualImage);
                }
            } else {
                message = "failed to save file file of repairActualInfo, no records saved";
                throw new RuntimeException();
            }

            /**
             * 已上传的文件数增一
             */
            Integer oldAlreadyUploadedFilesNumber;
            Integer newAlreadyUploadedFilesNumber;
            oldAlreadyUploadedFilesNumber = Integer.parseInt(repairActualInfo1.getAlreadyUploadedFilesNumber());
            newAlreadyUploadedFilesNumber = oldAlreadyUploadedFilesNumber + 1;
            repairActualInfo1.setAlreadyUploadedFilesNumber(newAlreadyUploadedFilesNumber.toString());
            repairActualInfoService.update(repairActualInfo1);

            /**
             * 如果文件上传全部完成，更新record/machine
             */
            if (repairActualInfo1.getAlreadyUploadedFilesNumber().equals(repairActualInfo1.getUploadFilesAmount())) {
                RepairRecord repairRecord = null;
                if (repairResult.equals(Constant.REPAIR_STATUS_REPAIR_NG) || repairResult.equals(Constant.REPAIR_STATUS_REPAIR_OK)) {
                    repairRecord = repairRecordService.findById(repairActualInfo1.getRepairRecordId());
                    repairRecord.setStatus(repairResult);
                    repairRecord.setRepairEndTime(new Date());
                    repairRecordService.update(repairRecord);
                } else {
                    message = " repairResult 值不对！";
                    throw new RuntimeException();
                }
                repairRecordService.update(repairRecord);

                /**
                 * 更新machine状态为 待维修或正常工作状态
                 */
                Machine machine = machineService.findBy("nameplate", nameplate);
                if (machine != null) {
                    if (repairResult.equals(Constant.REPAIR_STATUS_REPAIR_NG)) {
                        machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_REPAIR);
                    } else if (repairResult.equals(Constant.REPAIR_STATUS_REPAIR_OK)) {
                        machine.setStatus(Constant.MACHINE_STATUS_IN_NORMAL);
                    }
                    machineService.update(machine);
                } else {
                    message = "can not find machine by the nameplate: " + nameplate;
                    throw new RuntimeException();
                }

                /**
                 * 维修完成（且选择成功），发送消息给用户
                 */
                RepairRecord repairRecordOld = repairRecordService.findById(repairRecord.getId());
                repairRecordService.update(repairRecord);
                repairRecord.setUpdateTime(new Date());

                //只有维修成功时才通知客户
                if (repairResult.equals(Constant.REPAIR_STATUS_REPAIR_OK)) {

                    User customer = userService.findById(repairRecordOld.getCustomer());
                    if (customer == null) {
                        logger.info("找不到对应的客户，请检查！ customerId： " + repairRecordOld.getCustomer());
                        return ResultGenerator.genFailResult("找不到对应的客户，请检查！");
                    }
                    User repairCharger = userService.findById(repairRecordOld.getRepairChargePerson());
                    if (repairCharger == null) {
                        logger.info("找不到对应的维修负责人，请检查！ getRepairChargePerson： " + repairRecordOld.getRepairChargePerson());
                        return ResultGenerator.genFailResult("找不到对应的维修负责人，请检查！");
                    }
                    WxMessageTemplateJsonData wxMessageTemplateJsonData = new WxMessageTemplateJsonData();
                    try {
                        //                {{first.DATA}}
                        //                任务名称：{{keyword1.DATA}}
                        //                负责人：{{keyword2.DATA}}
                        //                提交时间：{{keyword3.DATA}}
                        //                {{remark.DATA}}
                        wxMessageTemplateJsonData.setCustomerName(customer.getName());
                        wxMessageTemplateJsonData.setRepairTaskName("机器维修");
                        wxMessageTemplateJsonData.setRepairChargePerson(repairCharger.getName());
                        wxMessageTemplateJsonData.setRepairActualTime(repairRecordOld.getRepairPlanDate());
                        wxMessageTemplateJsonData.setRepairTaskDoneMessage("请知悉");
                        wechatUserInfoService.sendMsgTemplate(customer.getAccount(),
                                WX_TEMPLATE_4_TASK_DONE,
                                Constant.WX_MSG_10_REPAIR_DONE_TO_CUSTOMER,
                                JSONObject.toJSONString(wxMessageTemplateJsonData));
                    } catch (Exception e) {
                        logger.info("发送消息给客户失败 " + e.toString());
                        e.printStackTrace();
                    }
                } else {
                    logger.info("维修NG，不发送推送消息给客户");
                }
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult(ex.getMessage() +"," + message);
        }
        return ResultGenerator.genSuccessResult(repairActualInfo1);
    }

    /**
     * 根据 repairRecordId 查询该机器进行中的repairActualInfo记录(上传进行中或者失败了导致应上传的文件总数和已上传的文件数不相等)
     */
    @PostMapping("/getRepairActualInfoInUpdating")
    public Result getRepairActualInfoInUpdating(@RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String repairRecordId) {
        PageHelper.startPage(page, size);
        List<RepairActualInfo> list = repairActualInfoService.getRepairActualInfoInUpdating(repairRecordId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        RepairActualInfo repairActualInfo = repairActualInfoService.findById(id);
        return ResultGenerator.genSuccessResult(repairActualInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<RepairActualInfo> list = repairActualInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
