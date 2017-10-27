# optionsaregreat
As most of you know, Options are great and since Rust has no concept of `null`
and only use `Option` or `Result` we can skip all those calls to `Optional.ofNullable`
and simply do what we want anyway.

## Implement the streaming solution in Rust
You have the same api functions as in the Java program. Now implement the same
functionality.

To implement it cleanly using streams you need:
- [lambda](https://doc.rust-lang.org/book/second-edition/ch13-01-closures.html#closures-store-code-to-be-executed-later)
- [`and_then`](https://doc.rust-lang.org/std/option/enum.Option.html#method.and_then);
- [`map`](https://doc.rust-lang.org/std/option/enum.Option.html#method.map);
- [`match`ing](https://doc.rust-lang.org/std/option/) enums to destructure them;
