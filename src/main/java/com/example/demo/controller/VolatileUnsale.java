package com.example.demo.controller;

public class VolatileUnsale {
    private static class VolatileVar implements Runnable{
    private  int a =0;
        @Override
        public void run() {
            a++;
            System.out.println(Thread.currentThread().getName()+"====="+a);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            a++;
            System.out.println(Thread.currentThread().getName()+"====="+a);

        }
    }

    public static void main(String[] args) {
        Thread thread1= new Thread(new VolatileVar());
        Thread thread2= new Thread(new VolatileVar());
        Thread thread3= new Thread(new VolatileVar());
        Thread thread4= new Thread(new VolatileVar());
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
    }

}
