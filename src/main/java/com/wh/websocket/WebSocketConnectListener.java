package com.wh.websocket;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.ConnectListener;

/**
 * Author: wanghao
 * Date: 2023/10/19 10:39
 * Description:
 */
public class WebSocketConnectListener implements ConnectListener {
    @Override
    public void onConnect(SocketIOClient client) {

        System.out.println("连接成功");


    }
}
