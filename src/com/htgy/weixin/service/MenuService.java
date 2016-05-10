package com.htgy.weixin.service;

import java.io.IOException;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public interface MenuService {
    public void createMenu(String json) throws IOException;
    public String menuSeek(String json);
    public String menuDelete(String json);
    public String menuPush(String json);
}
