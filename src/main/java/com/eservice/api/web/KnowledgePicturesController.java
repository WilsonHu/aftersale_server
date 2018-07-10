package com.eservice.api.web;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.knowledge_pictures.KnowledgePictures;
import com.eservice.api.service.KnowledgePicturesService;
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
@RequestMapping("/knowledge/pictures")
public class KnowledgePicturesController {
    @Resource
    private KnowledgePicturesService knowledgePicturesService;

    @PostMapping("/add")
    public Result add(KnowledgePictures knowledgePictures) {
        knowledgePicturesService.save(knowledgePictures);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        knowledgePicturesService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(KnowledgePictures knowledgePictures) {
        knowledgePicturesService.update(knowledgePictures);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Integer id) {
        KnowledgePictures knowledgePictures = knowledgePicturesService.findById(id);
        return ResultGenerator.genSuccessResult(knowledgePictures);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<KnowledgePictures> list = knowledgePicturesService.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
