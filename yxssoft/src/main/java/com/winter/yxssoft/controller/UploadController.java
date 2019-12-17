package com.winter.yxssoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Controller
public class UploadController {
//    private final static String FILE_UPLOAD_PATH = "/home/project/upload";
    private final static String FILE_UPLOAD_PATH = "D:\\SourceControl\\Git\\angularStudy\\yxssoft\\upload\\";
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public String Upload(@RequestParam("file") MultipartFile file){
        if(file.isEmpty()){
            return "上传失败";
        }
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmss");
        Random r = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(sdf.format(new Date())).append(r.nextInt(100)).append(suffixName);
        String newFileName =tempName.toString();
        try{
            byte[] bytes = file.getBytes();
            Path path= Paths.get(FILE_UPLOAD_PATH + newFileName);
            Files.write(path,bytes);
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传成功，图片地址为：/files/" + newFileName;
    }
}
