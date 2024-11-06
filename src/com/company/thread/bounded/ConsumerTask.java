package com.company.thread.bounded;

import static com.company.util.MyLogger.log;

public class ConsumerTask implements Runnable {

    private BoundedQueue queue;

    public ConsumerTask(BoundedQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        log("[consume try ] " + queue);
        queue.take();
        log("[consume end ]" + queue);
    }
}
