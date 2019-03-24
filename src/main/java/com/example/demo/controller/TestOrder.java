package com.example.demo.controller;

import java.util.concurrent.DelayQueue;

public class TestOrder {

    public static void main(String[] args) throws InterruptedException{
        DelayQueue<ItemVO<Order>> queue = new DelayQueue<ItemVO<Order>>();
        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();
        //每格500毫秒打印数字
        for (int i = 0; i <15 ; i++) {
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
