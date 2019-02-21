package com.example.demo.controller;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class FindDirsFiles extends RecursiveAction{
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        int count=0;
        int dirCount=0;
        List<FindDirsFiles> subTasks = new ArrayList<>();
        File[] files =path.listFiles();
        if (files!=null){
            for (File file : files) {
                if (file.isDirectory()){  
                    subTasks.add(new FindDirsFiles(file));
                }else{
                    if (file.getAbsolutePath().endsWith("txt")){
                        System.out.println("文件名称："+file.getPath()+"---当前线程"+Thread.currentThread().getName());
                    }
                }
            }
        }
        if (!subTasks.isEmpty()){
            for (FindDirsFiles subTask : invokeAll(subTasks)) {
              subTask.join();
            }
        }
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        FindDirsFiles sumDirsFiles = new FindDirsFiles(new File("F:/"));
        forkJoinPool.execute(sumDirsFiles);

        try {
            Thread.sleep(50);
            System.out.println(" I AM GOOD");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //保证主线程最后结束。join是一个阻塞的方法，一定会先执行完sumDirsFiles，主线程才会结束
        sumDirsFiles.join();
        System.out.println("END");
    }
}
