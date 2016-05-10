package com.htgy.weixin.util;

import com.htgy.weixin.po.*;
import com.htgy.weixin.util.ip.IPAddress;
import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * Created by Administrator on 2016/3/11 0011.
 */
public class MessageUtil extends MessageType{
    public final static String IP_ADDRESS = "http://113.251.163.244/";

    /**
     * xml转成map集合
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xml_to_Map(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<>();
        SAXReader saxReader = new SAXReader();
        InputStream inputStream = request.getInputStream();
        Document document = saxReader.read(inputStream);

        Element element = document.getRootElement();
        List<Element> elementList = element.elements();

        for(Element element1 : elementList){
            map.put(element1.getName(),element1.getText());
        }
        inputStream.close();
        return map;
    }

    /**
     * 将文本信息转成xml格式
     * @param textMessage
     * @return
     */
    public static String textMessage_to_xml(TextMessage textMessage){
        XStream xStream = new XStream();
        //将头改成xml
        xStream.alias("xml",textMessage.getClass());
        //将文本对象转化成xml
        return  xStream.toXML(textMessage);
    }

    public static String init_text(String toUserName,String fromUserName,String content) {
        TextMessage textMessage = new TextMessage();
        textMessage.setFromUserName(toUserName);
        textMessage.setToUserName(fromUserName);
        textMessage.setCreateTime(new Date().getTime());
        textMessage.setMsgType(MessageUtil.MESSAGE_TEXT);
        textMessage.setContent(content);
        return MessageUtil.textMessage_to_xml(textMessage);
    }

    /**
     * 主菜单
     * @return
     */
    public static String menu_text(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("欢迎来到寒潭古月订阅号\n\n");
        stringBuffer.append("1.寒潭古月订阅号介绍\n");
        stringBuffer.append("2.抽奖活动\n");
        stringBuffer.append("回复?调出菜单\n");
        return stringBuffer.toString();
    }

    /**
     * 第一个菜单
     * @return
     */
    public static String first_menu(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("这是一个测试的订阅号");
        return stringBuffer.toString();
    }

    /**
     * 第二个菜单
     */
    public static String second_menu(){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("暂时不能抽奖");
        return stringBuffer.toString();
    }

    /**
     * 将图文消息转化成xml
     * @param newsMessage
     * @return
     */
    public static String newsMessageToXml(NewsMessage newsMessage){
        XStream xStream = new XStream();
        xStream.alias("xml",newsMessage.getClass());
        xStream.alias("item",new News().getClass());
        return xStream.toXML(newsMessage);
    }

    public static String getImageUrl(){
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("http://");
            sb.append(IPAddress.getMyIP());
            sb.append("static/image/");
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPageUrl(){
        StringBuffer sb = new StringBuffer();
        try {
            sb.append("http://");
            sb.append(IPAddress.getMyIP());
            sb.append("/");

            String s = sb.toString();
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 图文消息的组装
     * @param toUserName
     * @param fromUserName
     * @return
     */
    public static String initNewsMessage(String toUserName,String fromUserName){
        String message = null;
        List<News> list = new ArrayList<>();
        NewsMessage newsMessage = new NewsMessage();
        News news = new News();
        news.setTitle("寒潭古月介绍");
        news.setDescription("欢迎来到寒潭古月");
        news.setPicUrl(IP_ADDRESS+"/static/image/1.jpg"); //设置图片路径
        news.setUrl(IP_ADDRESS+"/index.jsp"); //设置跳转路径
        News news1 = new News();
        news1.setTitle("寒潭古月抽奖");
        news1.setDescription("寒潭古月抽奖规则");
        news1.setPicUrl(IP_ADDRESS +"static/image/2.jpg"); //设置图片路径
        news1.setUrl(IP_ADDRESS +"lottery.html"); //设置跳转路径
        list.add(news);
        list.add(news1);
        newsMessage.setToUserName(fromUserName);
        newsMessage.setFromUserName(toUserName);
        newsMessage.setCreateTime(System.currentTimeMillis());
        newsMessage.setMsgType(MESSAGE_NEWS);
        newsMessage.setArticles(list);
        newsMessage.setArticleCount(list.size());
        message = MessageUtil.newsMessageToXml(newsMessage);
        return message;
    }


}
