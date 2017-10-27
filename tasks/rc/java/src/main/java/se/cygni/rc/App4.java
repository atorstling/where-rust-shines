package se.cygni.rc;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class App4 {

    static class User {
        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String firstName;
        public String lastName;
    }

    static class Users {
        List<User> users = Collections.synchronizedList(new ArrayList<User>());

        public void add(User user) {
            users.add(user);
        }

        public Set<String> getLastNames() {
            return users.stream()
                    .map(u -> u.lastName)
                    .collect(Collectors.toSet());
        }
    }

    public static void main(String[] args) throws Exception {
        final int writers = 100;
        final ExecutorService executorService = Executors.newFixedThreadPool(writers);
        final ArrayList<Future> futures = new ArrayList<>();
        final Users users = new Users();

        final CountDownLatch found99sson = new CountDownLatch(1);

        futures.add(executorService.submit(() -> {
            while (true) {
                if (users.getLastNames().contains("99sson")) {
                    System.out.println("Found Pelle 99sson!");
                    break;
                }
                Thread.sleep(10);
            }
            return null;
        }));

        for (int i = 0; i < writers; i++) {
            int currentI = i;
            futures.add(executorService.submit(() ->
                    users.add(new User("Pelle", currentI + "sson"))));
        }
        for (Future future : futures) {
            future.get(1, TimeUnit.SECONDS);
        }

        executorService.shutdown();
        final boolean success = executorService.awaitTermination(1, TimeUnit.SECONDS);
        if (!success) {
            throw new RuntimeException("Timeout waiting for executorService to terminate");
        }
        System.out.println("OK");
    }
}
