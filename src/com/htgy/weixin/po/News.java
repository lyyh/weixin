package com.htgy.weixin.po;

/**
 * Created by Administrator on 2016/3/13 0013.
 */
public class News {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public void setDescription(String description) {
        Description = description;
    }

    public String getDescription() {
        return Description;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitle() {
        return Title;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getUrl() {
        return Url;
    }
}
