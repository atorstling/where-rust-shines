package se.cygni.atomic;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class App {

    static int i = 0;

    public static void main(String[] args) throws Exception {
        final int threadCount = 10;
        final int incrementsPerThread = 100000;

        final ArrayList<Thread> threads = new ArrayList<>();
        for (int j = 0; j < threadCount; j++) {
            threads.add(new Thread(() -> {
                for (int j1 = 0; j1 < incrementsPerThread; j1++) {
                    i += 1;
                }
            }));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.printf("Result of %s*%s increments: %d", threadCount, incrementsPerThread, i);
    }
}
