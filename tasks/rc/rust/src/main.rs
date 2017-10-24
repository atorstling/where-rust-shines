extern crate crossbeam;

#[derive(Debug)]
struct MyList<T> {
    size: u32,
    ts: Box<[T]>
}

impl<T> MyList<T> {
    fn new() -> MyList<T> {
        MyList {
            size: 0,
            ts: Box::new([])
        }
    }
    fn resize(&self) {
        //T[] newElems = (T[]) new Object[buffer.length * 2];
        //System.arraycopy(buffer, 0, newElems, 0, size);
        //buffer = newElems;
        self.ts = Box::new([T; (*self.ts).len() * 2]);
    }

    fn add(&self, t: T) {
        let new_size = self.size + 1;
        if (new_size > (*self.ts).len()) {
            self.resize();
        }
        (*self.ts)[self.size] = t;
        self.size = new_size;
    }
}

fn main() {
    let thread_count = 10;
    let element_count = 100000;
    let l: MyList<u32> = MyList::new();
    crossbeam::scope(|scope| {
        for _ in 0..thread_count {
            scope.spawn(|| {
                            for i in 0..(element_count / thread_count) {
                                l.add(i);
                            }
                        });
        }
    });
    println!("Result : {:?}", l);
}
