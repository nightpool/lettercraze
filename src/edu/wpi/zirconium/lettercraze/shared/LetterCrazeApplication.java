package edu.wpi.zirconium.lettercraze.shared;

import edu.wpi.zirconium.lettercraze.utils.WordTable;
import javafx.application.Application;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public abstract class LetterCrazeApplication extends Application {
    public static Path dataFolder() {
        return Paths.get("lettercraze_data");
    }

    @Override
    public void init() throws InterruptedException {
        try {
            if (!Files.exists(dataFolder()) || !Files.isDirectory(dataFolder())) {
                System.out.println("Creating source folder!");
                URI resourceFolder;
                resourceFolder = getClass().getResource("/lettercraze_data").toURI();
                if (!resourceFolder.getScheme().equalsIgnoreCase("file")) {
                    Map<String, String> env = new HashMap<>();
                    env.put("create", "true");
                    FileSystem zipfs = FileSystems.newFileSystem(resourceFolder, env);
                }
                Path source_data = Paths.get(resourceFolder);
                Stream<Path> pathStream = Files.walk(source_data);
                pathStream.forEach(src -> {
                    Path dest = dataFolder().resolve(source_data.relativize(src).toString());
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
            WordTable.loadWordTable();
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        Thread.sleep(800);
    }
}