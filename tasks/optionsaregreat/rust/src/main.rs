use std::fs::File;
use std::io::Read;
use std::path::PathBuf;

fn main() {
    // Implement the code from Java and do it cleanly
}

#[derive(Debug)]
struct Configuration {
    content: String,
}

fn get_configuration_file_path() -> PathBuf {
    // TODO: To run this, point this at a file that is readable
    PathBuf::from("/some/file")
}

fn get_absolute_configuration_path(path: PathBuf) -> Option<PathBuf> {
    path.canonicalize().ok()
}

fn get_config_file_contents(path: PathBuf) -> Option<String> {
    let mut contents = String::new();
    File::open(&path)
        .and_then(|mut file| file.read_to_string(&mut contents))
        .ok()
        .map(|_| contents)
}

fn get_configuration(content: String) -> Configuration {
    Configuration { content }
}
