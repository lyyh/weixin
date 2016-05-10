package com.htgy.weixin.po;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class ImageMessage extends BaseMessage {
    public Image getImage() {
        return Image;
    }

    public void setImage(Image Image) {
        this.Image = Image;
    }

    private Image Image;
}
