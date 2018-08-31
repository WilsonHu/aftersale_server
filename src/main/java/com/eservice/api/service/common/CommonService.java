package com.eservice.api.service.common;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class Description:通用服务类
 *
 * @author Wilson Hu
 * @date 22/12/2017
 */
@Service
public class CommonService {
    Logger logger = Logger.getLogger(CommonService.class);


    /**
     * @param path      保存文件的总路径
     * @param file      文件
     * @param nameplate 机器的铭牌号
     * @param fileType 文件类型
     * @param number    表示第几个文件
     * @return 文件路径
     * eg: mph333_REPAIR_REQUEST_IMAGE_2018-07-13-16-17-50_1.png
     * TODO:保存的文件，名称多了一个空格待查
     */
    public String saveFile(String path,
                           MultipartFile file,
                           @RequestParam(defaultValue = "") String nameplate,
                           @RequestParam(defaultValue = "") String fileType,
                           @RequestParam(defaultValue = "0") int number) throws IOException {
        String targetFileName = null;
        try {
            if (path != null && !file.isEmpty()) {

                String fileName = file.getOriginalFilename();
                targetFileName = path + formatFileName(fileName.replaceAll("/", "-"), nameplate.replaceAll("/", "-"), fileType, number);
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(new File(targetFileName)));
                out.write(file.getBytes());
                out.flush();
                out.close();
                logger.info("====CommonService.saveFile(): success ========" + targetFileName);
            }
        } catch (IOException e) {
            throw e;
        }
        return targetFileName;
    }

    public String formatFileName(
                                 String fileName,
                                 @RequestParam(defaultValue = "") String nameplate,
                                 @RequestParam(defaultValue = "") String fileType,
                                 @RequestParam(defaultValue = "0") int number) {
        String targetFileName = "";
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        //指定北京时间
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        String dateStr = formatter.format(date);

        targetFileName = nameplate + "_" + fileType + "_" + dateStr + "_" + number + suffixName;
        return targetFileName;
    }

}
