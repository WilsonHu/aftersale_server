package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.parts_info.PartsInfo;
import com.eservice.api.model.repair_actual_info.RepairActualInfo;
import com.eservice.api.model.repair_record.RepairRecord;
import com.eservice.api.service.PartsInfoService;
import com.eservice.api.service.common.Constant;
import com.eservice.api.service.impl.RepairActualInfoServiceImpl;
import com.eservice.api.service.impl.RepairRecordServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
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
    PartsInfoService partsInfoService;
    @Resource
    private RepairRecordServiceImpl repairRecordService;

    /**
     * 在上传（新增）实际维修情况时，也同时上传（新增）了要寄回的配件，并更新record状态
     * partsInfoList: 配件信息，可以多个配件
     * repairResult: 维修结果 4(Constant.REPAIR_STATUS_REPAIR_NG)表示NG,7(Constant.REPAIR_STATUS_REPAIR_OK)表示OK
     */
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/add")
    public Result add(@RequestParam String repairActualInfo,
                      @RequestParam List<String> partsInfoList,
                      @RequestParam String repairResult) {
        String message = null;
        try {
            RepairActualInfo repairActualInfo1 = JSON.parseObject(repairActualInfo, RepairActualInfo.class);
            if( null == repairActualInfo1) {
                message = " repairActualInfo解析出错！";
                throw new RuntimeException();
            }
            repairActualInfoService.saveAndGetID(repairActualInfo1);
            PartsInfo partsInfo1 = null;
            for(int i=0; i<partsInfoList.size(); i++){
                partsInfo1 = JSON.parseObject( partsInfoList.get(i), PartsInfo.class);
                if( null == partsInfo1) {
                    message = " partsInfo 解析出错！" ;
                    throw new RuntimeException();
                }
                partsInfo1.setRepairActualInfoId(repairActualInfo1.getId());
                partsInfoService.save(partsInfo1);
            }

            if(repairResult.equals(Constant.REPAIR_STATUS_REPAIR_NG) || repairResult.equals(Constant.REPAIR_STATUS_REPAIR_OK)){
                RepairRecord repairRecord = repairRecordService.findById(repairActualInfo1.getRepairRecordId());
                repairRecord.setStatus(repairResult);
                repairRecordService.update(repairRecord);
            } else {
                message = " repairResult 值不对！";
                throw new RuntimeException();
            }
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("添加实际维修信息出错！" + message + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        repairActualInfoService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull RepairActualInfo repairActualInfo) {
        repairActualInfoService.update(repairActualInfo);
        return ResultGenerator.genSuccessResult();
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
