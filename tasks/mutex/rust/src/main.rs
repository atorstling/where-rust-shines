extern crate crossbeam;
use std::collections::HashSet;

#[derive(Debug)]
struct User {
    first_name: String,
    last_name: String,
}

fn main() {
    let thread_count = 10;
    let element_count = 100000;
    let users: Vec<User> = vec![];
    crossbeam::scope(|scope| {
        scope.spawn(|| loop {
                        let last_names: HashSet<String> =
                            users.iter().map(|ref u| u.last_name.clone()).collect();
                        if last_names.contains("99sson") {
                            println!("Found Pelle 99sson");
                            break;
                        }
                        std::thread::sleep(std::time::Duration::from_millis(10));
                    });
        for _ in 0..thread_count {
            scope.spawn(|| for i in 0..(element_count / thread_count) {
                            //users.push(User {
                            //               first_name: "Pelle".to_owned(),
                            //               last_name: (i.to_string() + "sson").to_owned(),
                            //           });
                        });
        }
    });
    println!("OK");
}
