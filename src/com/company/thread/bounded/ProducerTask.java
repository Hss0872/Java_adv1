package com.company.thread.bounded;

import static com.company.util.MyLogger.log;

public class ProducerTask implements Runnable {

    private BoundedQueue queue;
    private String request;

    public ProducerTask(BoundedQueue queue, String request) {
        this.queue = queue;
        this.request = request;
    }

    @Override
    public void run() {
        log("[try request] " + request + " -> " + queue);
        queue.put(request);
        log("[request end] " + request + " -> " + queue);
    }
}
