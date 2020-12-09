package com.juc.all.runAThread;

/**
 * @ClassNameExtendThread
 * @Description
 * @Author Yao Xin
 * @Date2020/11/19 9:08
 **/
public class ExtendThread extends Thread{
    public ExtendThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        System.out.println("线程 "+this.getName()+" is running");
    }

    public static void main(String[] args) {
        System.out.println("线程 "+Thread.currentThread().getName()+" is running");
        ExtendThread extendThread = new ExtendThread("private thread");
        extendThread.start();
    }
}
