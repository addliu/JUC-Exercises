package com.shan.T3;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class T12_WorkStealingPool {

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newWorkStealingPool();

        service.execute(new R(1000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));
        service.execute(new R(2000));

        // 阻塞主线程
        System.in.read();
    }

    static class R implements Runnable {

        int time;

        R(int time) {
            this.time = time;
        }

        @Override
        public void run() {
            try {
                TimeUnit.MILLISECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(time + "-" + Thread.currentThread().getName());
        }
    }

}
