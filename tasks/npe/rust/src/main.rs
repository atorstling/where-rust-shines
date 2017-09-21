use std::string::String;

fn safe_print(s: String) {
  let cleaned = s.replace("\n", "");
  println!("{}", cleaned);
}

fn main() {
   let username = String::from("Kalle");
   //let username = std::env::var("CONF_USERNAME");
   safe_print(username);
}
