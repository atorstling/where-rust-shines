# Null Pointer Exception (NPE)

## Part 1 - Analyze the Java Program
This excercise contains a java program which reads a environment variable, cleans it up and prints it to stdout.

To run it from the command line, do
```bash
$ mvn
...
$ java -jar target/npe-1.0-SNAPSHOT.jar
```

Your first task is to run it, see that it throws an exception, and to figure out why the exception arises.

## Part 2 - Discussion
Is this type of mistake something you have made earlier? Do you think it could be a common source of error in
a Java program?

## Part 3 - Replicate same mistake in Rust

Your task is now to code the same solution into a Rust program. Can you end up with the same bug? The Rust program
already contains logic which does everything the Java program does, except reading the
environment variable. Add this part of the program.

The rust program can be run with
```bash
$ cargo run
```

## Part 4 - Discussion

Did Rust sucessfully prevent you from making the same mistake? How?
Do you these preventions are a good idea to build into the language?
