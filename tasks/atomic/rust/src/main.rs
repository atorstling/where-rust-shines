use std::thread;

static THREAD_COUNT: u32 = 10;
static INCREMENTS_PER_THREAD: u32 = 100000;

fn main() {
    let i = 0;
    thread::spawn(|| {
                      for _ in 0..INCREMENTS_PER_THREAD {
                          //i+=1;
                      }
                  });
    println!("Result of {}*{} increments: {}",
             THREAD_COUNT,
             INCREMENTS_PER_THREAD,
             i);
}
