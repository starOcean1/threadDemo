package com.example.demo.controller;

public class EndRunnable {

    private static class  UserRunnable implements Runnable{

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()){
                System.out.println("当前线程正在运行："+Thread.currentThread().isInterrupted());
            }
            System.out.println("当前线程状态："+Thread.currentThread().isInterrupted());
        }
        }

    public static void main(String[] args) throws InterruptedException{
        UserRunnable userRunnable = new UserRunnable();
        Thread thread = new Thread(userRunnable);
        thread.start();
        Thread.sleep(20);
        thread.interrupt();
    }

}
