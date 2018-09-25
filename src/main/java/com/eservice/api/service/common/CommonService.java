package com.eservice.api.service.common;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
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


    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url 发送请求的 URL
     * @return 所代表远程资源的响应结果
     */
    public String sendPost(String url, Map<String, ?> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();
        String message = null;

        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }

        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
            message = "sendPost error, " + e.getMessage();
            return  message;
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

}
