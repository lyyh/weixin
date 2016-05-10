package com.htgy.test;

import com.alibaba.fastjson.JSON;
import com.htgy.weixin.po.AccessToken;
import com.htgy.weixin.util.ImageUtil;
import com.htgy.weixin.util.MenuUtil;
import com.htgy.weixin.util.WeixinUtil;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class TestMain {
    public static void main(String[] args) {
        AccessToken token = WeixinUtil.getAccessToken();
        System.out.println("票据：" + token.getToken());
        System.out.println("有效时间" + token.getExpiresIn());

//        WeixinUtil.getIP(token.getToken());
//        try {
//            String mediaId = ImageUtil.upload("G:/IDEAweb/weixin/web/static/image/2.jpg", token.getToken(),"image");
//            System.out.println(mediaId);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
//
//
//    String path = "G:/图片/诛仙/3.jpg";
//    try {
//        String medialId = ImageUtil.upload(path,token.getToken(),"image");
//        System.out.println(medialId);
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//}
//        public static void main (String[]args){

//        }
//
//    private static String getMyIP() throws IOException {
//        InputStream ins = null;
//        try {
//            URL url = new URL("http://iframe.ip138.com/ic.asp");
//            URLConnection con = url.openConnection();
//            ins = con.getInputStream();
//            InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
//            BufferedReader bReader = new BufferedReader(isReader);
//            StringBuffer webContent = new StringBuffer();
//            String str = null;
//            while ((str = bReader.readLine()) != null) {
//                webContent.append(str);
//            }
//            int start = webContent.indexOf("[") + 1;
//            int end = webContent.indexOf("]");
//            return webContent.substring(start, end);
//        } finally {
//            if (ins != null) {
//                ins.close();
//            }
//        }
//    }
//
//    private static String getMyIPLocal() throws IOException {
//        InetAddress ia = InetAddress.getLocalHost();
//        return ia.getHostAddress();
//    }
}
