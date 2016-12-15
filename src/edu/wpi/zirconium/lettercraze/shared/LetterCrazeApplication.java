package edu.wpi.zirconium.lettercraze.shared;

import javafx.application.Application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public abstract class LetterCrazeApplication extends Application {
    public static Path dataFolder() {
        return Paths.get("lettercraze_data");
    }

    @Override
    public void init() {
        try {
            if (!Files.exists(dataFolder()) || !Files.isDirectory(dataFolder())) {
                System.out.println("Creating source folder!");
                Path source_data = Paths.get(getClass().getResource("/lettercraze_data").toURI());
                Stream<Path> pathStream = Files.walk(source_data);
                pathStream.forEach(src -> {
                    Path dest = dataFolder().resolve(source_data.relativize(src));
                    try {
                        System.out.println("Copying "+src+" to "+dest);
                        Files.copy(src, dest);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                pathStream.close();
            } else {
                System.out.println("**NOT** creating source folder!");
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
