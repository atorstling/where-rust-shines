extern crate crossbeam;
use std::sync::atomic::{AtomicUsize, Ordering};

fn main() {
    let thread_count = 10;
    let increments_per_thread = 100000;
   let mut i = AtomicUsize::new(0);
   crossbeam::scope(|scope| {
        for j in 0..thread_count {
            scope.spawn(|| {
                for k in 0..increments_per_thread {
                    //i+=1;
                    i.fetch_add(1, Ordering::Relaxed);
                }
            });
        }
    });
   println!("Result of {}*{} increments: {}", thread_count, increments_per_thread, i.get_mut());
}
