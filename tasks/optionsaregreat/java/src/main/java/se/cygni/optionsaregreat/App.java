package se.cygni.optionsaregreat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        // THIS CODE IS NOT ALLOWED TO CRASH
        DumbLibrary library = new DumbLibrary();
        if (library == null) {
            return;
        }

        Configuration config = Optional.ofNullable(library.getConfigurationFilePath())
            .flatMap(c -> Optional.ofNullable(library.getAbsoluteConfigurationPath(c)))
            .flatMap(p -> Optional.ofNullable(library.getConfigFileContents(p)))
            .map(p -> library.getConfiguration(p)).orElse(null);

        if (config == null) {
            System.out.println("We got a config:  " + config);
        } else {
            System.out.println("No config");
        }
    }

    private static final class Configuration {
        String content;
    }

    private static final class DumbLibrary { 
        public Path getConfigurationFilePath() {
            // TODO: To run this, point this at a file that is readable
            Path path = Paths.get("/some/file");
            return path;
        }

        public Path getAbsoluteConfigurationPath(Path path) {
            return path.toAbsolutePath();
        }

        public String getConfigFileContents(Path path) {
            try {
                byte[] encoded = Files.readAllBytes(path);
                return new String(encoded, Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        public Configuration getConfiguration(String configFileContents) {
            Configuration config = new Configuration();
            config.content = configFileContents;
            return config;
        }
    }
}
