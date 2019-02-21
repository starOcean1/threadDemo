package com.example.demo.controller;

public class nomalDeadLock {
    private static Object firstLock = new Object();
    private static Object secondLock = new Object();

    private static void firstToSecond(){
        String threadName = Thread.currentThread().getName();
        synchronized (firstLock){
            System.out.println(threadName+"getFirst");
            synchronized (secondLock){
                System.out.println(threadName+"getSecond");
            }
        }
    }

    private static void secondTofirst(){
        String threadName = Thread.currentThread().getName();
        synchronized (secondLock){
            System.out.println(threadName+"getSecond");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (firstLock){
                System.out.println(threadName+"getFirst");
            }
        }
    }

    private static class TestThread extends Thread{
        @Override
        public void run() {
            secondTofirst();
        }
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
        firstToSecond();
    }

}
