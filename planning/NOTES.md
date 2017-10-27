Thread-safety of primitive types in Java https://stackoverflow.com/a/9278798/83741
Atomic solution: use std::sync::atomic::{AtomicUsize, Ordering};
i.fetch_add(1, Ordering::Relaxed);

public static void main(String[] args) throws Exception {
        final int threadCount = 10;
        final int incrementsPerThread = 100000;

        final ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        for (int j = 0; j < threadCount; j++) {
            executorService.submit(() -> {
                for (int j1 = 0; j1 < incrementsPerThread; j1++) {
                    i += 1;
                }
            });
        }

        executorService.shutdown();
        final boolean success = executorService.awaitTermination(1, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout waiting for executor service to shut down");
        }

        System.out.printf("Result of %s*%s increments: %d", threadCount, incrementsPerThread, i);
    }

Mutex:
Rust guarantees that you protect all the data that you access with a Mutex.
In Java we could do different things which violates this:
  * Increment a counter in parallel with a list, but only synchronize on the
    list. -> not guaranteed to be in sync
    Rust would not allow this without holding at least a different mutex on the counter
  * Synchronize on a mutable variable. Rust would forbid doing this (I guess?)