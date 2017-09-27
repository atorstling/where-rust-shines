struct Worker {
  worker_time: i32
}

impl Worker {
  fn do_work(&mut self) {
    if self.worker_time > 10 {
      println!("Uh oh, worker is out of time!");
      return;
    }

    println!("Doing some work as we have time left!");
    self.worker_time -= 5;
  }
}

struct Core {
  workers: Vec<Worker>,
}

impl Core {
  fn round_robin(&mut self) {
    for worker in &mut self.workers {
      worker.do_work();
    }
  }
}

fn main() {
  let mut worker = Worker { worker_time: 10};
  let mut workers: Vec<Worker> = vec![];

  // for i in 1..5 {
  //   workers.push(worker);
  // }

  let mut core = Core { workers: workers };
  core.round_robin();
  core.round_robin();
}
