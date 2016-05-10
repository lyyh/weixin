package com.htgy.weixin.service.impl;

import com.alibaba.fastjson.JSON;
import com.htgy.weixin.service.MenuService;
import com.htgy.weixin.util.MenuUtil;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class MenuServiceImpl implements MenuService{

    /**
     * 创建菜单
     * @param token
     * @throws IOException
     */
    @Override
    public void createMenu(String token) throws IOException {
        String menu = JSON.toJSONString(MenuUtil.initMenu(token));
        int errcode = MenuUtil.createMenu(token,menu);
        if(errcode!=0){
            System.out.println("创建菜单错误："+errcode);
        }else {
            System.out.println("创建成功！");
        }
    }

    @Override
    public String menuSeek(String json) {
        return null;
    }

    @Override
    public String menuDelete(String json) {
        return null;
    }

    @Override
    public String menuPush(String json) {
        return null;
    }
}
