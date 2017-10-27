extern crate crossbeam;
use std::sync::Mutex;

#[derive(Debug)]
struct User {
    first_name: String,
    last_name: String
}

#[derive(Debug)]
struct UserRepo {
    users: Vec<User>
}

impl UserRepo {
    fn new() -> UserRepo {
        UserRepo {
            users: vec![]
        }
    }

    fn add_user(&mut self, user: User) {
        self.users.push(user);
    }
}

fn main() {
    let thread_count = 10;
    let element_count = 100000;
    let mut user_repo = UserRepo::new();
    crossbeam::scope(|scope| {
        for _ in 0..thread_count {
            scope.spawn(|| {
                            for i in 0..(element_count / thread_count) {
                                user_repo.add_user(User {
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
    println!("Result : {:?}", user_repo);
}
