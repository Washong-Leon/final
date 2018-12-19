package com.clin.controller;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.clin.entity.Message;
import com.clin.entity.OnlineUser;

import com.clin.entity.Primessage;
import com.clin.service.PriMsgService;
import com.clin.util.ApplicationContextRegister;
import com.clin.util.MessageEncoder;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Controller
@ServerEndpoint(value="/ws/{uid}",
        encoders = { MessageEncoder.class })
public class WSServer {
    public Session session;
    public static Map<String,OnlineUser> onlineUserMap = new ConcurrentHashMap<String,OnlineUser>();
    //连接打开时执行
    private PriMsgService priMsgService;
    @OnOpen
    public void onOpen(@PathParam(value="uid") String uid,Session session) {
    	OnlineUser olu=new OnlineUser(session);
        System.out.println(uid);
    	onlineUserMap.put(uid, olu);
        System.out.println("一个客户端连接进来了 ... 它的sessionid是：" + session.getId());
    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String data,Session session) {
    	 JSONObject messageJson = JSONObject.fromObject(data);
         JSONObject message = messageJson.optJSONObject("message");
         String to = message.optString("to");
        String from = message.optString("from");
         String content=message.optString("content");
         System.out.println(content);
        try{
        	sendMessage(content,to,from);
          /* sendMessage(message,onlineUserMap.values());//消息发回给客户端
*/        }catch(Exception e){
            e.printStackTrace();
        }
        //return currentUser + "：" + message;如果有返回值，则客户端发送消息后会收到这个返回值
    }

    //连接关闭时执行
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        System.out.println("一个客户端关闭了，它的sessionid是：" + session.getId());
    }

    //连接错误时执行
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
    //自定义的方法，用于发送消息
    public void sendMessage(String message,String to,String from) throws IOException, EncodeException {
    /*	for(OnlineUser user:onlineUsers) {
    	user.getSession().getBasicRemote().sendText(message);}
    }*/
        /*Message message1=new Message();
        message1.setTo(to);
        message1.setFrom(from);
        message1.setContent(message);
        if(onlineUserMap.get(to)==null)                 //不在线存入离线消息
        {
            //websocket 使用service 层
            ApplicationContext act = ApplicationContextRegister.getApplicationContext();
            priMsgService=act.getBean(PriMsgService.class);
            priMsgService.insert(message1);

        } else {                                         //在线就发消息
            onlineUserMap.get(to).getSession().getBasicRemote().sendObject(message1);
        }*/
    }
    public void sendAllFans(String channel) throws IOException {
        //将订阅了channel的全部找出来,在线就推送，不在线存入离线
        onlineUserMap.get(channel).getSession().getBasicRemote().sendText("1");
    }
}
