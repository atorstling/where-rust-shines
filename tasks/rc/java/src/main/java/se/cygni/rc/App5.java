package se.cygni.rc;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

// Candidate 2?
public class App5 {

    static class User {
        int id;
        String name;

        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    static class System {
        User root;
        final List<User> otherUsers;
        static int nextId = 0;

        public System(User root) {
            this.root = root;
            otherUsers = new ArrayList<>();
        }

        public void addUser(String name, boolean assignRoot) {
            synchronized (root) {
                if (assignRoot) {
                    User oldRoot = root;
                    oldRoot.id = nextId++;
                    root = new User(0, name);
                } else {

                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        final int writers = 10;
        final ExecutorService executorService = Executors.newFixedThreadPool(writers);
        final ArrayList<Future> futures = new ArrayList<>();



        for (int i = 0; i < writers; i++) {
            int currentI = i;

        }
        for (Future future : futures) {
            future.get(1, TimeUnit.SECONDS);
        }

        executorService.shutdown();
        final boolean success = executorService.awaitTermination(1, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout waiting for executorService to terminate");
        }
        java.lang.System.out.println("OK");
    }
}
