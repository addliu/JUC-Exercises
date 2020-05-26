package com.shan.T3;

import java.util.concurrent.Executor;

/**
 * Executor 执行Runnable方法
 */
public class T01_MyExecutor implements Executor {
    @Override
    public void execute(Runnable command) {
        command.run();
    }

    public static void main(String[] args) {
        new T01_MyExecutor().execute(()-> System.out.println("hello"));
    }

}
