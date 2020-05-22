package com.shan.T1;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用lockSupport 的 park 和 unpark 方法
 */

public class T04_LockSupport {

    private static Thread t1=null, t2 = null;


    public static void main(String[] args) {

        t1 = new Thread(() -> {

            for (char i = 'A'; i < 'A'+26; i++) {
                System.out.print(i);
                LockSupport.unpark(t2);
                LockSupport.park();
            }

        }, "t1");

        t2 = new Thread(() -> {
            for (int i = 1; i < 27; i++) {
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();

    }

}
