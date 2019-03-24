package com.example.demo.controller;


import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;

public class FetchOrder implements Runnable {
    private DelayQueue<ItemVO<Order>> queue;

    public FetchOrder(DelayQueue<ItemVO<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true){
            try {
                ItemVO<Order> item=queue.take();
                Order order = (Order) item.getData();
                System.out.println("get  from  queue:"+"data="+order.getOrderMoney()+";"+order.getOrderNo());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
