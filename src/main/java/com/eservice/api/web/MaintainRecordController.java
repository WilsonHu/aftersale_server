package com.eservice.api.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.maintain_lib.MaintainLib;
import com.eservice.api.model.maintain_members.MaintainMembers;
import com.eservice.api.model.maintain_record.MaintainRecord;
import com.eservice.api.model.maintain_record.MaintainRecordInfo;
import com.eservice.api.model.user.User;
import com.eservice.api.service.common.MaintainContentData;
import com.eservice.api.service.common.MaintainData;
import com.eservice.api.service.impl.MaintainLibServiceImpl;
import com.eservice.api.service.impl.MaintainMembersServiceImpl;
import com.eservice.api.service.impl.MaintainRecordServiceImpl;
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
        maintainRecord.setMaintainInfo(generateMaintainInfo(maintainRecord.getMaintainLibName()));
        maintainRecordService.save(maintainRecord);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        maintainRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull MaintainRecord maintainRecord) {
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
     * @param agent                      -- 代理商名称
     * @param maintainStatus
     * @param customerName               保养的客户联系人名称
     * @param machineType
     * @param maintainChargePerson       保养员负责人
     * @param query_start_time_maintain
     * @param query_finish_time_maintain
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
                isFuzzy);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 根据用户(无论是负责人还是保养成员)返回他的保养待处理 任务,如果用户为空则返回所有待处理的保养任务。
     * TODO: 确认是否重复数据问题 ---应该是假数据本身问题，待后面真实数据再确认一次。
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
            List<MaintainMembers> members = JSONObject.parseArray(maintainMembers, MaintainMembers.class);

            if (model == null || members == null || members.size() < 1) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return ResultGenerator.genFailResult("数据保存出错！");
            }
            model.setUpdateTime(new Date());
            model.setMaintainStatus(com.eservice.api.service.common.Constant.MAINTAIN_STATUS_ASSIGNED);
            maintainRecordService.update(model);
            List<User> list = maintainMembersService.getMembersByMaintainRecordId(model.getId().toString());
            for (User u : list) {
                for (MaintainMembers m : members) {
                    if (m.getUserId() == u.getId()) {
                        members.remove(m);
                        break;
                    }
                }
            }
            maintainMembersService.save(members);
        } catch (Exception ex) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultGenerator.genFailResult("数据保存出错！" + ex.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }
}
