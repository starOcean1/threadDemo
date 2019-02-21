package com.example.demo.controller;

public class EndThread {

    private static class UserThread extends Thread{
        @Override
        public void run() {
            while (!isInterrupted()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("catch："+Thread.currentThread().isInterrupted());
                    interrupt();
                    e.printStackTrace();
                }
                System.out.println("当前线程正在运行："+Thread.currentThread().isInterrupted());
            }
            System.out.println("当前线程状态："+Thread.currentThread().isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException{
        UserThread userThread = new UserThread();
        userThread.start();
        Thread.sleep(500);
        userThread.interrupt();

    }
}
