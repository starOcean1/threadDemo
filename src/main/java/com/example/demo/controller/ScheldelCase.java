package com.example.demo.controller;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheldelCase {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(1);
        scheduled.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("this task run once");
            }
        },3000, TimeUnit.MICROSECONDS);

        scheduled.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                System.out.println("FixDelay start ");
                    Thread.sleep(2000);
                System.out.println("FixDelay end ");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },1000,3000,TimeUnit.MICROSECONDS);
    }
}
