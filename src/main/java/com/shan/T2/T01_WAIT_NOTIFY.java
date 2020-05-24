package com.shan.T2;

import java.util.LinkedList;
import java.util.Queue;

public class T01_WAIT_NOTIFY<T>{

    private final LinkedList<T> list = new LinkedList<T>();

    private static final int MAX_SIZE = 5;
    private static final int MIN_SIZE = 0;

    public synchronized void put(T t) {
        while (list.size() == MAX_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        this.notifyAll(); // 通知消费者消费
    }

    public synchronized T get() {
        while (list.size() == MIN_SIZE) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T product = list.removeFirst();
        this.notifyAll(); // 通知生产者生产
        return product;
    }

    public static void main(String[] args) {
        T01_WAIT_NOTIFY<String> t = new T01_WAIT_NOTIFY<String>();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    t.put(Thread.currentThread().getName()+"-产品"+j);
                }
            }, "生产者"+i).start();
        }

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(Thread.currentThread().getName()+"-"+t.get());
                }
            },"消费者"+i).start();
        }
    }
}
