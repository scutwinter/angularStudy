package com.winter.highlight_springmvc4.web.ch4_5;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class UploadController {
    @RequestMapping(value="/upload",method = RequestMethod.POST)
    //1 使用MultipartFile file 接受上传的文件
    public @ResponseBody String upload(MultipartFile file){
        try{
            // 使用FileUtils.writeByteArrayToFile写入文件到磁盘
            FileUtils.writeByteArrayToFile(new File("d:/upload/"+file.getOriginalFilename()),file.getBytes());
            return "ok";
        }catch (IOException e){
            e.printStackTrace();
            return "wrong";
        }
    }
}
