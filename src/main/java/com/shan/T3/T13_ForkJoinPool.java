package com.shan.T3;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * forkJoinPool实现整数相加
 */
public class T13_ForkJoinPool {

    static int[] nums = new int[1000000];
    static final int MAX_NUM = 50000;
    static Random r = new Random();

    static {
        for(int i=0; i<nums.length; i++) {
            nums[i] = r.nextInt(100);
        }

        System.out.println("---" + Arrays.stream(nums).sum() + "---"); //stream api
    }

    // 无返回值的任务
    static class AddTask extends RecursiveAction {
        int start, end;

        AddTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                System.out.println("from "+start+" to "+end+" sum "+sum);
            } else {
                int middle = (start+end)/2;
                AddTask task1 = new AddTask(start, middle);
                AddTask task2 = new AddTask(middle, end);
                task1.fork();
                task2.fork();
            }
        }
    }

    // 有返回值的任务
    static class AddTaskRet extends RecursiveTask<Long> {

        int start, end;

        AddTaskRet(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            if (end - start <= MAX_NUM) {
                long sum = 0L;
                for (int i = start; i < end; i++) {
                    sum += nums[i];
                }
                return sum;
            } else {
                int middle = (start+end)/2;
                AddTaskRet task1 = new AddTaskRet(start, middle);
                AddTaskRet task2 = new AddTaskRet(middle, end);
                task1.fork();
                task2.fork();
                return task1.join() + task2.join();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new T13_ForkJoinPool();

        ForkJoinPool fjp = new ForkJoinPool();
        AddTaskRet task = new AddTaskRet(0, nums.length);
        fjp.execute(task);
        long result = task.join();//阻塞
        System.out.println(result);
//        System.in.read();
    }


}
