package com.ntu.yangyu.myClient;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class Client {

    //上传文件方法
    public void upload(String path){

        CloseableHttpClient client= HttpClients.createDefault();
        String urlStrl="http://localhost:8080/upload";
        //创建http对象
        HttpPost httpPost=new HttpPost(urlStrl);
        //构造一个contentBody 实现类对象
//        FileBody fileBody=new FileBody(new File("E:\\uploadTest\\aaa.jpg"));
        //构造文件使用的entity
        MultipartEntityBuilder builder=MultipartEntityBuilder.create();
        builder.setCharset(Consts.UTF_8);   //设置编码
        builder.setContentType(ContentType.create("multipart/form-data",Consts.UTF_8));
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);  //设置浏览器模式
        HttpEntity htttpEntity= builder
//                .addPart("fileName",null)
                //通过file byte[] inputstream 来上传文件
                .addBinaryBody("file",new File(path)).build();
        //  .addTextBody("")    //其他参数
        httpPost.setEntity(htttpEntity);
        CloseableHttpResponse response=null;
        try {
            response=client.execute(httpPost);
            HttpEntity entity=response.getEntity();
            String toStringResult= EntityUtils.toString(entity, StandardCharsets.UTF_8);

            // 客户端  获取返回的 UUID
            System.out.println("***********************************************************");
            System.out.println(toStringResult);
            System.out.println("***********************************************************");
            EntityUtils.consume(entity);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //jdk原生api发送请求
    //根据UUID 获取文件信息
    public void getData(String UUID) throws IOException {
        String urlStr="http://localhost:8080/getData?UUID="+UUID;
        URL url=new URL(urlStr);
        URLConnection urlConnection=url.openConnection();
        HttpURLConnection httpURLConnection= (HttpURLConnection) urlConnection;
        try(
            InputStream is=httpURLConnection.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,StandardCharsets.UTF_8);
            BufferedReader br=new BufferedReader(isr);
        ){
            String line;
            while ((line= br.readLine())!=null){
                System.out.println(line);
            }
        }

    }


//    下载文件到本地
    public void download(String UUID,String myPath) throws MalformedURLException {
        URL url=new URL("http://localhost:8080/getData?UUID="+UUID);
        HttpURLConnection urlConnection= null;
        InputStream inputStream= null;
        FileOutputStream fos= null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
            fos = new FileOutputStream(myPath);
            byte[] buffer=new byte[1024*1024*1024];
            int len;
            while ((len=inputStream.read(buffer))!=-1){
                fos.write(buffer,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                urlConnection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }


}
