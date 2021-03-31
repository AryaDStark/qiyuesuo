package com.ntu.yangyu.myClient;

import org.junit.Test;

import java.io.IOException;

public class TestClient {

    Client client=new Client();
    // 上传文件路径+文件名
    String pathA="E:\\uploadTest\\aaa.jpg";
    String pathB="E:\\uploadTest\\123.txt";
    String pathC="E:\\uploadTest\\jvm.jpg";

    // 客户端的 UUID
    String uuid="b93336ff-ce9a-4e10-b466-00bac50ab2e9";

    // 下载到本地 需要的路径+文件名
    String pathE="E:\\downloadTest\\123.txt";

//    上传文件测试
    @Test
    public void upload(){
        client.upload(pathB);
    }

    //获取元数据测试
    @Test
    public void getData() throws IOException {
        client.getData(uuid);
    }

    //下载测试
    @Test
    public void download() throws IOException {
        client.download(uuid,pathE);
    }
}
