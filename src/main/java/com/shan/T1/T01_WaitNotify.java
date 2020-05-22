package com.shan.T1;

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

        t2.start();

    }

}
