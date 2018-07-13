package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.repair_request_info.RepairRequestInfo;
import com.eservice.api.service.RepairRequestInfoService;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@RestController
@RequestMapping("/repair/request/info")
public class RepairRequestInfoController {
    @Resource
    private RepairRequestInfoService repairRequestInfoService;

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
     * 传入文件需要包含对应的名称（"REPAIR_REQUEST_VOICE"等），用于区分不同文件类型放入不同目录,否则不会保存文件。
     * @param repairRequestInfo
     * @param files
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestParam String  repairRequestInfo,
                      @RequestParam MultipartFile[] files) {
            RepairRequestInfo repairRequestInfo1 = JSON.parseObject(repairRequestInfo,RepairRequestInfo.class);

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
            String nameplate = repairRequestInfo1.getNameplate();
            if (nameplate == null){
                return ResultGenerator.genFailResult("Error: no machine found by the abnormalRecordId, no records saved");
            }

            String fileNameWithPath = null;
            //
            List<String> listResultPathRequestVoice = new ArrayList<>() ;
            List<String> listResultPathRequestImage = new ArrayList<>() ;
            List<String> listResultPathRequestNameplateImage = new ArrayList<>() ;
            for(int i=0; i<files.length; i++) {
                try {
                    //报修语音:
                    if(files[i].getOriginalFilename().contains(Constant.REPAIR_REQUEST_VOICE)){
                        fileNameWithPath = commonService.saveFile(repairReqVoiceDir, files[i], nameplate, i );
                        if(fileNameWithPath != null){
                            listResultPathRequestVoice.add(fileNameWithPath);
                        } else {
                            return ResultGenerator.genFailResult("failed to save file, no records saved" + Constant.REPAIR_REQUEST_VOICE);
                        }
                    } //报修图片:
                    else if(files[i].getOriginalFilename().contains(Constant.REPAIR_REQUEST_IMAGE)){
                        fileNameWithPath = commonService.saveFile(repaiReqImgDir, files[i], nameplate, i );
                        if(fileNameWithPath != null){
                            listResultPathRequestImage.add(fileNameWithPath);
                        } else {
                            return ResultGenerator.genFailResult("failed to save file, no records saved" + Constant.REPAIR_REQUEST_IMAGE);
                        }
                    } //报修铭牌号图片:
                    else if(files[i].getOriginalFilename().contains(Constant.REPAIR_REQUEST_NAMEPLATE_IMAGE)){
                        fileNameWithPath = commonService.saveFile(repairReqNameplateImgDir, files[i], nameplate, i );
                        if(fileNameWithPath != null){
                            listResultPathRequestNameplateImage.add(fileNameWithPath);
                        } else {
                            return ResultGenerator.genFailResult("failed to save file, no records saved" + Constant.REPAIR_REQUEST_NAMEPLATE_IMAGE);
                        }
                    } else {
                        //图片名称不对。
                        System.out.println("======== WRONG file name :  " + files[i].getOriginalFilename() );
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            repairRequestInfo1.setVoice(listResultPathRequestVoice.toString());
            repairRequestInfo1.setPictures(listResultPathRequestImage.toString());
            repairRequestInfo1.setNameplatePicture(listResultPathRequestNameplateImage.toString());
            repairRequestInfoService.save(repairRequestInfo1);

            return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairRequestInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(RepairRequestInfo repairRequestInfo) {
        repairRequestInfoService.update(repairRequestInfo);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
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
