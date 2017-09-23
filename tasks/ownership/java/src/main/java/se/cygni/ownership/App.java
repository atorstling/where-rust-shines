package se.cygni.ownership;

import java.util.ArrayList;
import java.util.List;

public class App {
  public static void main(String[] args) {
    Worker worker = new Worker();
    List<Worker> workers = new ArrayList<Worker>();
    for (int i = 0; i < 5; i++) {
      workers.add(worker);
    }

    Core core = new Core(workers);
    core.roundrobin();
    core.roundrobin();
  }

  public static final class Worker {
    int workerTime = 10;

    void doWork() {
      if (workerTime < 0) {
        System.out.println("Uh oh, worker is out of time!");
        return;
      }

      System.out.println("Doing some work as we have time left!");
      workerTime -= 5;
    }
  }

  public static final class Core {
    List<Worker> workers;

    Core(List<Worker> workers) {
      this.workers = workers;
    }

    void roundrobin() {
      for (int i = 0; i < workers.size(); i++) {
        this.workers.get(i).doWork();
      }
    }
  }
}
