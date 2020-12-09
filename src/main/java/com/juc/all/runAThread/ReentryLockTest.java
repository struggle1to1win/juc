package com.juc.all.runAThread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassNameReentryLockTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/24 20:12
 **/
public class ReentryLockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Thread t1 = new Thread(()->{
            try{
                lock.lock();
                for (int i=0;i<5;i++){
                    Thread.sleep(1000);
                    System.out.println(i);
                }
            }catch (Exception e){
                System.out.println("interrupted!");
            }finally {
                lock.unlock();
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            try{
//                lock.lock();
//                boolean locked = lock.tryLock(2, TimeUnit.SECONDS);
                lock.lockInterruptibly();
                System.out.println("m2.....");
            }catch (Exception e){
                System.out.println("interrupted!");
            }finally {
                lock.unlock();
            }
        });
        t2.start();
        t2.interrupt();
        Thread t3 = new Thread(()->{
            try{
                lock.lock();
                System.out.println("m3.....");
            }catch (Exception e){
                System.out.println("interrupted!");
            }finally {
                lock.unlock();
            }
        });
        t3.start();
    }
}
