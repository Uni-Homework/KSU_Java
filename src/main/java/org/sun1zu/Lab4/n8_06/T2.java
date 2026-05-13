package org.sun1zu.Lab4.n8_06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class T2 {
    public static void main(String[] args) throws IOException {
        String firstLine = Files.readAllLines(Path.of("input.txt"))
                .stream()
                .findFirst()
                .orElse("Файл пуст");

        System.out.println("Первая строка: " + firstLine);
    }
}
