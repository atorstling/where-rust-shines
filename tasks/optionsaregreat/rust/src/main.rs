use std::fs::File;
use std::io::Read;
use std::path::PathBuf;

fn main() {
    let path = get_configuration_file_path();
    match get_absolute_configuration_path(path)
        .and_then(|p| get_config_file_contents(p))
        .map(|c| get_configuration(c))
    {
        Some(config) => println!("Got config {:?}", config),
        None => println!("No config!"),
    }
}

#[derive(Debug)]
struct Configuration {
    content: String,
}

fn get_configuration_file_path() -> PathBuf {
    PathBuf::from("/some/file")
}

fn get_absolute_configuration_path(path: PathBuf) -> Option<PathBuf> {
    path.canonicalize().ok()
}

fn get_config_file_contents(path: PathBuf) -> Option<String> {
    let mut contents = String::new();
    File::open(&path)
        .and_then(|mut file| file.read_to_string(&mut contents))
        .ok().map(|_| contents)
}

fn get_configuration(content: String) -> Configuration {
    Configuration { content }
}
