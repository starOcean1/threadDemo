package com.example.demo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumDirsFiles extends RecursiveTask<Integer>{
          private File path;

    public SumDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected Integer compute() {
        int count=0;
        int dirCount=0;
        List<SumDirsFiles> subTasks = new ArrayList<>();
        File[] files =path.listFiles();
        if (files!=null){
            for (File file : files) {
                if (file.isDirectory()){
                    subTasks.add(new SumDirsFiles(file));
                    dirCount++;
                }else{
                    count++;
                }
            }
        }


        if (!subTasks.isEmpty()){
            for (SumDirsFiles subTask : invokeAll(subTasks)) {
                count += subTask.join();
            }
        }
        System.out.println("目录："+path.getAbsolutePath()+"包含的子目录个数；"+dirCount);
        System.out.println("目录："+path.getAbsolutePath()+"包含的文件个数；"+dirCount);
        return count;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        SumDirsFiles sumDirsFiles = new SumDirsFiles(new File("F:/"));
        forkJoinPool.invoke(sumDirsFiles);
        System.out.println(sumDirsFiles.join());
    }
}













