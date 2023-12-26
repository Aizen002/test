package com.wh.demo.countdown;

import java.util.concurrent.CountDownLatch;

public class CountDownDemo {

    private static CountDownLatch countDownLatch = new CountDownLatch(3);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 3;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    countDownLatch.countDown();
                    System.out.println(Thread.currentThread().getName() + "---------执行");
                    try {
                        Thread.sleep(3000l);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //该线程等待其他三个线程执行结束之后再次执行
                    Thread.sleep(1000);
                    countDownLatch.await();
                    System.out.println("执行完成。。。。。。。。");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        }).start();

        Thread.sleep(500000000);

    }
}
