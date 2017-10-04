# Null Pointer Exception (NPE)

- Just make it one excercise with discussion at the end
- Just check the problem with the Java code, uncomment it in Rust
- Then fix the rust code using a suitable choice of methods from Rust
- Discuss how Rust vs. Java handles values that don't exist

## Part 1 - Analyze the Java Program
This excercise contains a java program which reads an environment variable, cleans it up and prints it to stdout.

To run it from the command line, do
```bash
$ mvn
...
$ java -jar target/npe-1.0-SNAPSHOT.jar
```

Your first task is to run it, see that it throws an exception, and to figure out why the exception arises.

## Part 2 - Mob Discussion
Is this type of mistake something you have made earlier? Do you think it could be a common source of error in
a Java program?

## Part 3 - Replicate same mistake in Rust

Your task is now to code the same solution into a Rust program. Can you end up with the same bug? The Rust program
already contains logic which does everything the Java program does, except reading the
environment variable.

The rust program can be ran with
```bash
$ cargo run
Compiling npe v0.1.0 (file:///Users/alexandert/projects/private/where-rust-shines/tasks/npe/rust)
    Finished dev [unoptimized + debuginfo] target(s) in 0.36 secs
     Running `target/debug/npe`
Kalle
$
```

Your task is to replace the static name "Kalle" with reading from an
environment variable. Comment out the line which defines
the `username` variable to "Kalle" and uncomment the line which reads
it from an environment variable.

Try to get it to compile. Is it easy to make the same mistake in
Rust as in Java? See below for hints.

## Hints

1. Try to determine the type of the username variable. Is it a string? Is
   it nullable?

2. A result object can be destructured by
   [matching](https://rustbyexample.com/flow_control/match.html), or
   optimitically fetched by calling [unwrap](https://doc.rust-lang.org/std/result/enum.Result.html#method.unwrap).
   Note that you can provide fallback values with methods like
   [unwrap_or](https://doc.rust-lang.org/std/result/enum.Result.html#method.unwrap_or)

## Part 4 - Mob Discussion

Did Rust sucessfully prevent you from making the same mistake? How?
Do you think these preventions are a good idea to build into the language?
