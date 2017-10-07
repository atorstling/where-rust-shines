package se.cygni.divisible;

import java.util.concurrent.*;

public class App {

    static int i;

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        final ExecutorService es = Executors.newFixedThreadPool(10);
        final ExecutorCompletionService ecs = new ExecutorCompletionService(es);

        ecs.submit(new Callable() {
            public Object call() throws Exception {
                i += 1;
                return null;
            }
        });
        
        Future f;
        while((f = ecs.poll()) != null) {
            f.get(1, TimeUnit.SECONDS);
        }
        es.shutdown();
        final boolean success = es.awaitTermination(1, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout waiting for termination");
        }
    }
}
