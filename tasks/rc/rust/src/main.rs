extern crate crossbeam;
use std::sync::Mutex;
use std::collections::HashSet;

#[derive(Debug)]
struct User {
    first_name: String,
    last_name: String
}

#[derive(Debug)]
struct UserRepo {
    users: Vec<User>
}

fn main() {
    let thread_count = 10;
    let element_count = 100000;
    let mut users: Vec<User> = vec![];
    crossbeam::scope(|scope| {
        for _ in 0..thread_count {
            scope.spawn(|| {
                loop {
                    let last_names: HashSet<String> = users.iter().map(|u| u.last_name)
                    .collect();
                    if last_names.contains("99sson") {
                        println!("Found Pelle 99sson");
                        break;
                    }
                    std::thread::sleep(std::time::Duration::from_millis(10));
                }
            });
            scope.spawn(|| {
                            for i in 0..(element_count / thread_count) {
                                users.push(User {
                                    first_name: "Pelle".to_owned(),
                                    last_name: (i.to_string() + "sson").to_owned()
                                });
                                //let mut lock = l.lock().unwrap();                            
                                //(*lock).age+=i;
                                //(*lock).push_str(" ");
                                //(*lock).push_str(&i.to_string());                            
                            }
                        });
        }
    });
    println!("Result : {:?}", users);
}
