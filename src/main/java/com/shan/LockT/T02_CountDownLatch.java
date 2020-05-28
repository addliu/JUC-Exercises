package com.shan.LockT;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T02_CountDownLatch {

    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName()+" count down.");
            },"count"+i).start();
        }

        try {
            System.out.println(Thread.currentThread()+" await. ");
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+" done. ");
    }

}
