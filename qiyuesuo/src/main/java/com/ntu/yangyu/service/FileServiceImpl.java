package com.ntu.yangyu.service;

import com.ntu.yangyu.dao.FileDao;
import com.ntu.yangyu.myResp.Resp;
import com.ntu.yangyu.vo.FileQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Value("${path.filePath}")
     private String path;

    @Autowired
    FileDao fileDao;

    @Override
    public Resp<String> upload(MultipartFile file) {
       //文件非空判断
        if (file.isEmpty()){
            return Resp.fail("400","文件为空");
        }
        //获取文件大小  判断一下单位 略有瑕疵
        String fileSize="";
        Long size=file.getSize();
        if (size<=1024){
            fileSize=size+"B";
        }
        if (size>1024&&size<=1024*1024){
            fileSize=size/1024+"KB";
        }
        if (size>1024*1024){
            size=size*100;
            fileSize=String.valueOf(size/100)+"."+String.valueOf(size%100)+"MB";
        }

        //获取文件原始名字
        String originalFileName=file.getOriginalFilename();
        // 取一个 UUID 名
        String fileName= UUID.randomUUID().toString();
        // 获取当前时间 + 设置格式
        String form    = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MMM-dd");
        Date date=new Date();
        String currentTime=format.format(date);
        String time=currentTime+"";

        //设置 文件存储路径
        String filePath=path+"\\"+time+"\\"+fileName+"."+form;
        File dest=new File(filePath);
        if (!dest.getParentFile().exists()){dest.getParentFile().mkdirs();}
        try{

            // 存储文件
            file.transferTo(dest);

            //向数据库存储文件信息
            fileDao.saveFile(fileSize,form,originalFileName,time,filePath,fileName);

        } catch (IOException e) {
            e.printStackTrace();
            return Resp.fail("500",originalFileName+"上传失败");
        }
        return Resp.success(fileName);

    }

    //返回元数据
    @Override
    public FileQuery getData(String UUID) {
        return fileDao.getData(UUID);
    }

    //返回路径
    @Override
    public String findType(String UUID) {
        return fileDao.getData(UUID).getPath();
    }
}
