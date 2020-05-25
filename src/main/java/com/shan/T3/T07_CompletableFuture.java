package com.shan.T3;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class T07_CompletableFuture {

    private static void delay() {
        int time = new Random().nextInt(500);
        try {
            TimeUnit.MILLISECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("After %s sleep!\n", time);
    }

    static Double getPriceTM() {
        delay();
        return 1.0;
    }

    static Double getPriceTB() {
        delay();
        return 2.0;
    }

    static Double getPriceJD() {
        delay();
        return 3.0;
    }

    public static void main(String[] args) {
        long start, end;

        start = System.currentTimeMillis();

        CompletableFuture<Double> CompletableFutureTM = CompletableFuture.supplyAsync(()->getPriceTM());
        CompletableFuture<Double> CompletableFutureTB = CompletableFuture.supplyAsync(()->getPriceTB());
        CompletableFuture<Double> CompletableFutureJD = CompletableFuture.supplyAsync(()->getPriceJD());

        CompletableFuture.allOf(CompletableFutureTM, CompletableFutureTB, CompletableFutureJD).join();

//        CompletableFuture.supplyAsync(() -> getPriceJD()).thenApply(String::valueOf).thenApply((str)-> "京东价格："+str).thenAccept(System.out::println);

        end = System.currentTimeMillis();
        System.out.println("time:"+(end - start));
//        try {
//            System.in.read();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
