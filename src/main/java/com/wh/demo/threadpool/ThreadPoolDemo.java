package com.wh.demo.threadpool;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        scheduledExecutorService.schedule(()->{
//            System.out.println("延迟执行一次:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//        },10, TimeUnit.SECONDS);
        //首次5s之后执行，后续以2s的频次执行
        scheduledExecutorService.scheduleAtFixedRate(() -> {System.out.println("1.以给定的频率周期性的运行:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
            try {
                Thread.sleep(9000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },5,2,TimeUnit.SECONDS);

//        scheduledExecutorService.scheduleWithFixedDelay(()-> {System.out.println("2.每次执行相隔delay时间:" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
//            try {
//                Thread.sleep(3000L);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        },5,2,TimeUnit.SECONDS);

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,10,60,TimeUnit.SECONDS,new ArrayBlockingQueue<>(500),Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());



    }
}
