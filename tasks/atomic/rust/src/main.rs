extern crate crossbeam;
use std::sync::atomic::{AtomicUsize, Ordering};

fn main() {
    let thread_count = 10;
    let increments_per_thread = 100000;
   let i = AtomicUsize::new(0);
   crossbeam::scope(|scope| {
        for _ in 0..thread_count {
            scope.spawn(|| {
                for _ in 0..increments_per_thread {
                    //i+-=1;
                    i.fetch_add(1, Ordering::Release);
                }
            });
        }
    });
   println!("Result of {}*{} increments: {}", thread_count, increments_per_thread, i.into_inner());
   //println!("Result of {}*{} increments: {}", thread_count, increments_per_thread, i.load(Ordering::Acquire));
}
