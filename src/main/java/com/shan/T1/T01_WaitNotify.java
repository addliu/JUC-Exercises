package com.shan.T1;

import java.util.concurrent.TimeUnit;

/**
 * 使用Synchronized锁与wait、notify来完成
 */

public class T01_WaitNotify {

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
            synchronized (lock) {
                for (char i = 'A'; i < 'A'+26; i++) {
                    System.out.print(i);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                }
            }
        });

        t1.start();

        Thread t2 = new Thread(()->{
            synchronized (lock) {
                for (int i = 1; i <= 26; i++) {
                    System.out.print(i);
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        // 需让t1线程先执行，t2线程后执行
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();

    }

}
