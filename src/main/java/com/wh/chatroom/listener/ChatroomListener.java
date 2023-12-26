package com.wh.chatroom.listener;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.wh.chatroom.req.EnterChatroomReq;
import com.wh.chatroom.req.ExitChatroomReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Author: wanghao
 * Date: 2023/10/8 23:47
 * Description:
 */
public class ChatroomListener {

    private Logger logger = LoggerFactory.getLogger(ChatroomListener.class);

    @OnEvent("ENTER_CHATROOM")
    public void enterChatroom(SocketIOClient client, EnterChatroomReq req) {
        logger.info("用户{}进入聊天室", req.getNickName());
        client.joinRoom(req.getRoomId().toString());
    }

    @OnEvent("EXIT_CHATROOM")
    public void exitChatroom(SocketIOClient client, ExitChatroomReq req) {
        logger.info("用户{}离开聊天室", req.getNickName());
        client.leaveRoom(req.getRoomId().toString());
    }
}
