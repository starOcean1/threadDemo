package com.example.demo.controller;

public interface ITeskProcess<T,R> {
    TaskResult<R>  taskExce(T data);
}
