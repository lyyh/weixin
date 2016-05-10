package com.htgy.weixin.po;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class AccessToken {
    private String token;
    private String Ip;

    public String getIp() {
        return Ip;
    }

    public void setIp(String ip) {
        Ip = ip;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    private int expiresIn;
}
