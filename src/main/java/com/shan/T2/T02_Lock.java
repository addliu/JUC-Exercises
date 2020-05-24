package com.shan.T2;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T02_Lock<T> {

    private final LinkedList<T> list = new LinkedList<T>();

    private static final int MAX_SIZE = 5;
    private static final int MIN_SIZE = 0;

    private Lock lock = new ReentrantLock();
    private Condition p = lock.newCondition();
    private Condition c = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            while (list.size() == MAX_SIZE) {
                p.await();
            }
            list.add(t);
            c.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T get() {
        T t = null;
        try {
            lock.lock();
            while (list.size() == MIN_SIZE) {
                c.await();
            }
            t = list.removeFirst();
            p.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();

        }
        return t;
    }

    public static void main(String[] args) throws InterruptedException {
        T02_Lock<String> l = new T02_Lock<String>();

        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                for (int j = 0; j < 5; j++) {
                    System.out.println(l.get());
                }
            }, "消费者"+i).start();
        }
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                for (int j = 0; j < 25; j++) {
                    l.put(Thread.currentThread().getName()+"-产品"+j);
                }
            }, "生产者"+i).start();
        }
    }

}
