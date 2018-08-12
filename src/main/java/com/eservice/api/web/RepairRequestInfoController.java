package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.model.repair_request_info.RepairRequestInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.eservice.api.service.impl.RepairRecordServiceImpl;
import com.eservice.api.service.impl.RepairRequestInfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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

    @Value("${Repair_req_nameplate_img}")
    private String repairReqNameplateImgDir;

    @Value("${Repair_req_voice}")
    private String repairReqVoiceDir;

    @Value("${Repair_req_img}")
    private String repaiReqImgDir;

    /**
     * 保存相关的铭牌号图片（可以为空），报修语音（可以为空），报修图片（可以为空） 。
     * 成功之后，增加报修记录.
     * @param repairRequestInfo
     * @param fileOfNameplate:铭牌号照片
     * @param fileOfVoice: 语音文件
     * @param filesOfRepairRequestPicture:报修图片
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@RequestParam String  repairRequestInfo,
                      @RequestParam MultipartFile fileOfNameplate,
                      @RequestParam MultipartFile fileOfVoice,
                      @RequestParam MultipartFile[] filesOfRepairRequestPicture) {
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
            RepairRequestInfo repairRequestInfo1 = JSON.parseObject(repairRequestInfo,RepairRequestInfo.class);
            String nameplate = repairRequestInfo1.getNameplate();
            if (nameplate == null){
                return ResultGenerator.genFailResult("Error: no nameplate found by the repairRequestInfo, no records saved");
            }
            String fileNameWithPath;
            String resultPathRequestNameplateImage = null;
            String resultPathRequestVoice = null;
            List<String> listResultPathRequestImage =  new ArrayList<>();

            try {
                //报修铭牌号图片:
                fileNameWithPath = commonService.saveFile(repairReqNameplateImgDir, fileOfNameplate, nameplate, Constant.FILE_TYPE_REPAIR_REQUEST_NAMEPLATE_IMAGE,0 );
                if(fileNameWithPath != null){
                    resultPathRequestNameplateImage = fileNameWithPath;
                } else {
                    return ResultGenerator.genFailResult("failed to save file fileOfNameplate, no records saved");
                }
                //报修语音:
                fileNameWithPath = commonService.saveFile(repairReqVoiceDir, fileOfVoice, nameplate, Constant.FILE_TYPE_REPAIR_REQUEST_VOICE, 0 );
                if(fileNameWithPath != null){
                    resultPathRequestVoice = fileNameWithPath;
                } else {
                    return ResultGenerator.genFailResult("failed to save file fileOfVoice, no records saved");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //报修图片
            for(int i=0; i<filesOfRepairRequestPicture.length; i++) {
                try {  //报修图片:
                    fileNameWithPath = commonService.saveFile(repaiReqImgDir, filesOfRepairRequestPicture[i], nameplate,Constant.FILE_TYPE_REPAIR_REQUEST_IMAGE, i );
                    if(fileNameWithPath != null){
                        listResultPathRequestImage.add(fileNameWithPath);
                    } else {
                        return ResultGenerator.genFailResult("failed to save file filesOfRepairRequestPicture, no records saved"  );
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            repairRequestInfo1.setVoice(resultPathRequestVoice);
            repairRequestInfo1.setPictures(listResultPathRequestImage.toString());
            repairRequestInfo1.setNameplatePicture(resultPathRequestNameplateImage);
            repairRequestInfoService.saveAndGetID(repairRequestInfo1);

            /**
             * 添加报修记录之后，要生成最基本的维修记录
             */
            RepairRecord repairRecord1 = new RepairRecord();
            repairRecord1.setCustomer(repairRequestInfo1.getCustomer());
            repairRecord1.setMachineNameplate(repairRequestInfo1.getNameplate());
            repairRecord1.setStatus(Constant.REPAIR_STATUS_UNSIGNED_TO_REPAIRER);
            repairRecord1.setRepairRequestInfo(repairRequestInfo1.getId());
            repairRecord1.setCreateTime(new Date());
            repairRecordService.save(repairRecord1);

            /**
             * machine 状态置为待修理
             */
            Machine machine = machineService.findBy("nameplate",repairRequestInfo1.getNameplate());
            if(machine != null) {
                machine.setStatus(Constant.MACHINE_STATUS_WAIT_FOR_REPAIR);
                machineService.update(machine);
            } else {
                return ResultGenerator.genFailResult("can not find machine by the nameplate: " + repairRequestInfo1.getNameplate());
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult(ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairRequestInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull RepairRequestInfo repairRequestInfo) {
        repairRequestInfoService.update(repairRequestInfo);
        return ResultGenerator.genSuccessResult();
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
}
