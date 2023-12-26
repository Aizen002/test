package com.wh.chatroom;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;
import com.wh.chatroom.listener.ChatroomListener;

/**
 * Author: wanghao
 * Date: 2023/10/8 23:37
 * Description:
 */
public class ChatroomServer extends SocketIOServer {
    public ChatroomServer(Configuration configuration) {
        super(configuration);
    }

    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8080);

        ChatroomServer chatroomServer = new ChatroomServer(config);
        chatroomServer.start();

        SocketIONamespace chatNamespace = chatroomServer.addNamespace("/chat");
        chatNamespace.addListeners(new ChatroomListener());


    }


}
