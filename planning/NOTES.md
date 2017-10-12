Thread-safety of primitive types in Java https://stackoverflow.com/a/9278798/83741
Atomic solution: use std::sync::atomic::{AtomicUsize, Ordering};
i.fetch_add(1, Ordering::Relaxed);
