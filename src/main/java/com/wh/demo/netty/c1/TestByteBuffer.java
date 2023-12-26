package com.wh.demo.netty.c1;


import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

@Slf4j
public class TestByteBuffer {

    public static void main(String[] args) {

        try (FileChannel channel = new FileInputStream("demo.txt").getChannel()) {
            //创建大小为10字节的缓存区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true){
                int len = channel.read(buffer);
                log.info("读取字节个数:{}",len);
                if (len == -1){
                    break;
                }

                buffer.flip();  //改为读模式
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    log.info("result:{}",(char)b);
                }
                buffer.clear(); //改为写模式
            }
        } catch (IOException e) {



        }

    }
}
