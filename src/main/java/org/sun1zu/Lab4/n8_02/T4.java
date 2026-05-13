package org.sun1zu.Lab4.n8_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class T4 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("input.txt"));

        String[] linesArray = lines.toArray(new String[0]);

        System.out.println("Массив строк:");
        for (int i = 0; i < linesArray.length; i++) {
            System.out.println(i + ": " + linesArray[i]);
        }
    }
}
