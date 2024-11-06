package com.company.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;

import static com.company.util.MyLogger.log;
import static com.company.util.MySleep.sleep;

public class BoundedQueueV3 implements BoundedQueue {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV3(int max) {
        this.max = max;
    }

    @Override
    public synchronized void put(String data) {
        while (queue.size() == max) {
            log("[put] queue is already full, producer wait");
            try {
                wait(); // Runnable -> Waiting, 락 반납
                log("[put] producer run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(data);
        log("[put] producer put data, call notify()");
        notify(); // 대기 스레드 -> wait -> blocked
    }

    @Override
    public synchronized String take() {
        while (queue.isEmpty()) {
            log("[take] queue is empty, consumer wait");
            try {
                wait();
                log("[take] consumer run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String data = queue.poll();
        log("[take] consumer get data, call notify()");
        notify(); // 대기 스레드, wait -> blocked
        return data;
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
