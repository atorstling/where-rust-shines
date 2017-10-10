use std::string::String;

fn cleanup(s: String) -> String {
    s.replace("\n", "")
}

fn main() {
    let username = String::from("Kalle");
    let username = std::env::var("NPE_USERNAME");
    println!("{}", cleanup(username));
}
