use std::string::String;

fn safe_print(s: String) {
  let cleaned = s.replace("\n", "");
  println!("{}", cleaned);
}

fn main() {
   safe_print(String::from("Hello, world!"));
}
