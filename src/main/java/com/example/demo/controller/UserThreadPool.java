package com.example.demo.controller;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UserThreadPool {
    static class Work implements Runnable{
        private String taskName;
        private Random random = new Random();

        public Work(String taskName) {
            this.taskName = taskName;
        }

        public String getName() {
            return taskName;
        }


        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" process the task : "+taskName);
            try {
                Thread.sleep(random.nextInt(100)*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(4,4,3, TimeUnit.SECONDS,
               new ArrayBlockingQueue<>(2),
               new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 10; i++) {
            Work work = new Work("work"+i);
            System.out.println("a new work was added"+work.getName());
            executorService.execute(work);
        }
        executorService.shutdown();

    }
}
