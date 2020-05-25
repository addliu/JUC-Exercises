package com.shan.T3;

import java.util.concurrent.*;

public class T03_Callable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<String> c = new Callable<String>() {
            @Override
            public String call() {
                return "Hello Callable";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String > future = executorService.submit(c);

        System.out.println(future.get());

        executorService.shutdown();

    }

}
