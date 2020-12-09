package com.juc.all.runAThread.container.container5Notify;

import java.util.Date;
import java.util.Objects;

/**
 * @ClassNameSynchroized5Notify
 * @Description
 * @Author Yao Xin
 * @Date2020/12/8 9:31
 **/
public class Synchronized5Notify {
    public static void main(String[] args) {
        Volatile5Notify container = new Volatile5Notify();
        Object lock = new Object();
        Thread t1 = new Thread(()->{
            synchronized (lock){
                try{
                    System.out.println("t1 等待");
                    lock.wait();
                    System.out.println("t1 执行");
                }catch (Exception e){}finally {
                    lock.notifyAll();
                }
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            synchronized (lock){
                for(int i=0;i<10;i++){
                    System.out.println(i);
                    container.add(i);
                    if(container.size()==5){
                        try{
                            lock.notifyAll();
                            lock.wait();
                        }catch (Exception e){}finally {
                        }
                    }
                }
            }
        });
        t2.start();
    }
}
