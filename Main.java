package com.company;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;

import com.mpatric.mp3agic.ID3v1Tag;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

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
        //3. Parsing MP3 metadata with an external library
        List<Song> songList = new ArrayList<Song>();
        try (DirectoryStream<Path> stream =
                     Files.newDirectoryStream(path, "*.mp3")) { //uses glob syntax: pattern recognition behavior
            stream.forEach(s -> {
                try {
                    Mp3File mp3file = new Mp3File(s);
                    if (mp3file.hasId3v1Tag()) {
                        ID3v1Tag id3v1Tag = (ID3v1Tag) mp3file.getId3v1Tag(); //had to cast for some reason
                        String artist = id3v1Tag.getArtist();
                        String year = id3v1Tag.getYear();
                        String album = id3v1Tag.getAlbum();
                        String title = id3v1Tag.getTitle();
                        songList.add(new Song(artist, year, album, title));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                }
            });
        }


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