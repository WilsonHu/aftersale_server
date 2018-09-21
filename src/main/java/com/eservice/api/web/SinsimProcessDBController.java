package com.eservice.api.web;

import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.MachineInfo;
import com.eservice.api.model.machine.MachineInfosInProcessDb;
import com.eservice.api.model.machine.MachineType;
import com.eservice.api.service.impl.MachineServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @PostMapping("/getMachineList")
    public Result getMachineList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size, String orderNum, String nameplate, boolean isFuzzy) {

        //PageHelper.startPage(page, size);
        String query = " select m.*,mo.needle_num,mo.head_num,mo.head_distance,mo.x_distance,mo.y_distance,mo.order_num, mt.name as machine_type_name,c.contract_num,c.customer_name" +
                " from  machine m " +
                "left join machine_order mo on mo.id=m.order_id " +
                "left join machine_type mt on mt.id=m.machine_type " +
                "left join contract c on c.id = mo.contract_id" +
                " where m.status='" + com.eservice.api.service.common.Constant.MACHINE_INSTALLED + "' ";
        //需要查询原生产库中完成的机器
        String fuzzyFormat = isFuzzy ? "%" : "";
        String fuzzyKey = isFuzzy ? " like " : "=";
        if (orderNum != null && orderNum.length() > 0) {
            query += " AND mo.order_num " + fuzzyKey + " '" + fuzzyFormat + orderNum + fuzzyFormat + "'";
        }
        if (nameplate != null && nameplate.length() > 0) {
            query += " AND mo.nameplate " + fuzzyKey + " '" + fuzzyFormat + nameplate + fuzzyFormat + "'";
        }

        List<MachineInfosInProcessDb> list = dataSourceSinsimProcessDbTemplate.query(query, new BeanPropertyRowMapper(MachineInfosInProcessDb.class));
        if (!isFuzzy) {//查询需要和售后的机器做拆分比较
            for (MachineInfosInProcessDb item : list) {
                item.setStatus((byte) 0);
                item.setFacoryDate(null);
                List<MachineInfo> saledMachineList = machineService.getSaledMachineInfoList(item.getNameplate(),
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
                        "",
//                        "",
                         isFuzzy);
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
