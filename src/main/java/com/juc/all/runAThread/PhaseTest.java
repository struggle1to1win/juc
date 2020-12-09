package com.juc.all.runAThread;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @ClassNamePhaseTest
 * @Description
 * @Author Yao Xin
 * @Date2020/11/24 22:02
 **/
public class PhaseTest {

    public static class MarriageWay implements Runnable{
        private MarriagePhase phaser;
        private Random r = new Random();

        public MarriageWay(MarriagePhase phaser) {
            this.phaser = phaser;
        }

        @Override
        public void run() {
            arrive();
            eat();
            leave();
            hug();
        }

        public void arrive(){
            sleepM(r.nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"到了");
            phaser.arriveAndAwaitAdvance();
            System.out.println(Thread.currentThread().getName()+"入席");
        }

        public void eat(){
            sleepM(r.nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"吃了");
            phaser.arriveAndAwaitAdvance();
        }

        public void leave(){
            sleepM(r.nextInt(1000));
            System.out.println(Thread.currentThread().getName()+"走了");
            phaser.arriveAndAwaitAdvance();
        }

        public void hug(){
            if(Thread.currentThread().getName().equals("新娘")||Thread.currentThread().getName().equals("新郎")){
                sleepM(r.nextInt(1000));
                System.out.println(Thread.currentThread().getName()+"来了");
                phaser.arriveAndAwaitAdvance();
            }else {
                phaser.arriveAndDeregister();
            }

        }

        public void sleepM(int s){
            try {
                Thread.sleep(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class MarriagePhase extends Phaser{
        public MarriagePhase(int parties) {
            super(parties);
        }

        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            switch (phase){
                case 0:
                    System.out.println("所有人到齐了");
                    System.out.println();
                    return false;
                case 1:
                    System.out.println("所有人吃完了");
                    System.out.println();
                    return false;
                case 2:
                    System.out.println("所有人离开了");
                    System.out.println();
                    return false;
                case 3:
                    System.out.println("新娘新郎入洞房");
                    System.out.println();
                    return true;
                default:
                    return true;
            }
        }
    }

    public static void main(String[] args) {
        MarriagePhase phaser = new MarriagePhase(7);
        Thread xinniang = new Thread(new MarriageWay(phaser),"新娘");
        Thread xinlang = new Thread(new MarriageWay(phaser),"新郎");

        for(int i=0;i<5;i++){
            new Thread(new MarriageWay(phaser),""+i).start();
        }

        xinniang.start();
        xinlang.start();
    }
}
