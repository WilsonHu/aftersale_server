package com.eservice.api.web;
import com.alibaba.fastjson.JSON;
import com.eservice.api.core.Result;
import com.eservice.api.core.ResultGenerator;
import com.eservice.api.model.knowledge_lib.KnowledgeLib;
import com.eservice.api.service.KnowledgeLibService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.util.List;

/**
* Class Description: xxx
* @author Wilson Hu
* @date 2018/08/04.
*/
@RestController
@RequestMapping("/knowledge/lib")
public class KnowledgeLibController {
    @Resource
    private KnowledgeLibService knowledgeLibService;

    /**
     * 添加知识库文章 到数据库，blob形式，即文章的所有内容，包括图片等都以二进制形式保存到数据库
     * @param knowledgeLib  --该条知识库文章的其他信息
     * @param mhtmlFile      --文章的来源文件,用mhtml格式保存web文件。
     * 数据库需要设置允许packet为较大值，比如20M.  show VARIABLES like '%max_allowed_packet%'; set global max_allowed_packet = 2*1024*1024*10
     */
    @PostMapping("/add")
    public Result add(String knowledgeLib, @RequestParam MultipartFile mhtmlFile) {
        KnowledgeLib knowledgeLib1 = JSON.parseObject(knowledgeLib,KnowledgeLib.class);
        if (knowledgeLib1 == null){
            return ResultGenerator.genFailResult(" knowledgeLib1 为空 ");
        }
        byte[] fileBytes = null;
        try{
            fileBytes= mhtmlFile.getBytes();
            knowledgeLib1.setHtml(fileBytes);
            knowledgeLibService.save(knowledgeLib1);
        }catch(Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("knowledgeLib add失败，"+ e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    /**
     *  该接口可以测试把 KnowledgeLib.html 另存为一个文件
     * @param knowledgeLibId
     */
    @PostMapping("/getHtmlFile")
    public Result getHtmlFile(@RequestParam Integer knowledgeLibId) {
        KnowledgeLib knowledgeLib1 = knowledgeLibService.findById(knowledgeLibId);
        if (knowledgeLib1 == null){
            return ResultGenerator.genFailResult(" knowledgeLib1 为空 ");
        }
        byte[] fileBytes = knowledgeLib1.getHtml();
        try{
            File file = new File("D://getFromKnowledgeHtmlBlob.mhtml");
            OutputStream outputStream=new FileOutputStream(file);
            outputStream.write(fileBytes);
            outputStream.close();
        }catch(Exception e){
            e.printStackTrace();
            return ResultGenerator.genFailResult("knowledgeLib getHtmlFile 失败，"+ e.getMessage());
        }
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Integer id) {
        knowledgeLibService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(@RequestBody @NotNull KnowledgeLib knowledgeLib) {
        knowledgeLibService.update(knowledgeLib);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam @NotNull Integer id) {
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
