package com.eservice.api.web;

import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineInfo;
import com.eservice.api.model.machine.MachineInfosInProcessDb;
import com.eservice.api.model.machine.MachineType;
import com.eservice.api.service.common.PrepareUnAssignedMachineService;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class Description: xxx
 *
 * @author Wilson Hu
 * @date 2018/07/03.
 */
@RestController
@RequestMapping("/SinsimProcessDB")
public class SinsimProcessDBController {

    @Resource
    private MachineServiceImpl machineService;

    @Autowired
    @Qualifier("DataSourceSinsimProcessDbTemplate")
    private JdbcTemplate dataSourceSinsimProcessDbTemplate;

    @Resource
    private PrepareUnAssignedMachineService prepareUnAssignedMachineService;

    /**
     * 根据条件查询未绑定到客户的机器
     */
    @PostMapping("/getMachineList")
    public Result getMachineList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size,
                                 String orderNum, String nameplate, boolean isFuzzy) {
        List<MachineInfosInProcessDb> list = prepareUnAssignedMachineService.getInstalledNotBoundMachineList();
        List<MachineInfosInProcessDb>  tmpList1 = new ArrayList<>();
        List<MachineInfosInProcessDb>  tmpList = new ArrayList<>();
        ///根据铭牌号和订单号过滤
        if(orderNum != null && orderNum != "") {
            for (MachineInfosInProcessDb item : list) {
                if(item.getOrderNum().contains(orderNum)) {
                    tmpList1.add(item);
                }
            }
        } else {
            tmpList1.addAll(list);
        }
        if(nameplate != null && nameplate != "") {
            for (MachineInfosInProcessDb item : tmpList1) {
                if(item.getNameplate().contains(nameplate)) {
                    tmpList.add(item);
                }
            }
        } else {
            tmpList.addAll(tmpList1);
        }
        list = tmpList;
        //查询需要和售后的机器做拆分比较
        if (!isFuzzy) {
            for (MachineInfosInProcessDb item : list) {
                item.setStatus((byte) 0);
                item.setFacoryDate(null);
                List<MachineInfo> saledMachineList = machineService.getSaledMachineInfoList(item.getNameplate(),"","","","","",
                        "","","",false, isFuzzy);
                MachineInfo machineInfo = null;
                if (saledMachineList != null && saledMachineList.size() > 0) {
                    machineInfo = saledMachineList.get(0);
                }
                if (machineInfo != null) {
                    item.setStatus((byte) Integer.parseInt(machineInfo.getStatus()));
                    item.setCustomerName(machineInfo.getCustomerName().toString());
                    item.setAgent(machineInfo.getAgent());
                    item.setHasBinding(true);
                    item.setFacoryDate(machineInfo.getFacoryDate());

                }
            }
        }
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    /**
     * 返回生产部数据库的机器型号列表
     */
    @PostMapping("/getMachineTypeList")
    public Result getMachineTypeList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        String query = "select * from machine_type";
        List<MachineType> list = dataSourceSinsimProcessDbTemplate.query(query, new BeanPropertyRowMapper(MachineType.class));
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
