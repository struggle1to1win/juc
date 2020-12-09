package com.juc.all.runAThread.container.containerSynchronized;

import java.util.LinkedList;
import java.util.List;

public class SynchronizedContainer {
    private List<Integer> list = new LinkedList<>();
    int max = 10;
    int count;

    public synchronized void add(Integer i){
        while (count==max){
            try{
                this.wait();
            }catch (Exception e){
            }
        }
        list.add(i);
        count++;
        this.notifyAll();
    }

    public synchronized int get(){
        while (count==0){
            try{
                this.wait();
            }catch (Exception e){
            }
        }
        Integer remove = list.remove(0);
        count--;
        this.notifyAll();
        return remove;
    }

    public static void main(String[] args) {
        SynchronizedContainer container = new SynchronizedContainer();

        for(int i=0;i<10;i++){
            new Thread(()->{
                for(int j=0;j<5;j++){
                    System.out.println(container.get());
                }
            }).start();
        }

        for(int i=0;i<2;i++){
            new Thread(()->{
                for(int j=0;j<25;j++){
                    container.add(j);
                }
            }).start();
        }
    }
}
