package com.example.demo.controller;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarric {

    static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,new CollectThread());
    private static ConcurrentHashMap<String,Long> resultMap = new ConcurrentHashMap();


    public static void main(String[] args) {
        for (int i = 0; i <5 ; i++) {
            Thread thread = new Thread(new SubThread());
            thread.start();
        }
    }

    private static class CollectThread implements Runnable{

        @Override
        public void run() {
            StringBuilder result = new StringBuilder();
            for (Map.Entry<String,Long> workResult:resultMap.entrySet()){
                result.append("["+workResult.getValue()+"]");
            }
            System.out.println("result="+result);
            System.out.println("............do other business");
        }
    }

    private static  class SubThread implements Runnable{

        @Override
        public void run() {
            long id= Thread.currentThread().getId();
            resultMap.put(id+"",id);
            Random random = new Random();
            if (random.nextBoolean()){
                try {
                    Thread.sleep(1000+id);
                    System.out.println("Thread---"+id+"----do something");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                cyclicBarrier.await();
                Thread.sleep(1000+id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Thread---"+id+"----do my business");
        }
    }


}
