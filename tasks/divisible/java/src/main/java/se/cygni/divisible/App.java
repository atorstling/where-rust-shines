package se.cygni.divisible;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class App {

    //static AtomicInteger i = new AtomicInteger(0);
    static int i = 0;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final int nThreads = 10;

        final CountDownLatch started = new CountDownLatch(nThreads);
        final CountDownLatch mayBegin = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(nThreads);
        for (int j = 0; j < nThreads; j++) {
            new Thread(new Runnable() {
                public void run() {
                    try {
                        started.countDown();
                        mayBegin.await(1, TimeUnit.SECONDS);
                        for (int j = 0; j < 1000; j++) {
                            i += 1;
                            //i.incrementAndGet();
                        }
                        done.countDown();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        started.await(1, TimeUnit.SECONDS);
        mayBegin.countDown();
        final boolean success = done.await(5, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout while waiting for threads to terminate");
        }
        System.out.println("Sum: " + i);
    }
}
