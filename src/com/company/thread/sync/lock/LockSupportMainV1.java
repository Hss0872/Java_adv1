package com.company.thread.sync.lock;

import java.util.concurrent.locks.LockSupport;

import static com.company.util.MyLogger.log;
import static com.company.util.MySleep.sleep;

public class LockSupportMainV1 {

    public static void main(String[] args) {

        Thread thread1 = new Thread(new ParkTest(), "Thread -1");
        thread1.start();

        sleep(100);
        log("Thread -1 state : " + thread1.getState());

        log("main -> unpark");
//        LockSupport.unpark(thread1);

        thread1.interrupt();
    }

    static class ParkTest implements Runnable {

        @Override
        public void run() {
            log("park start");
            LockSupport.park();
            log("park end, state = " + Thread.currentThread().getState());
            log("interrupt state : " + Thread.currentThread().isInterrupted());
        }
    }
}
