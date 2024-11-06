package com.company.thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static com.company.util.MyLogger.log;
import static com.company.util.MySleep.sleep;

public class LockSupportMainV2 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTest(), "Thread -1");
        thread1.start();

        sleep(100);
        log("Thread -1 state : " + thread1.getState());
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park start");
            LockSupport.parkNanos(2000_000000);
            log("park end, state = " + Thread.currentThread().getState());
            log("interrupt state : " + Thread.currentThread().isInterrupted());
        }
    }
}
