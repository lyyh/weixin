package com.htgy.weixin.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class ImageUtil {
    private static String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN" +
            "&&type=TYPE";
    public static String upload(String filePath,String accessToken,String type) throws IOException {
        File file = new File(filePath);
        if(!file.exists()||!file.isFile()){
            throw  new IOException("文件不存在");
        }
        String url = UPLOAD_URL.replace("ACCESS_TOKEN",accessToken).replace("TYPE",type);
        URL urlObject = new URL(url);
        //连接
        HttpURLConnection conn = (HttpURLConnection) urlObject.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false); //忽略缓存

        //设置请求头
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "utf-8");

        //设置边界
        String BOUNDARY = "--------"+System.currentTimeMillis();
        conn.setRequestProperty("Content-Type","multipart/form-data;boundary="+BOUNDARY);

        StringBuilder sb = new StringBuilder();
        sb.append("--");
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Conntent-Disposition:form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");

        byte[] head = sb.toString().getBytes("utf-8");

        //获得输出流
        OutputStream out = new DataOutputStream(conn.getOutputStream());
        //输出表头
        out.write(head);

        //文件正文部分
        //把文件以流的方式推送到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferObject = new byte[1024];
        while ((bytes=in.read(bufferObject))!=-1){ //当没有读取完全时继续读取
            out.write(bufferObject,0,bytes);
        }
        in.close();

        //结尾部分
        byte[] foot = ("\r\n--"+BOUNDARY+"--\r\n").getBytes("utf-8"); //定义最后数据分割线
        out.write(foot);
        out.flush();
        out.close();

        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader read = null;
        String result = null;
        try{
            //定义BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = null;
            while((line = read.readLine())!=null){
                stringBuffer.append(line);
            }
            if(result==null){
                result = stringBuffer.toString();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(read!=null){
                read.close();
            }
        }

        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println("上传图片返回的JsonObject"+jsonObject);
        String mediaId = jsonObject.getString("media_id");
        return mediaId;
    }
}
