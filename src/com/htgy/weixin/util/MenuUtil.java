package com.htgy.weixin.util;

import com.alibaba.fastjson.JSONObject;
import com.htgy.weixin.po.Button;
import com.htgy.weixin.po.ClickButton;
import com.htgy.weixin.po.Menu;
import com.htgy.weixin.po.ViewButton;
import com.htgy.weixin.service.MenuService;
import com.htgy.weixin.service.impl.MenuServiceImpl;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class MenuUtil {
    private final static String MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    /**
     * 组装菜单
     *
     * @param accessToken
     * @return
     */
    public static Menu initMenu(String accessToken) {
        //主菜单
        ClickButton clickButton = new ClickButton();
        clickButton.setName("今日新闻");
        clickButton.setType("click");
        clickButton.setKey("main_caidan");
        //主菜单
        ViewButton viewButton = new ViewButton();
        viewButton.setName("图片墙");
        viewButton.setType("view");
        viewButton.setUrl(MessageUtil.IP_ADDRESS + "static/image/1.jpg");
//        //主菜单
//        ClickButton clickButton1 = new ClickButton();
//        clickButton1.setName("扫码");
//        clickButton1.setType("scancode_push");
//        clickButton.setKey("main_saoma");
        //今日活动的子菜单
        ViewButton viewButton1 = new ViewButton();
        viewButton1.setName("点击抽奖");
        viewButton1.setUrl(MessageUtil.IP_ADDRESS + "static/image/2.jpg");
        viewButton1.setType("view");
        //今日活动的子菜单
        ViewButton viewButton2 = new ViewButton();
        viewButton2.setName("中奖名单");
        viewButton2.setType("view");
        viewButton2.setUrl(MessageUtil.IP_ADDRESS + "lottery.html");

        Button button = new ClickButton();
        button.setName("抽奖活动");
        button.setSub_button(new Button[]{viewButton1,viewButton2});


        Menu menu = new Menu();
        menu.setButton(new Button[]{button,viewButton,clickButton});
        return menu;
    }

    public static int createMenu(String token, String menu) throws IOException {
        int result = 0;
        String url = MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WeixinUtil.doPostStr(url, menu);
        if (jsonObject != null) {
            result = jsonObject.getInteger("errcode");
            return result;
        }
        return -1;
    }
}
