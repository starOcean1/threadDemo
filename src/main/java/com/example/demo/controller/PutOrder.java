package com.example.demo.controller;

import java.util.concurrent.DelayQueue;

public class PutOrder implements Runnable {
    private DelayQueue<ItemVO<Order>> queue;

    public PutOrder(DelayQueue<ItemVO<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Order order = new Order("TB13874612",10000);
        ItemVO<Order> item= new ItemVO<Order>(5000,order);
        queue.offer(item);
        System.out.println("订单5秒后过期:"+order.getOrderNo()+";"+order.getOrderMoney());

        Order order2 = new Order("TB2451235",10000);
        ItemVO<Order> item2= new ItemVO<Order>(8000,order2);
        queue.offer(item2);
        System.out.println("订单8秒后过期:"+order2.getOrderNo()+";"+order2.getOrderMoney());


    }
}
