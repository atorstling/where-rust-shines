# Atomic

Java provides stronger guarantees about thread safety than many other languages. But it's still easy
to forget to protect your data from concurrent access. This excercise showcases how Rust
helps with that. The program is supposed to calculate the multiplication 10*100000 by spinning up
10 concurrent tasks in different threads, and let each thread increment a shared variable 100000 times.  

## Part 1 - Analyze the Java Program

Run the Java program. Does it correctly calculate 10*100000? If not, why is this? 

## Part 2 - Fix the same mistake in Rust

You have a skeleton of the same solution in Rust. Uncomment the incrementing logic and see what
happens. Can you fix the problem?

### Things to Know

1. Rust doesn't allow more than one mutable borrow at a time, which means
   that you cannot access a variable from multiple scopes at the same time.

2. In order to fix this, you need to synchronize access somehow. For ints, the
   most logical choice is an [atomic type](https://doc.rust-lang.org/std/sync/atomic/)
   like [AtomicUsize](https://doc.rust-lang.org/std/sync/atomic/struct.AtomicUsize.html).
   Atomic types implement `Sync` and are therefore safe to share between threads.
   This type has a method called [fetch_add](https://doc.rust-lang.org/std/sync/atomic/struct.AtomicUsize.html#method.fetch_add),
   which can be used to implement incrementing the counter.
   You can read back with [load](https://doc.rust-lang.org/std/sync/atomic/struct.AtomicUsize.html#method.load) or
   unwrap the value altogether with use [into_inner](https://doc.rust-lang.org/std/sync/atomic/struct.AtomicUsize.html#method.into_inner).  

   #### Ordering
   One solution is to write with the `Release` memory ordering and to read with the
   `Acquire` ordering. This makes sure that the read sees all writes. This
   works in the general case of concurrent reading and writing.

   Alternatively, we can exploit the case that we only read when we're done writing.
   This means that you may write with `Relaxed` mode and read back with the consuming
   [into_inner](https://doc.rust-lang.org/std/sync/atomic/struct.AtomicUsize.html#method.into_inner).
   You may only use `into_inner` if you are the only owner of the data, and there are
   no outstanding borrows.