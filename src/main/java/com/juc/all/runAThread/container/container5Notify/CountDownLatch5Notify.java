package com.juc.all.runAThread.container.container5Notify;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassNameCountDownLatch5Notify
 * @Description
 * @Author Yao Xin
 * @Date2020/12/8 17:38
 **/
public class CountDownLatch5Notify {
    public static void main(String[] args) {
        Volatile5Notify container = new Volatile5Notify();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread t1 = new Thread(()->{
            System.out.println("t1 start");
            try{
                countDownLatch.await();
            }catch (Exception e){}
            System.out.println("t1 end");
        });
        t1.start();

        Thread t2 = new Thread(()->{
            for(int i=0;i<10;i++){
                container.add(i);
                System.out.println(i);
                if(container.size()==5){
                    countDownLatch.countDown();
                }
            }
        });
        t2.start();
    }
}
