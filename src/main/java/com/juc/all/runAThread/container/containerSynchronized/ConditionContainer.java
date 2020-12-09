package com.juc.all.runAThread.container.containerSynchronized;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionContainer {
    private List<Integer> list = new LinkedList<>();
    int max = 10;
    int count;

    private ReentrantLock reentrantLock = new ReentrantLock();
    Condition consumer = reentrantLock.newCondition();
    Condition producer = reentrantLock.newCondition();

    public void add(Integer i){
        try{
            reentrantLock.lock();
            while (count == max) {
                producer.await();
            }
            list.add(i);
            count++;
            consumer.signalAll();
        }catch (Exception e){}finally {
            reentrantLock.unlock();
        }
    }

    public Integer get(){
        Integer remove = null;
        try {
            reentrantLock.lock();
            while (count == 0) {
                consumer.await();
            }
            remove = list.remove(0);
            count--;
            producer.signalAll();
        } catch (Exception e) {
        } finally {
            reentrantLock.unlock();
        }
        return remove;
    }

    public static void main(String[] args) {
        ConditionContainer container = new ConditionContainer();

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
