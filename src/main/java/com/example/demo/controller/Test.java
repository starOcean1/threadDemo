package com.example.demo.controller;


import java.util.concurrent.*;

 public class Test implements Runnable {
private     static     ThreadPoolExecutor executor = new ThreadPoolExecutor
        (10, 20,1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(30),new ThreadPoolExecutor.AbortPolicy());
     @Override
        public void run() {
         try {
             Thread.sleep(1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
         System.out.println(Thread.currentThread().getName() +" is end");
        }


    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 100; i++) {
            try {
                System.out.println("queue大小："+executor.getQueue().size()+"------ActiveCount大小："+
                        executor.getActiveCount()+"-----TaskCount大小："+executor.getTaskCount());
                executor.execute(new Test());
            }catch (RejectedExecutionException e){
                System.out.println("抛弃任务"+i);
            }
        }

        System.out.println("主线程结束");
        Thread.sleep(5000);
        System.out.println("queue大小："+executor.getQueue().size()+"------ActiveCount大小："+
                executor.getActiveCount()+"-----TaskCount大小："+executor.getTaskCount());
    }


 }

