# Ownership

Rust makes you forced to explicitly manage ownership of all variables, in order
to ensure memory consistency and safety. However, thinking about ownership while
constructing a program can also prevent bugs that are not related to memory.
That is what this exercise illustrates.

The task is a (very) simple model of a number of workers that have some work to
do and will be allowed to do it based on the the roundrobin algorithm.

## Part 1 - Analyze the Java Program

Run the Java program. Do the workers do all they have to do? Or do we have some
bug in the program? (Hint: we do).

## Part 2 - Fix the same mistake in Rust

You have a skeleton of the same solution in Rust. Implement the main function
exactly like the Java program (including the bug) and see if it works. Now fix
the problem and verify that your solution works.

