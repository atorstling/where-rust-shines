# Mutex

This exercise is a follow-up to the atomics one. This time we look into how
you can protect larger data structures.

## Part 1 - Analyze the Java Program

Run the Java program a few times. Sometimes it fails with a stacktrace. Why?

## Part 2 - Fix the same mistake in Rust

You have a skeleton of the same solution in Rust. Uncomment the incrementing logic and see what
happens. Can you fix the problem?

### Things to Know

1. Just like in the atomics case, you must protect access to the data you are accessing.
   The most common type to do this with is [Mutex](https://doc.rust-lang.org/std/sync/struct.Mutex.html) and
    [RWLock](https://doc.rust-lang.org/std/sync/struct.RwLock.html).

2. When you want to lock for an extended period of time, you obtain a guard
   by calling something like `let guard = mutex.lock()` or`let guard = rwlock.read()`.
   These guard variables will keep the mutex or rwlocked locked until the guard
   goes out of scope. Also see [MutexGuard](https://doc.rust-lang.org/std/sync/struct.MutexGuard.html)
   , [RwLockReadGuard](https://doc.rust-lang.org/std/sync/struct.RwLockReadGuard.html) and
   [RwLockWriteGuard](https://doc.rust-lang.org/std/sync/struct.RwLockWriteGuard.html).

   Dereferencing these guard objects gives
   you the wrapped object:

   ```
   use std::sync::Mutex;

   fn main() {
       let m = Mutex::new(0u32);
       {
         let mut guard = m.lock().unwrap();
         *guard += 1;
       }
       println!("{:?}", m);
   }
   ```
