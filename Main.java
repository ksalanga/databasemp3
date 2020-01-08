package com.company;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
//import com.mpatric.mp3agic.Mp3File;

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
                     Files.newDirectoryStream(path, "*.mp3")) { //uses glob syntax: pattern recognition behavior
            for (Path entry : stream) {
                pathList.add(entry);
            }
        }
        System.out.println(pathList);

//        Mp3File mp3file = new Mp3File();
    }

    // Step 3: The Domain Class - Part 2
    public static class Song {

        private final String artist;
        private final String year;
        private final String album;
        private final String title;

        public Song(String artist, String year, String album, String title) {
            this.artist = artist;
            this.year = year;
            this.album = album;
            this.title = title;
        }

        public String getArtist() {
            return artist;
        }

        public String getYear() {
            return year;
        }

        public String getAlbum() {
            return album;
        }

        public String getTitle() {
            return title;
        }
    }


}