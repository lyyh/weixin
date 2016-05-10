package com.htgy.weixin.util.ip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/3/14 0014.
 */
public class IPAddress {
    /**
     * 获取本地外网IP
     * @return
     * @throws IOException
     */
    public static String getMyIP() throws IOException {
        InputStream ins = null;
        URLConnection conn = null;
        try {
            URL url = new URL("http://iframe.ip138.com/ic.asp");
            conn = url.openConnection();
            ins = conn.getInputStream();
            InputStreamReader isReader = new InputStreamReader(ins, "GB2312");
            BufferedReader bReader = new BufferedReader(isReader);
            StringBuffer webContent = new StringBuffer();
            String str = null;
            while ((str = bReader.readLine()) != null) {
                webContent.append(str);
            }
            System.out.println(webContent);
            int start = webContent.indexOf("[") + 1;
            int end = webContent.indexOf("]");
            return webContent.substring(start, end);
        } finally {
            if (ins != null) {
                ins.close();
            }
        }
    }

    /**
     * 获取本地IP
     * @return
     * @throws IOException
     */
    public static String getMyIPLocal() throws IOException {
        InetAddress ia = InetAddress.getLocalHost();
        return ia.getHostAddress();
    }
}
