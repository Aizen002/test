package com.wh.demo.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo implements Runnable{


    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            System.out.println("----------------最后的执行");
        }
    });

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("线程1启动。。。。。");
                try {
                    Thread.sleep(1000L);
                    cyclicBarrier.await();
                    System.out.println("-----线程1--------");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("线程2启动。。。。。");
                try {
                    Thread.sleep(3000L);
                    cyclicBarrier.await();
                    System.out.println("-----线程2--------");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (BrokenBarrierException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();


        System.out.println("---------——主线程------------");
        cyclicBarrier.await();

        Thread.sleep(50000000000000L);


    }

    @Override
    public void run() {
        System.out.println("----------最终的执行");
    }
}
