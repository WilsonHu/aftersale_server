package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.parts_info.PartsAllInfo;
import com.eservice.api.model.parts_info.PartsInfo;
import com.eservice.api.model.parts_info.PartsInfoWithRepairRecordInfo;
import com.eservice.api.service.common.CommonService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.PartsInfoServiceImpl;
import com.eservice.api.service.impl.RepairRecordServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/parts/info")
public class PartsInfoController {
    @Resource
    private PartsInfoServiceImpl partsInfoService;

    @Resource
    private CommonService commonService;

    @Resource
    private RepairRecordServiceImpl repairRecordService;

    @Value("${parts_info_img_dir}")
    private String partsInfoImgDir;

    /**
     * 配件信息 是在repair_actual_info.add维修员提交记录时一并创建，客户在提交配件回厂信息时更新图片和快递单号。
     */
//    @PostMapping("/add")
//    public Result add(@RequestBody @NotNull PartsInfo partsInfo) {
//        partsInfoService.save(partsInfo);
//        return ResultGenerator.genSuccessResult();
//    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        partsInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 客户在提交配件回厂信息时更新图片和快递单号。
     */
    @PostMapping("/update")
    public Result update(@RequestParam String partsInfo,
                         @RequestParam MultipartFile file ) {
        String message = null;
        String fileNameWithPath = null;
        String nameplate = null;
        PartsInfo partsInfo1 = null;
        try {
            File dir = new File(partsInfoImgDir);
            if (!dir.exists()) {
                dir.mkdir();
            }

            partsInfo1 = JSON.parseObject(partsInfo, PartsInfo.class);
            if (partsInfo1 != null) {
                nameplate = repairRecordService.selectRepairRecordByPartsInfoId(partsInfo1.getId().toString()).getMachineNameplate();
                fileNameWithPath = commonService.saveFile(partsInfoImgDir, file, nameplate, Constant.FILE_TYPE_PARTS_SENDBACK_IMAGE, 0);
                if (fileNameWithPath != null) {
                    partsInfo1.setSendbackTrackingPictrue(fileNameWithPath);
                } else {
                    message = "failed to save file sendbackTrackingPictrue, no records saved";
                    throw new RuntimeException();
                }
                partsInfoService.update(partsInfo1);
            }
        } catch (Exception ex) {
            return ResultGenerator.genFailResult(ex.getMessage() +"," + message);
        }
        /**
         * 把更新成功的配件信息返回
         */
        return ResultGenerator.genSuccessResult(partsInfo1);
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        PartsInfo partsInfo = partsInfoService.findById(id);
        return ResultGenerator.genSuccessResult(partsInfo);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<PartsInfo> list = partsInfoService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据 repair_record_id 查询 配件信息
     * （RepairRecordInfo中已不包含配件信息）
     */
    @PostMapping("/getPartsInfoByRepairRecordId")
    public Result getPartsInfoByRepairRecordId(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                               @RequestParam Integer repairRecordId) {
        PageHelper.startPage(page, size);
        List<PartsAllInfo> list = partsInfoService.getPartsInfoByRepairRecordId(repairRecordId);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     *  配件信息的查询方法：
     *
     * @param nameplate 配件归属的机器的铭牌号
     * @param partsName 配件名称
     * @param customerNameInMachine 配件归属的机器的客户名称
     * @param repairChargePersonName 配件维修的负责人
     * @param supplier 配件的供应商
     * @param partsStatus 配件状态  1: 无需回寄，其他表示需要寄回(具体 2：未寄回，3：已寄回（待确认），4：已确认)
     * @param sendbackTrackingNumber 寄回配件的快递单号
     * @param sendbackConfirmedPerson 配件寄回后信胜确认收到的确认人
     *                                
     * 注: 时间查询的输入格式应该为类似"2018-08-01 17:26:10",其他地方的时间查询也一样。
     */
    @PostMapping("/getPartsInfoList")
    public Result getPartsInfoList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                   String nameplate,
                                   String partsName,
                                   String customerNameInMachine,
                                   String repairChargePersonName,
                                   String queryStartSendbackConfirmedTime,
                                   String queryFinishSendbackConfirmedTime,
                                   String supplier,
                                   String partsStatus,
                                   String sendbackTrackingNumber,
                                   String sendbackConfirmedPerson,
                                   boolean isFuzzy) {
        PageHelper.startPage(page, size);
        List<PartsAllInfo> list = partsInfoService.getPartsInfoList(
                nameplate,
                partsName,
                customerNameInMachine,
                repairChargePersonName,
                queryStartSendbackConfirmedTime,
                queryFinishSendbackConfirmedTime,
                supplier,
                partsStatus,
                sendbackTrackingNumber,
                sendbackConfirmedPerson,
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户名查询该用户负责的维修的待寄回（包括已寄回还等待信胜确认）的配件及相关维修信息
     * userName：客户方的维修联系人名字
     */
    @PostMapping("/getPartsInfoTaskByUserName")
    public Result getPartsInfoTaskByUserName(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                         @RequestParam String userName) {
        PageHelper.startPage(page, size);
        List<PartsInfoWithRepairRecordInfo> list = partsInfoService.getPartsInfoTaskByUserName(userName);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

}
