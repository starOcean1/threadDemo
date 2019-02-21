package com.example.demo.controller;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NewThread {
      private static class UserThread extends Thread{
          @Override
          public void run() {
              super.run();
              System.out.println(Thread.currentThread().getName()+"--userThread");
          }
      }

      private static class  UserRunnable implements Runnable{

          @Override
          public void run() {
              System.out.println(Thread.currentThread().getName()+"--userRunnable");
          }
      }

      private static class UserCallable implements Callable{

          @Override
          public Object call() throws Exception {
              System.out.println(Thread.currentThread().getName()+"--userCallable");
              return "callable返回值";
          }
      }

    public static void main(String[] args) throws InterruptedException,ExecutionException{
        UserThread userThread = new UserThread();
        userThread.start();

        UserRunnable userRunnable = new UserRunnable();
        Thread runnable = new Thread(userThread);
        runnable.start();

        UserCallable userCallable = new UserCallable();
        FutureTask<String> futureTask = new FutureTask<>(userCallable);
        new Thread(futureTask).start();
        System.out.println(futureTask.get());
    }


}
