package com.ntu.yangyu.service;

import com.ntu.yangyu.myResp.Resp;
import com.ntu.yangyu.vo.FileQuery;
import org.springframework.web.multipart.MultipartFile;



public interface FileService {
    Resp<String> upload(MultipartFile file);
    FileQuery getData(String UUID);
    String findType(String UUID);
}
