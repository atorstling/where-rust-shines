extern crate crossbeam;

fn main() {
   let mut i = 0;
   for j in 1..10001 {
       i+=1;
   }
   
   println!("Result of 10*1000 increments: {}", i);
}
