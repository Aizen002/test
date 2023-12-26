package com.wh.websocket;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIONamespace;
import com.corundumstudio.socketio.SocketIOServer;

/**
 * Author: wanghao
 * Date: 2023/10/19 10:35
 * Description:
 */
public class LocalSocketIOServer {

    public static void main(String[] args) {

        Configuration config = new Configuration();
        config.setHostname("localhost"); // 主机名
        config.setPort(9098); // 端口号

        SocketIOServer server = new SocketIOServer(config);
        server.start();
        System.out.println("Socket.IO服务器已启动");
        SocketIONamespace test = server.addNamespace("TEST");
        test.addConnectListener(new WebSocketConnectListener());
        test.addListeners(new WebSocketDataListener());
        System.out.println("webSocket server init success");

    }


}
