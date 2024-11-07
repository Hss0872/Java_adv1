package com.company.thread.bounded;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.company.util.MyLogger.log;

public class BoundedQueueV4 implements BoundedQueue {

    private final Lock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition(); // 스레드 대기 집합

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    public BoundedQueueV4(int max) {
        this.max = max;
    }

    @Override
    public void put(String data) {
        lock.lock();
        try {
            while (queue.size() == max) {
                log("[put] queue is already full, producer wait");
                try {
                    condition.await();
                    log("[put] producer run");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            queue.offer(data);
            log("[put] producer put data, call notify()");
            condition.signal();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String take() {
        lock.lock();
        try {
            while (queue.isEmpty()) {
                log("[take] queue is empty, consumer wait");
                try {
                    condition.await();
                    log("[take] consumer run");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String data = queue.poll();
            log("[take] consumer get data, call notify()");
            condition.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return queue.toString();
    }
}
