package com.example.demo.controller;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class UseFuture {
    private static class UserCallable implements Callable{
        private int sum;
        @Override
        public Object call() throws Exception {
            System.out.println("开始计算--");
            Thread.sleep(2000);
            for (int i = 0; i <5000 ; i++) {
                sum+=i;
            }
            System.out.println("计算结果："+sum);
            return sum;
        }
    }

    public static void main(String[] args) throws Exception{
        UserCallable userCallable = new UserCallable();
        FutureTask<Integer> futureTask = new FutureTask<Integer>(userCallable);
        new Thread(futureTask).start();
        Random tt = new Random();
        if (tt.nextBoolean()){
            System.out.println("Get UserableResult:"+futureTask.get());
        }else {
            futureTask.cancel(true);
            System.out.println("中段过程");
        }
    }
}
