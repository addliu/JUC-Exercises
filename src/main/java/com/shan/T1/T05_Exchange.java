package com.shan.T1;

import java.util.concurrent.Exchanger;

/**
 * 无法完全保证顺序一致
 */
public class T05_Exchange {

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<String>();

        new Thread(()->{
            for (char i = 'A'; i < 'A'+26; i++) {
                try {
                    String temp = exchanger.exchange(""+i);
                    System.out.print(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 1; i < 27; i++) {
                try {
                    String temp = exchanger.exchange(""+i);
                    System.out.print(temp);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


    }

}
