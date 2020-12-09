package com.juc.all.runAThread;

import java.util.concurrent.Exchanger;

/**
 * @ClassNameExchangerTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/26 9:48
 **/
public class ExchangerTest {
    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();
        new Thread(()->{
            try{
                Object t1 = exchanger.exchange("t1");
                System.out.println("Thread:"+Thread.currentThread().getName()+" param:"+t1.toString());
            }catch (Exception e){}
        },"T1").start();

        new Thread(()->{
            try{
                Object t1 = exchanger.exchange("t2");
                System.out.println("Thread:"+Thread.currentThread().getName()+" param:"+t1.toString());
            }catch (Exception e){}
        },"T2").start();
    }
}
