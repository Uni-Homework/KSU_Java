package org.sun1zu.Lab4.n8_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class T3 {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Paths.get("numbers.txt"));

        int max = Arrays.stream(content.split(","))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .max()
                .orElseThrow(() -> new RuntimeException("Файл пуст или нет чисел"));

        System.out.println("Максимальное число: " + max);
    }
}
