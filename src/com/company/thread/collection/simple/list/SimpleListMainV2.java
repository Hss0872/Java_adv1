package com.company.thread.collection.simple.list;

import static com.company.util.MyLogger.log;

public class SimpleListMainV2 {

    public static void main(String[] args) throws InterruptedException {

//        test(new BasicList());
//        test(new SyncList());
        test(new SyncProxyList(new BasicList()));

    }

    private static void test(SimpleList list) throws InterruptedException {
        log(list.getClass().getSimpleName());

        Runnable addA = new Runnable() {
            @Override
            public void run() {
                list.add("A");
                log("Thread-1 : list.add(A)");
            }
        };

        Runnable addB = () -> {
            list.add("B");
            log("Thread-1 : list.add(B)");
        };

        Thread thread1 = new Thread(addA, "Thread-1");
        Thread thread2 = new Thread(addB, "Thread-2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(list);
    }
}
