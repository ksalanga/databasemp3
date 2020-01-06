package com.company;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String args[]) throws Exception {
        //My solution 1
        try {
            Path path = Paths.get(args[0]);
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

        //Original Solution
    }
}