package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SchelduleWork implements Runnable {
    private final static int  Normal=0;
    private final static int  HasException=-1;
    private final static int  processException=1;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int taskType;

    public SchelduleWork(int taskType) {
        this.taskType = taskType;
    }

    @Override
    public void run() {
        if (taskType==HasException){
            System.out.println(format.format(new Date())+"Exception been made");
            throw new RuntimeException("ExceptionHappen");
        }else if (taskType ==processException){
            try {
                System.out.println(format.format(new Date())+"Exception been made");
                throw new RuntimeException("ExceptionHappen");
            } catch (RuntimeException e) {
                System.out.println("we catch exception");
            }
        }else {
            System.out.println("nomal..............");
        }

    }
}
