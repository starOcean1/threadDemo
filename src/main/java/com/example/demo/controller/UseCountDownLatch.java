package com.example.demo.controller;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch {
    static CountDownLatch latch = new CountDownLatch(6);

    public static class InitThread implements Runnable{

        @Override
        public void run() {
            System.out.println("Thread:"+Thread.currentThread().getName()+"ready in init work");
            latch.countDown();//
            System.out.println("Thread:"+Thread.currentThread().getName()+"do other work");
        }
    }

    public static class BusiThread implements  Runnable{

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread:"+Thread.currentThread().getName()+"do Business");
        }
    }

    public static void main(String[] args)throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1);
                  System.out.println("Thread:"+Thread.currentThread().getName()+"ready in init work step 1");
                    latch.countDown();
                    Thread.sleep(5);
                    System.out.println("Thread:"+Thread.currentThread().getName()+"ready in init work step 2");
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new BusiThread()).start();
        for (int i = 0; i <=3 ; i++) {
            Thread thread = new Thread(new InitThread());
            thread.start();
        }
            latch.await();
        System.out.println("Main Thread is doing its work");
    }
}
