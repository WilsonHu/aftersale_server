package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.knowledge_lib.KnowledgeLib;
import com.eservice.api.service.KnowledgeLibService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/07/10.
*/
@RestController
@RequestMapping("/knowledge/lib")
public class KnowledgeLibController {
    @Resource
    private KnowledgeLibService knowledgeLibService;

    @PostMapping("/add")
    public Result add(KnowledgeLib knowledgeLib) {
        knowledgeLibService.save(knowledgeLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        knowledgeLibService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(KnowledgeLib knowledgeLib) {
        knowledgeLibService.update(knowledgeLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        KnowledgeLib knowledgeLib = knowledgeLibService.findById(id);
        return ResultGenerator.genSuccessResult(knowledgeLib);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<KnowledgeLib> list = knowledgeLibService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
