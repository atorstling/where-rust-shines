package se.cygni.rc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.*;

public class App3 {

    static class User {
        public String firstName;
        public String lastName;

        public User(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    static class Users {
        final ConcurrentHashMap<String, User> byLastName = new ConcurrentHashMap<>();

        public void add(User user) {
            byLastName.put(user.lastName, user);
        }

        public void remove(User user) {
            byLastName.remove(user.lastName);
        }

        public String[] getLastNames() {
            return byLastName.keySet().toArray(new String[byLastName.size()]);
        }
    }

    public static void main(String[] args) throws Exception {
        final int writers = 1000;
        final ExecutorService executorService = Executors.newFixedThreadPool(writers);
        final ArrayList<Future> futures = new ArrayList<>();

        final Users users = new Users();

        final CountDownLatch found514Sson = new CountDownLatch(1);
        //Schedule the Pelle 514sson finder
        futures.add(executorService.submit(() -> {
            while (true) {
                final String[] lastNames = users.getLastNames();
                for (String lastName : lastNames) {
                    if (lastName.toLowerCase().equals("514sson")) {
                        found514Sson.countDown();
                        break;
                    }
                }
                Thread.sleep(10);
            }
        }));

        //Schedule the user adders
        for (int i = 0; i < writers; i++) {
            int finalI = i;
            final User user = new User("Pelle", finalI + "sson");
            futures.add(executorService.submit(() -> users.add(user)));
            final User user2 = new User("Jocke", finalI + "dottir");
            futures.add(executorService.submit(() -> users.add(user2)));
            futures.add(executorService.submit(() -> users.remove(user2)));

        }

        // Wait for 514sson to be added
        final boolean found = found514Sson.await(10, TimeUnit.SECONDS);
        if (!found) {
            throw new RuntimeException("Pelle 514sson never got found");
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
