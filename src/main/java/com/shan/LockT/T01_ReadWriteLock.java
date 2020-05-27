package com.shan.LockT;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 共享锁、排他锁
 */

public class T01_ReadWriteLock {

    public static void main(String[] args) {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                // 读锁
                try {
                    readLock.lock();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("共享锁"+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    readLock.unlock();
                }
            }, "t"+i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                // 写锁
                try {
                    writeLock.lock();
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println("排他锁"+Thread.currentThread().getName());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    writeLock.unlock();
                }
            }, "t"+i).start();
        }

    }

}
