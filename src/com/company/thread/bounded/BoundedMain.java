package com.company.thread.bounded;

import java.util.ArrayList;
import java.util.List;

import static com.company.util.MyLogger.log;
import static com.company.util.MySleep.sleep;

public class BoundedMain {

    public static void main(String[] args) {
        //1. BoundedQueue 선택
//        BoundedQueue queue = new BoundedQueueV2(2);
//        BoundedQueue queue = new BoundedQueueV3(2);
//        BoundedQueue queue = new BoundedQueueV4(2);
//        BoundedQueue queue = new BoundedQueueV5(2);
//        BoundedQueue queue = new BoundedQueueV6_1(2);
//        BoundedQueue queue = new BoundedQueueV6_2(2);
        BoundedQueue queue = new BoundedQueueV6_3(2);

        //2. 생산자 소비자 실행순서 선택, 반드시 하나만 선택!
//        producerFirst(queue);
        consumerFirst(queue);
    }

    private static void consumerFirst(BoundedQueue queue) {
        log("[consumer first start] " + queue.getClass().getSimpleName());
        List<Thread> threads = new ArrayList<>();
        startConsumer(queue, threads);
        printAllState(queue, threads);
        startProducer(queue, threads);
        printAllState(queue, threads);
        log("[consumer first start] end " + queue.getClass().getSimpleName());
    }

    private static void producerFirst(BoundedQueue queue) {
        log("[producer first start] " + queue.getClass().getSimpleName());
        List<Thread> threads = new ArrayList<>();
        startProducer(queue, threads);
        printAllState(queue, threads);
        startConsumer(queue, threads);
        printAllState(queue, threads);
        log("[producer first start] end " + queue.getClass().getSimpleName());
    }

    private static void startConsumer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("consumer start");
        for (int i = 1; i <= 3; i++) {
            Thread consumer = new Thread(new ConsumerTask(queue), "consumer" + i);
            threads.add(consumer);
            consumer.start();
            sleep(100);
        }
    }

    private static void printAllState(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("print all current state, queue data = " + queue);
        for (Thread thread : threads) {
            log(thread.getName() + ": " + thread.getState());
        }

    }

    private static void startProducer(BoundedQueue queue, List<Thread> threads) {
        System.out.println();
        log("producer start");
        for (int i = 1; i <= 3; i++) {
            Thread producer = new Thread(new ProducerTask(queue, "data" + i), "producer" + i);
            threads.add(producer);
            producer.start();
            sleep(100);
        }
    }
}
