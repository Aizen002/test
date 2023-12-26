package com.wh.demo.threadlocal;

public class ThreadLocalDemo {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();


    public static void main(String[] args) throws InterruptedException {

        threadLocal.set("hahaha");

        new Thread(() -> {
            String value = threadLocal.get();
            System.out.println("s:" + value);
        }).start();

        Thread.sleep(10000L);

        System.out.println("value:" + threadLocal.get());

    }

}
