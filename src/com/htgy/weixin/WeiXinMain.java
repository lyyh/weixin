package com.htgy.weixin;

import com.htgy.weixin.po.AccessToken;
import com.htgy.weixin.service.MenuService;
import com.htgy.weixin.service.impl.MenuServiceImpl;
import com.htgy.weixin.util.WeixinUtil;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/17 0017.
 */
public class WeiXinMain {
    public static void main(String[] args) throws IOException {
        AccessToken token = WeixinUtil.getAccessToken();
        System.out.println("票据：" + token.getToken());
        System.out.println("有效时间" + token.getExpiresIn());
        MenuService menuService = new MenuServiceImpl();
        menuService.createMenu(token.getToken());
    }
}
