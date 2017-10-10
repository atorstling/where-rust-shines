extern crate crossbeam;

fn main() {
   let mut i = 0;
   crossbeam::scope(|scope| {
        for j in 0..10 {
            scope.spawn(|| {
                for k in 0..1000 {
                    i+=1;
                }
            });
        }
    });
   }
   
   println!("Result of 10*1000 increments: {}", i);
}
