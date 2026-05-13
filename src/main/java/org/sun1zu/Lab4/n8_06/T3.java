package org.sun1zu.Lab4.n8_06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public class T3 {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("input.txt");

        List<String> lines = Files.readAllLines(filePath);
        Collections.shuffle(lines);
        Files.write(filePath, lines);

        System.out.println("Строки перемешаны!");
    }
}
