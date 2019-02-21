package com.example.demo.controller;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

public class UseExchange {
    private static final Exchanger<Set<String>> exchange = new Exchanger<Set<String>>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setA = new HashSet<>();
                try {
                    setA.add("a");
                    setA.add("b");
                    setA.add("c");
                    setA = exchange.exchange(setA);
                    System.out.println("A:===="+setA);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setB = new HashSet<>();
                try {
                    setB.add("d");
                    setB.add("e");
                    setB.add("f");
                    setB = exchange.exchange(setB);
                    System.out.println("B:===="+setB);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setC = new HashSet<>();
                try {
                    setC.add("G");
                    setC.add("H");
                    setC.add("I");
                    setC = exchange.exchange(setC);
                    System.out.println("C:===="+setC);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> setD = new HashSet<>();
                try {
                    setD.add("J");
                    setD.add("K");
                    setD.add("M");
                    setD = exchange.exchange(setD);
                    System.out.println("D:===="+setD);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }
}
