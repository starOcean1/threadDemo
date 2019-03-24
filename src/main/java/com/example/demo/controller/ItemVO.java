package com.example.demo.controller;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ItemVO<T> implements Delayed {

    private long activeTime;
    private T Data;

    public ItemVO(long activeTime, T data) {
        super();
        this.activeTime = activeTime+System.nanoTime();
        Data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return Data;
    }

    //返回元素的剩余时间
    @Override
    public long getDelay(TimeUnit unit) {
        long t = unit.convert(this.activeTime-System.nanoTime(),TimeUnit.NANOSECONDS);
        return t;
    }

    //按照原始剩余时间排序
    @Override
    public int compareTo(Delayed o) {
        long d = getDelay(TimeUnit.NANOSECONDS)-o.getDelay(TimeUnit.NANOSECONDS);

        return (d==0)?0:(d>0?1:-1);
    }
}
