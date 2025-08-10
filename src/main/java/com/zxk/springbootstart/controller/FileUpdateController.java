package com.zxk.springbootstart.controller;

import com.zxk.springbootstart.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
//未实现
@RestController
public class FileUpdateController {
    @PostMapping("/upload")
    public Result<String> upload(String nickname, MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println(nickname);
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getContentType());
        String path = request.getServletContext().getRealPath("/upload/");
        System.out.println(path);
        saveFile(file,path);
        return Result.success();
    }
    public void saveFile(MultipartFile file,String path)throws IOException{
        File dir =new File(path);
        if(!dir.exists()){
            dir.mkdir();
        }
        File file1 =new File(path+file.getOriginalFilename());
        file.transferTo(file1);
    }
}
