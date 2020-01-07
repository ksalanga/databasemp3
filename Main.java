package com.company;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String args[]) throws Exception {
        //1. Validating User Input
        String directory = args[0];
        Path path = Paths.get(directory);
        try {
            if (Files.exists(path)) {
                System.out.println("File found");
            } else {
                throw new IllegalArgumentException();
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        } catch (IllegalArgumentException e) {
            System.out.println("File Path not found, try a new path\n" + e);
        }

        //2. Reading in directories & files
        List<Path> pathList = new ArrayList<Path>();
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(path, "*.mp3")) {
            for (Path entry : stream) {
                pathList.add(entry);
            }
        }
        System.out.println(pathList);

    }


}