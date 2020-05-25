package com.shan.T3;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class T06_Future {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(()->{
            TimeUnit.SECONDS.sleep(5);
            return "FutureTask";
        });
        new Thread(futureTask).start();

//        futureTask.run();
        System.out.println(futureTask.get());
    }

}
