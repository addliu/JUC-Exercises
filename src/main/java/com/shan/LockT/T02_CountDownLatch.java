package com.shan.LockT;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class T02_CountDownLatch {

    public static void main(String[] args) {

        // 指定需要 countDown 多少次 await 的线程才能运行
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
            // 阻塞线程，等 5 次count down后才能继续执行
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread()+" done. ");
    }

}
