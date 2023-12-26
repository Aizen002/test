package com.wh.demo.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    private static Semaphore semaphore = new Semaphore(3);


    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        for (int i = 0; i< 5; i++){

            executorService.submit(new Runnable() {
                @Override
                public void run() {

                    try {
                        semaphore.acquire();
                        System.out.println("------------获取令牌成功");
                        Thread.sleep(10000);
                        semaphore.release();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
            });

        }
    }
}
