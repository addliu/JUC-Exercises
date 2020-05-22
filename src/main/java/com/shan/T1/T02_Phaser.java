package com.shan.T1;

import java.util.concurrent.Phaser;

/**
 * 阶段锁 phaser
 * 方案不能完全满足需求
 * 只能保证{A,1}一定在{B,2}之前输出，无法保证{A,1}输出的顺序为A1，有可能为1A
 */

public class T02_Phaser {

    public static void main(String[] args) {
        // 两个参与者
        Phaser phaser = new Phaser(2);

        Thread t1 = new Thread(()->{
            for (char i = 'A'; i < 'A'+26; i++) {
                System.out.print(i);
                phaser.arriveAndAwaitAdvance();
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 1; i < 27; i++) {
                System.out.print(i);
                phaser.arriveAndAwaitAdvance();
            }
        });

        t1.start();
        t2.start();

    }

}
