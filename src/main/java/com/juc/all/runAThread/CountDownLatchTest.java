package com.juc.all.runAThread;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassNameCountDownLacthTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/24 20:55
 **/
public class CountDownLatchTest {
    public static void main(String[] args) {
        useJoin();
    }

    public static void useJoin(){
        Thread[] ts = new Thread[100];
        for(int i=0;i<ts.length;i++){
            ts[i] = new Thread(()->{
                System.out.println("1");
            });
        }
        for (int i=0;i<ts.length;i++){
            ts[i].start();
        }
        for(int i=0;i<ts.length;i++){
            try {
                ts[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("latch off");
    }

    public static void useCountDownLatch(){
        Thread[] ts = new Thread[100];
        CountDownLatch latch = new CountDownLatch(ts.length);
        for(int i=0;i<ts.length;i++){
            ts[i] = new Thread(()->{
                System.out.println("1");
                latch.countDown();
            });
        }
        for (int i=0;i<ts.length;i++){
            ts[i].start();
        }
        try {
            latch.await();
        } catch (Exception e) {
        }
        System.out.println("latch off");
    }
}
