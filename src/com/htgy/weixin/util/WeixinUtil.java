package com.htgy.weixin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.htgy.weixin.po.AccessToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class WeixinUtil {
    //在开发者id上获取
    protected static final String APPID="wx01a61498dd4c7026";
    protected static final String APPSECRET="21e7f3e8937fce661dd1f29059ae378f"; //密钥
    private static final String ACCESS_TOKEN_URL="https://api.weixin.qq" +
            ".com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
    private static final String GET_IP_URL = "https://api.weixin.qq" +
            ".com/cgi-bin/getcallbackip?access_token=ACCESS_TOKEN";

    /**
     *以get请求方式从http中拿到结果
     * 将我们的参数提交到地址并从接口里面拿到结果
     * @param url
     * @return
     * @throws IOException
     */
    public static JSONObject doGetStr(String url) throws IOException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url); //get方式传值
        JSONObject jsonObject = null;
        HttpResponse httpResponse = defaultHttpClient.execute(httpGet);
        HttpEntity httpEntity = (HttpEntity) httpResponse.getEntity(); //从消息体里面拿结果
        if(httpEntity!=null){
            String result = EntityUtils.toString( httpEntity,"utf-8");
            jsonObject = JSONObject.parseObject(result);
        }
        return jsonObject;
    }

    /**
     * post请求方式从http中拿到结果
     * 将我们的参数提交到地址并从接口里面拿到结果
     * @param url
     * @param outStr
     * @return
     * @throws IOException
     */
    public static JSONObject doPostStr(String url,String outStr) throws IOException {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url); //post方式传值
        JSONObject jsonObject = null;
        httpPost.setEntity(new StringEntity(outStr,"utf-8")); //将传递的消息转为utf-8
        HttpResponse httpResponse = defaultHttpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity(),"utf-8"); //将接收的消息转为utf-8
        jsonObject = JSONObject.parseObject(result);
        return jsonObject;
    }

    /**
     * 获取access_token
     * @return 返回票据
     */
    public static AccessToken getAccessToken(){
        AccessToken accessToken = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID",APPID).replace("APPSECRET",APPSECRET);
        try {
            JSONObject jsonObject = doGetStr(url);
            if(jsonObject!=null){
                accessToken.setToken(jsonObject.getString("access_token")); //获取到的凭证
                accessToken.setExpiresIn(jsonObject.getInteger("expires_in")); //凭证有效时间，单位：秒
            }
            return accessToken;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }



    /**
     * 获取微信服务器IP地址
     * @param token
     * @return ip_list
     */
    public static JSONObject getIP(String token){
        String url = GET_IP_URL.replace("ACCESS_TOKEN",token);
        try {
            JSONObject jsonObject = doGetStr(url);
            if(jsonObject!=null){
                return jsonObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  null;
    }
}
