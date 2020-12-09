package com.juc.all.runAThread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassNameCyclicBarrierTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/24 21:20
 **/
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5,()-> System.out.println("人满发车"));
        for(int i=0;i<9;i++){
            new Thread(()->{
                try {
                    cyclicBarrier.await();
                    System.out.println(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
