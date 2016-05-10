package com.htgy.weixin.servlet;

import com.htgy.weixin.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/11 0011.
 */
public class WeiXinServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");
        String echostr = req.getParameter("echostr");
        PrintWriter printWriter = resp.getWriter();
        if(CheckUtil.check(signature, timestamp, nonce)){
            printWriter.print(echostr);
        }else{
            printWriter.print("验证无效！");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        String message = null;
                try {
                    Map<String,String> map = MessageUtil.xml_to_Map(req);
                    String toUserName = map.get("ToUserName");
                    String fromUserName = map.get("FromUserName");
//            String createTime = map.get("CreatTime");
                    String msgType = map.get("MsgType");
                    String content = map.get("Content");
                    String msgId = map.get("MsgId");
                    if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
                if("1".equals(content)){
                   message = MessageUtil.init_text(toUserName,fromUserName,MessageUtil.first_menu());
                }
                else if("2".equals(content)){
                    message = MessageUtil.initNewsMessage(toUserName,fromUserName);
                    System.out.println(message);
                }
                else if("3".equals(content)){
                    message = ImageMessageUtil.initImageMessage(toUserName,fromUserName);
                    System.out.println(message);
                }
                else if("4".equals(content)){
//                    String MenuUtil.initMenu(WeixinUtil.getAccessToken().getToken());
                }
                else if("?".equals(content)||"？".equals(content)) {
                    message = MessageUtil.init_text(toUserName, fromUserName, MessageUtil.menu_text());
                }
        }
            else if(MessageUtil.MESSAGE_EVENT.equals(msgType)){
                String eventName = map.get("Event");
                if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventName)){
                    message = MessageUtil.init_text(toUserName,fromUserName,MessageUtil.menu_text());
                }
            }
            out.print(message);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
