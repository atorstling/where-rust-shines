extern crate crossbeam;
use std::sync::Mutex;

#[derive(Debug)]
struct Bleh {
    age: u32
}

fn main() {
    let thread_count = 10;
    let element_count = 100000;
    let l: Mutex<Bleh> = Mutex::new(Bleh { age: 14});
    crossbeam::scope(|scope| {
        for _ in 0..thread_count {
            scope.spawn(|| {
                            for i in 0..(element_count / thread_count) {
                                let mut lock = l.lock().unwrap();                            
                                (*lock).age+=i;
                                //(*lock).push_str(" ");
                                //(*lock).push_str(&i.to_string());                            
                            }
                        });
        }
    });
    println!("Result : {:?}", l);
}
