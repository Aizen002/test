package com.wh.demo.netty.c3;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

@Slf4j
public class Server {

    public static void main(String[] args) throws IOException {
        //1.创建Selector,管理多个channel
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress(8083));

        //2.建立selector和channel的关系
        SelectionKey sscKey = ssc.register(selector, 0, null);
        log.info("sscKey:{}", sscKey);
        //只关注accept事件
        sscKey.interestOps(SelectionKey.OP_ACCEPT);

        while (true) {
            //3.select方法，没有事件发生，线程阻塞，有事件，线程恢复运行。
            selector.select();
            //4.处理事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();
                iterator.remove();
                log.info("key:{}", selectionKey);
                //
                if (selectionKey.isAcceptable()) {//建立连接
                    ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
                    SocketChannel sc = channel.accept();
                    sc.configureBlocking(false);

                    SelectionKey scKey = sc.register(selector, 0, null);
                    scKey.interestOps(SelectionKey.OP_READ);
                    log.info("scKey:{}", scKey);
                } else if (selectionKey.isReadable()) {
                    SocketChannel sc = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(16);
                    int read = sc.read(buffer);
                    log.info("read value:{}", read);
                    buffer.flip();
                    log.info("msg:{}", StandardCharsets.UTF_8.decode(buffer));
                }
            }

        }


    }
}
