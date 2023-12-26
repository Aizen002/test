package com.wh.demo.client;

import io.socket.client.IO;
import io.socket.client.Manager;
import io.socket.client.Socket;
import io.socket.engineio.client.transports.WebSocket;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Author: wanghao
 * Date: 2023/10/27 11:11
 * Description:
 */
public class MyClient02 {

    public static void main(String[] args) {

        IO.Options options = new IO.Options();
        options.transports = new String[]{WebSocket.NAME};

        String lng = "121.615944";
        String lat = "31.251572";
        String sign = "1377ae8361f4a681d4e775e87111e4c4";
        String openId = "ye81922023082310000001";
        String deviceId = "mock_test_15201926603_abcd12345";
        String appId = "ye8192";

        Long liveId = 1545815724L;
        Long roomId = 5811144449L;
        //15201926603
        UserInfo userInfo = UserInfo.builder()
                .account("5530714")
                .avatar("https://ytmedia.radio.cn/CCYT%2F202002%2F13%2F15%2F2rlY0A4kbh7pZ202002131556.png")
                .nickName("ytqrrovBwx")
                .build();
        Integer userType = 1;
        String eventType = "EXIT_CHATROOM";

        Manager manager = new Manager(URI.create("http://iovws-test.radio.cn?" +
                "capabilities=PAY_CONTENT_SUPPORTTED,NEW_DOMAIN_SUPPORTTED,MEDIA_URL_MUST_BE_HTTPS&" +
                "os=android&" +
                "packagename=com.edog.car.ceshizhuanyong_kradio&" +
                "carType=X7&" +
                "openid=" + openId + "&" +
                "sign=" + sign + "&" +
                "deviceid=" + deviceId + "&" +
                "appid=" + appId + "&" +
                "lng=" + lng + "&" +
                "lat=" + lat), options);
        final Socket socket = manager.socket("/internal");
        socket.connect();

        //客户端监听事件
        socket.on("SYSTEM_PUSH", objects -> {
            System.out.println("SYSTEM_PUSH：" + objects[0].toString());
            int i = 1;
            for (Object obj : objects) {
                System.out.println(i + ".SYSTEM_PUSH:" + obj.toString());
                i++;
            }
        });
        socket.on("EB_PUSH", objects -> {
            System.out.println("EB_PUSH：" + objects[0].toString());
            int i = 1;
            for (Object obj : objects) {
                System.out.println(i + ".EB_PUSH:" + obj.toString());
                i++;
            }
        });
        socket.on("RECV_MSG", objects -> {
            System.out.println("RECV_MSG：" + objects[0].toString());
            int i = 1;
            for (Object obj : objects) {
                System.out.println(i + ".RECV_MSG:" + obj.toString());
                i++;
            }

        });
        //模拟 - 客户端发送事件 - 用户进入聊天室
        Map<String, Object> map = new HashMap<>();
        map.put("liveId", liveId);
        map.put("roomId", roomId);
        map.put("appId", appId);
        map.put("userType", userType);
        map.put("deviceId", deviceId);
        map.put("account", userInfo.getAccount());
        map.put("avatar", userInfo.getAvatar());
        map.put("nickName", userInfo.getNickName());
        socket.emit(eventType, map);

        socket.on(Socket.EVENT_CONNECT, args1 -> {
            System.out.println("connect success");
        });

        socket.on(Socket.EVENT_CONNECT_TIMEOUT, args1 -> {
            System.out.println("connect_timeout!");
        });

        socket.on(Socket.EVENT_ERROR, args1 -> {
            System.out.println("event error!");
        });

        socket.on(Socket.EVENT_CONNECT_ERROR, args1 -> {
            System.out.println(args1[0]);
            System.out.println("connect_error:" + args1[0].toString());
        });

    }
}
