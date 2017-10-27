extern crate crossbeam;
use std::sync::Mutex;
use std::collections::HashSet;

#[derive(Debug)]
struct User {
    first_name: String,
    last_name: String,
}

fn main() {
    let thread_count = 10;
    let element_count = 100000;
    let users: Mutex<Vec<User>> = Mutex::new(vec![]);
    crossbeam::scope(|scope| {
        scope.spawn(|| loop {
                        let lock = users.lock();
                        let last_names: HashSet<String> = lock.unwrap()
                .iter()
                .map(|ref u| u.last_name.clone())
                .collect();
                        if last_names.contains("99sson") {
                            println!("Found Pelle 99sson");
                            break;
                        }
                        std::thread::sleep(std::time::Duration::from_millis(10));
                    });
        for _ in 0..thread_count {
            scope.spawn(|| {
                for i in 0..(element_count / thread_count) {
                    users.lock().unwrap().push(User {
                                                   first_name: "Pelle".to_owned(),
                                                   last_name: (i.to_string() + "sson").to_owned(),
                                               });
                    //let mut lock = l.lock().unwrap();
                    //(*lock).age+=i;
                    //(*lock).push_str(" ");
                    //(*lock).push_str(&i.to_string());
                }
            });
        }
    });
    println!("OK");
}
