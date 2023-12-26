package com.wh.demo.netty.c2;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server {
    //使用nio来理解阻塞模式
    public static void main(String[] args) throws IOException {
        //0.创建缓存
        ByteBuffer buffer = ByteBuffer.allocate(16);
        //1.创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        //2.绑定监听端口
        ssc.bind(new InetSocketAddress(8083));

        List<SocketChannel> channels = new ArrayList<>();
        while (true){
            //3.accept 建立与客户端的连接
            //socketChannel用来与客户端之间通信
            log.info("connecting...........");
            SocketChannel sc = ssc.accept();
            if (sc != null){
                sc.configureBlocking(false);
                log.info("connected......{}",sc);
                channels.add(sc);
            }
            for (SocketChannel channel : channels){
                log.info("before read.....{}",channel);
                int read = channel.read(buffer);
                if (read > 0){
                    buffer.flip();
                    String s = StandardCharsets.UTF_8.decode(buffer).toString();
                    log.info("msg:{}",s);
                    buffer.clear();
                    log.info("after read......{}",channel);
                }
            }
        }
    }
}
