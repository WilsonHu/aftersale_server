package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.machine.Machine;
import com.eservice.api.model.machine.MachineInProcessDb;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/03.
*/
@RestController
@RequestMapping("/SinsimProcessDB")
public class SinsimProcessDBController {

    @Autowired
    @Qualifier("DataSourceSinsimProcessDbTemplate")
    private JdbcTemplate dataSourceSinsimProcessDbTemplate;

    @RequestMapping(value = "/getMachineList")
    public Result getMachineList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {

        PageHelper.startPage(page, size);
        String query = " select * from  machine";
        List<MachineInProcessDb> list = dataSourceSinsimProcessDbTemplate.query(query, new BeanPropertyRowMapper(MachineInProcessDb.class));;
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);

    }
}
