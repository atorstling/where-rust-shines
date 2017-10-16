fn main() {
  println!("Hello, world!");
  println!("{}", function());
  println!("{}", another_function());

  match res_function() {
    Ok(o) => println!("res_function returned Ok: {:?}", o),
    Err(e) => println!("res_function gave rise to error: {:?}", e)
  }

  let var = Struct {
    field: String::from(""),
    int32: -100,
    uint64: 1
  };

  println!("This is how a struct looks: {:?}", var);

  // 1. Uncomment the line below and verify that you get an error in your text editor
  // var.uint64 += 1;

  // 2. Uncomment the line below and verify that you get auto-completion
  // String::
}

fn function() -> &'static str {
  "Hello &'static str"
}

fn another_function() -> String {
  String::from("Hello String::from")
}

fn res_function() -> Result<(), FooError> {
  let a = good_function()?;
  let b = bad_function()?;
  println!("Responses: {:?} {:?}", a, b);
  Ok(())
}

fn good_function() -> Result<(), FooError> {
  Ok(())
}

fn bad_function() -> Result<(), FooError> {
  return Err(FooError::SomeError)
}

#[derive(Debug)]
struct Struct {
  field: String,
  int32: i32,
  uint64: u64
}

#[derive(Debug)]
enum FooError {
  SomeError
}
