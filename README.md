# Where Rust shines
A workshop to learn `Java{,script}` developers to write more robust code by showing how Rust forces you to do so.

## Required environment
You need:
- `Rust`, install the latest stable version via [rustup](https://www.rustup.rs/),
  see installation and set-up instructions below;
- **A text editor with support for Rust**, the recommendation is to use Visual
  Studo Code, see installation and set-up instructions below.
- **Java**;
- **Maven**.


### Installing Rust
Run `curl https://sh.rustup.rs -sSf | sh` to install `rustup`.

For these exercises we'll use a nightly version of `rust` (it is quite common when
developing Rust to use nightly for development and build for production with
stable).


```shell
rustup install stable
rustup default stable
rustc --version
# Should print something similar to this:
# rustc 1.21.0 (3b72af97e 2017-10-09)

rustup install nightly-2017-10-15
# We'll add an override for this specific repository, so ensure you stand the root of it when running the command
rustup override set nightly
rustc --version
# Should print something similar this:
# rustc 1.22.0-nightly (7778906be 2017-10-14)
```

### Installing Visual Studio Code for Rust

```shell
# If you are on macOS and have brew and brew cask:
brew cask install visual-studio-code
```

Otherwise, see [the installation instructions](https://code.visualstudio.com/docs/setup/setup-overview).

Then:
- Open Visual Studio Code (which will be referred to as vscode henceforth);
- Press `cmd+shift+p` to open the Command Palette;
- Write `ext install` and select the alternative `Extensions: Install extensions`;
- Type Rust and install the alternative called `Rust (rls)`;
- Since you will be looking at Java-code as well, write Java and install
  `Language Support for Java(TM) by Red Hat`;
- Open one of the Rust files (called `main.rs`) and you should see `RLS: ...` in
  the status bar at the bottom. Wait for RLS to set up properly and see that
  syntax highlighting and auto-completion work.

### Verifying your environment
To verify your environment clone this repo (if you have not already done so) and
navigate to it in your terminal.

```shell
# Go to the task
$ cd tasks/happy/rust

# Go to the Java part of the task and build it
$ mvn clean install && java -jar target/*.jar
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 3.984 s
[INFO] Finished at: 2017-10-16T16:57:50+02:00
[INFO] Final Memory: 22M/304M
[INFO] ------------------------------------------------------------------------
Hello World!

# Go the rust part of the task and build it
$ cd ../rust
$ cargo run
   Compiling happy v0.1.0 (file:///Users/mbark/repos/where-rust-shines/tasks/happy/rust)
    Finished dev [unoptimized + debuginfo] target(s) in 0.34 secs
     Running `target/debug/happy`
Hello, world!
Hello &'static str
Hello String::from
res_function gave rise to error: SomeError
This is how a struct looks: Struct { field: "", int32: -100, uint64: 1 }
```

If all of this works fine:
- Open the project in your editor;
- Open the file `tasks/happy/rust/src/main.rs`;
- Uncomment the lines marked to verify that you got `RLS` working with compilation
  errors and auto-completion.

## Tasks
The repository is separated into a number of tasks that all illustrate common and easy to make mistakes in Java. There is also a matching Rust-project that illustrates how Rust, through its focus on building robust software prevents these mistakes.

## How to run a Rust task

Once you are standing in a rust directory with a `Cargo.toml` file, you can run the program with
`cargo run`:

```bash
$ cargo run
Compiling npe v0.1.0 (file:///Users/alexandert/projects/private/where-rust-shines/tasks/npe/rust)
    Finished dev [unoptimized + debuginfo] target(s) in 0.36 secs
     Running `target/debug/npe`
Kalle
$
```
## How to run a Java task

```bash
alext@smith:~/projects/where-rust-shines/tasks/npe/java$ mvn clean install && java -jar target/*.jar
[INFO] Scanning for projects...
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] Building npe 1.0-SNAPSHOT
[INFO] ------------------------------------------------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ npe ---
[INFO] Deleting /home/alext/projects/where-rust-shines/tasks/npe/java/target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ npe ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/alext/projects/where-rust-shines/tasks/npe/java/src/main/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.6.0:compile (default-compile) @ npe ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 1 source file to /home/alext/projects/where-rust-shines/tasks/npe/java/target/classes
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ npe ---
[WARNING] Using platform encoding (UTF-8 actually) to copy filtered resources, i.e. build is platform dependent!
[INFO] skip non existing resourceDirectory /home/alext/projects/where-rust-shines/tasks/npe/java/src/test/resources
[INFO]
[INFO] --- maven-compiler-plugin:3.6.0:testCompile (default-testCompile) @ npe ---
[INFO] Changes detected - recompiling the module!
[WARNING] File encoding has not been set, using platform encoding UTF-8, i.e. build is platform dependent!
[INFO] Compiling 1 source file to /home/alext/projects/where-rust-shines/tasks/npe/java/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ npe ---
[INFO] Surefire report directory: /home/alext/projects/where-rust-shines/tasks/npe/java/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running se.cygni.npe.AppTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.078 sec

Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] --- maven-jar-plugin:3.0.2:jar (default-jar) @ npe ---
[INFO] Building jar: /home/alext/projects/where-rust-shines/tasks/npe/java/target/npe-1.0-SNAPSHOT.jar
[INFO]
[INFO] --- maven-install-plugin:2.4:install (default-install) @ npe ---
[INFO] Installing /home/alext/projects/where-rust-shines/tasks/npe/java/target/npe-1.0-SNAPSHOT.jar to /home/alext/.m2/repository/se/cygni/npe/npe/1.0-SNAPSHOT/npe-1.0-SNAPSHOT.jar
[INFO] Installing /home/alext/projects/where-rust-shines/tasks/npe/java/pom.xml to /home/alext/.m2/repository/se/cygni/npe/npe/1.0-SNAPSHOT/npe-1.0-SNAPSHOT.pom
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time: 4.854 s
[INFO] Finished at: 2017-10-10T16:27:09+02:00
[INFO] Final Memory: 16M/128M
[INFO] ------------------------------------------------------------------------
Exception in thread "main" java.lang.NullPointerException
  at se.cygni.npe.App.cleanup(App.java:6)
  at se.cygni.npe.App.main(App.java:12)
alext@smith:~/projects/where-rust-shines/tasks/npe/java$
```
