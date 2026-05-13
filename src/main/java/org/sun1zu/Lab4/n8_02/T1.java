package org.sun1zu.Lab4.n8_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class T1 {
    public static void main(String[] args) throws IOException {
        var p = Paths.get("input.txt");
        String content = Files.readString(p);

        long letterCount = content.chars()
                .filter(Character::isLetter)
                .count();

        System.out.println("Количество букв: " + letterCount);
    }
}
