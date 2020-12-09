package com.juc.all.runAThread;

/**
 * @ClassNameImplementsRunnable
 * @Description
 * @Author Yao Xin
 * @Date2020/11/19 9:12
 **/
public class ImplementsRunnable implements Runnable{
    public void run() {
        System.out.println("线程 "+Thread.currentThread().getName()+" is running");
    }

    public static void main(String[] args) {
        System.out.println("线程 "+Thread.currentThread().getName()+" is running");
        ImplementsRunnable runnable = new ImplementsRunnable();
        Thread thread = new Thread(runnable,"private");
        thread.start();
    }
}
