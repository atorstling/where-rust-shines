# Null Pointer Exception (NPE)

This excercise showcases the philosophy around null pointers in Rust. We do this by means of a program
which reads an environment variable, cleans it up and displays it on stdout. The program has a bug: when the variable is missing, the Java program crashes.  

## Part 1 - Analyze the Java Program

Run the Java program, see that it throws an exception, and try to figure out why the exception arises.

## Part 2 - Try and fix the same mistake in Rust

Your task is now to naively code the same buggy solution into a Rust program. Can you end up with the same bug? The Rust program
already contains logic which does everything the Java program does, except reading the
environment variable. 

Change the definition of the username variable by commenting out the line which sets
it to "Kalle" and uncomment the line which reads
it from an environment variable.

Try to get it to compile. Is it easy to make the same mistake in
Rust as in Java? 

### Things to Know

1. To check the type of a variable, you can
   add a type declaration to the username
   variable, like so: `let username: String = ...`.
   This causes the compiler to complain if the
   types don't match.

2. Rust doesn't have nullable types, but it
   does have a [result type](https://doc.rust-lang.org/std/result/).

3. A result object can be destructured by
   [matching](https://rustbyexample.com/flow_control/match.html), or
   optimistically unboxed by calling [unwrap](https://doc.rust-lang.org/std/result/enum.Result.html#method.unwrap).
   Note that you can provide fallback values with methods like
   [unwrap_or](https://doc.rust-lang.org/std/result/enum.Result.html#method.unwrap_or).

4. If you see an error like
   ```rust
   ^^^^^^^ expected struct `std::string::String`, found reference
   |
   = note: expected type `std::string::String`
              found type `&'static str`
   ```
   , you are dealing with the two standard
   string types in Rust. String constants like
   `"Kalle"` are references to the constant, immutable type `str`. Sometimes the mutable
   and sized type `String` is required. you
   can convert to `String` by calling
   `to_owned()`, like so: `let a_string: String = "Kalle".to_owned();`