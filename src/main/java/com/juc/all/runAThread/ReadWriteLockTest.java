package com.juc.all.runAThread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassNameReadWriteLockTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/26 9:11
 **/
public class ReadWriteLockTest {
    public static void main(String[] args) {
        ReentrantLock lock1 = new ReentrantLock();
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        Runnable readR = new Runnable() {
            @Override
            public void run() {
                readLock.lock();
//                lock1.lock();
                try{
                    Thread.sleep(500);
                }catch (Exception e){}finally {
                    readLock.unlock();
//                    lock1.unlock();
                }
                System.out.println("reading");
            }
        };
        Runnable writeR = new Runnable() {
            @Override
            public void run() {
                try{
//                    lock1.lock();
                    writeLock.lock();
                }catch (Exception e){}finally {
                    writeLock.unlock();
//                    lock1.unlock();
                }
                System.out.println("writing");
            }
        };
        for(int i=0;i<10;i++){
            new Thread(readR).start();
        }
        for(int i=0;i<2;i++){
            new Thread(writeR).start();
        }
    }
}
