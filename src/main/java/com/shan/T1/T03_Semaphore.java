package com.shan.T1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量 Semaphore
 */
public class T03_Semaphore {

    public static void main(String[] args) {
        // 只有一个资源，保证只能有一个线程执行
        Semaphore semaphore1 = new Semaphore(1, true);

        Thread t1 = new Thread(()->{
            for (char i = 'A'; i < 'A'+26; i++) {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                semaphore1.release();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        Thread t2 = new Thread(()->{
            for (int i = 1; i < 27; i++) {
                try {
                    semaphore1.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i);
                semaphore1.release();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");

        t1.start();
        t2.start();

    }

}
