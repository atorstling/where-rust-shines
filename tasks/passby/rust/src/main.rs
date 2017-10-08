struct Dog {
    name: &'static str,
}

impl Dog {
    fn get_name(&self) -> &'static str {
        return self.name;
    }

    fn set_name(&mut self, name: &'static str) {
        self.name = name;
    }
}

fn main() {
    let dog = &mut Dog { name: "Max" };

    println!("The dog's name is {}", dog.name);

    foo(dog);
    println!("After calling foo the dog's name is: {}", dog.get_name());

    bar(dog);
    println!("After calling bar the dog's name is: {}", dog.get_name());
}

fn foo(dog: &mut Dog) {
    // dog = &mut Dog { name: "Fifi" };
}

fn bar(dog: &mut Dog) {
    dog.set_name("Fifi");
}
