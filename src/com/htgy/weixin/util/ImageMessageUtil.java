package com.htgy.weixin.util;

import com.htgy.weixin.po.Image;
import com.htgy.weixin.po.ImageMessage;
import com.htgy.weixin.po.News;
import com.htgy.weixin.po.NewsMessage;
import com.thoughtworks.xstream.XStream;

/**
 * Created by Administrator on 2016/3/16 0016.
 */
public class ImageMessageUtil {
    /**
     * 组装图片消息
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initImageMessage(String toUserName,String fromUserName) {
        String message = null;
        Image image = new Image();
        image.setMediaId("DCt95u1_C7NCsgKskSeqViPkg05lRXIuqCGcwo2fPsOyltqMugylWbQgVXi7AvH_");
        ImageMessage imageMessage = new ImageMessage();
        imageMessage.setToUserName(fromUserName);
        imageMessage.setFromUserName(toUserName);
        imageMessage.setCreateTime(System.currentTimeMillis());
        imageMessage.setMsgType(MessageType.MESSAGE_IMAGE);
        imageMessage.setImage(image);
        return imageMessageToXml(imageMessage);
    }

    /**
     * 将图文消息转化成xml
     * @param imageMessage
     * @return
     */
    public static String imageMessageToXml(ImageMessage imageMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",imageMessage.getClass());
        return xStream.toXML(imageMessage);
    }
}
