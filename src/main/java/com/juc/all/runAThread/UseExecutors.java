package com.juc.all.runAThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassNameUseExcutors
 * @Description
 * @Author Yao Xin
 * @Date2020/11/19 9:18
 **/
public class UseExecutors implements Runnable{
    int count=0;

    public void run() {
        count++;
        System.out.println("线程 "+Thread.currentThread().getName()+" is running , count = "+count);
    }

    public static void main(String[] args) {
        UseExecutors useExecutors = new UseExecutors();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i<1000;i++){
            executorService.submit(useExecutors);
        }
        executorService.shutdown();
    }
}
