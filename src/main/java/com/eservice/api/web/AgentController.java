package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.agent.Agent;
import com.eservice.api.service.AgentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/agent")
public class AgentController {
    @Resource
    private AgentService agentService;

    @PostMapping("/add")
    public Result add(@RequestBody @NotNull Agent agent) {
        agent.setCreateTime(new Date());
        agentService.save(agent);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        agentService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull Agent agent) {
        agentService.update(agent);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
        Agent agent = agentService.findById(id);
        return ResultGenerator.genSuccessResult(agent);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Agent> list = agentService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
