package com.wh.demo.netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TestByteBufferExam {

    /**
     * 网络上有多条数据发送给服务端，数据之间使用\n进行分隔
     * 但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
     *      Hello,world\n
     *      I'm zhangsan\n
     *      How are you?\n
     * 变成了下面的两个byteBuffer(黏包，半包)
     *      Hello,world\nI'm zhangsan\nHo
     *      w are you?\n
     * 现在要求你编写程序，将错乱的数据恢复成原始的按\n分隔的数据
     * @param args
     */
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(32);
        buffer.put("Hello,world\nI'm zhangsan\nHo".getBytes());
        split(buffer);
        buffer.put("w are you?\n".getBytes());
        split(buffer);
    }


    private static void split(ByteBuffer buffer){
        //切换成读模式
        buffer.flip();
        for (int i = 0; i < buffer.limit(); i++) {
            //当遇到\n的时候说明是一个完整的包
            if (buffer.get(i) == '\n'){
                int len = i + 1  - buffer.position();
                ByteBuffer tempBuffer = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j ++){
                    byte b = buffer.get();
                    tempBuffer.put(b);
                }
                tempBuffer.flip();
                String s = StandardCharsets.UTF_8.decode(tempBuffer).toString();
                log.info("package result:{}",s);
            }
        }
        buffer.compact();
    }
}
