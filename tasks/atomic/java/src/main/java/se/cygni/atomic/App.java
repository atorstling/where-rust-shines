package se.cygni.atomic;

import java.util.concurrent.*;

public class App {

    static int i = 0;

    public static void main(String[] args) throws Exception {
        final int threadCount = 10;
        final int loopsPerThread = 100000;

        final CountDownLatch everyoneReady = new CountDownLatch(threadCount);
        final CountDownLatch startSignal = new CountDownLatch(1);
        final CountDownLatch everyoneDone = new CountDownLatch(threadCount);

        for (int j = 0; j < threadCount; j++) {
            final Thread t = new Thread(() -> {
                everyoneReady.countDown();
                await(startSignal, "Timeout waiting for start signal");
                for (int j1 = 0; j1 < loopsPerThread; j1++) {
                    i += 1;
                }
                everyoneDone.countDown();
            });
            t.start();
        }
        await(everyoneReady, "Timeout waiting for threads to start");
        startSignal.countDown();
        await(everyoneDone, "Timeout while waiting for threads to terminate");
        System.out.printf("Result of %s*%s increments: %d", threadCount, loopsPerThread, i);
    }

    private static void await(CountDownLatch latch, String message) {
        final boolean success;
        try {
            success = latch.await(1, TimeUnit.SECONDS);
            if (!success) {
                throw new RuntimeException(message);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
