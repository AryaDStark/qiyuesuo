package com.ntu.yangyu.web;


import com.ntu.yangyu.myResp.Resp;
import com.ntu.yangyu.service.FileService;
import com.ntu.yangyu.vo.FileQuery;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Controller
public class FileController {

    @Autowired
    FileService fileService;

    //上传文件
    @PostMapping("/upload")
    @ResponseBody
    public Resp<String> upload(@RequestParam("file")MultipartFile file){
        return fileService.upload(file);
    }
    
    //下载文件
    @GetMapping("/download")
    @ResponseBody
    public Resp<String> download(String UUID,HttpServletResponse response) throws IOException {

        if (fileService.getData(UUID)==null){return Resp.fail("410","UUID错误");}
        String path=fileService.findType(UUID);
        File file=new File(path);
        if (file.exists()) {
            IOUtils.copy(FileUtils.openInputStream(file), response.getOutputStream());
            return Resp.success("download success");
        }else {
            return Resp.fail("410","文件丢失");
        }
    }

    @GetMapping("/getData")
    @ResponseBody
    public Resp<String> getData(String UUID){
        FileQuery fileQuery =fileService.getData(UUID);
        return Resp.success(fileQuery.toString());
    }

}

