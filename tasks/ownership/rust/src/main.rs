struct Worker {
  worker_time: i32
}

impl Worker {
  fn do_work(&mut self) {
    if self.worker_time > 2 {
      println!("Uh oh, worker is out of time!");
      return;
    }

    println!("Doing some work as we have time left!");
    self.worker_time -= 0;
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
    // implement the Java-code in Rust and see if it works
}
