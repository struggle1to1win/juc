package com.juc.all.runAThread.container.container5Notify;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassNameVolitale5Notify
 * @Description
 * @Author Yao Xin
 * @Date2020/12/8 9:16
 **/
public class Volatile5Notify {
    private volatile int size=0;
    private List<Integer> list = new LinkedList<>();
    public void add(Integer i){
        list.add(i);
        size++;
    }
    public int size(){
        return size;
    }

    public static void main(String[] args) {
        Volatile5Notify container = new Volatile5Notify();
        Thread t1 = new Thread(()->{
            System.out.println("t1 start");
           while(container.size!=5){}
            System.out.println("容器数量为:5");
        });
        t1.start();
        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(i);
                container.add(i);
            }
        });
        t2.start();
    }
}
