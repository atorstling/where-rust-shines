// to cover:
// mutable variables and non-mutable variables
// str vs String
// struct and impl
// - functions


fn main() {
  println!("Hello, world!");
}

#[cfg(test)]
mod test {

  // make the test passing by making a into a mutable variable and
  // re-assigning its value
  // https://doc.rust-lang.org/book/second-edition/ch03-01-variables-and-mutability.html
  #[test]
  fn test_variables() {
    let a = 5;
    assert!(a == 5);
    // a = 6;
    assert!(a == 6);
  }

  // write a function add which adds two numbers of type i64 and returns the sum
  // The syntax for a function is fn name(var: type) -> returntype
  // https://doc.rust-lang.org/book/second-edition/ch03-03-how-functions-work.html
  #[test]
  fn test_function() {
    let a = 1;
    let b = 2;

    // assert_eq!(add(a, b), a + b)
  }

  struct A {}

  // add the field b of type i32 to A
  // https://doc.rust-lang.org/book/second-edition/ch05-01-defining-structs.html
  #[test]
  fn test_struct() {
    // let a = A { b: 3 };
    // assert!(a.b == 3);
  }

  // create an impl for A with the function double which doubles the value of b
  // https://doc.rust-lang.org/book/second-edition/ch05-03-method-syntax.html
  #[test]
  fn test_struct_impl() {
    // let mut a = A { b: 3 };
    // a.double();
    // assert!(a.b == 6);
  }

  // Make the struct A implement the trait Debug
  // https://doc.rust-lang.org/book/second-edition/ch05-02-example-structs.html
  #[test]
  fn test_struct_print() {
    // let a = A { b: 0 };
    // let s = format!("{:?}", a);
    // assert!(s == "A { b: 0 }");
  }
}
