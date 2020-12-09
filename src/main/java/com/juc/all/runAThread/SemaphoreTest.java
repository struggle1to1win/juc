package com.juc.all.runAThread;

import java.util.concurrent.Semaphore;

/**
 * @ClassNameSemaphoreTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/26 9:30
 **/
public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2,true);
        for (int i=0;i<10;i++){
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName());
                }catch (Exception e){

                }finally {
                    semaphore.release();
                }
            },"Thread "+i).start();
        }
    }
}
