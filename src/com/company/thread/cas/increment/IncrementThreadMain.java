package com.company.thread.cas.increment;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static com.company.util.ThreadUtils.sleep;

public class IncrementThreadMain {

    public static final int THREAD_COUNT = 1000;

    public static void main(String[] args) throws InterruptedException {
//        test(new BasicInteger());
//        test(new VolatileInteger());
        test(new SyncInteger());
    }

    private static void test(IncrementInteger incrementInteger) throws InterruptedException {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sleep(10); // 다른 스레드와 동시 실행을 위해 잠시 쉬었다가 실행
                incrementInteger.increment();
            }
        };

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < THREAD_COUNT; i++) {
            Thread thread = new Thread(runnable);
            threads.add(thread);
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        int result = incrementInteger.get();
        System.out.println(incrementInteger.getClass().getSimpleName() + " result : " + result);
    }
}