package com.company.thread.cas.spinlock;

import static com.company.util.MyLogger.log;

public class SpinLockMain {

    public static void main(String[] args) {
//        SpinLockBad spinlock = new SpinLockBad();
        SpinLock spinlock = new SpinLock();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                spinlock.lock();
                try {
                    log("비지니스 로직 실행");
                } finally {
                    spinlock.unlock();
                }
            }
        };

        Thread thread1 = new Thread(task, "thread - 1");
//        Thread thread2 = new Thread(task, "thread - 2");

        thread1.start();
//        thread2.start();
    }
}
