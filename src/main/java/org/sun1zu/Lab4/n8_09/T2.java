package org.sun1zu.Lab4.n8_09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class T2 {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("input.txt"));
        boolean result = checkFirstLetterCapital(content);
        System.out.println("Каждое предложение начинается с заглавной: " + result);
    }

    public static boolean checkFirstLetterCapital(String text) {
        // Разбиваем текст на предложения (по . ! ?)
        String[] sentences = text.split("[.!?]\\s*");

        for (String sentence : sentences) {
            if (sentence.trim().isEmpty()) continue;

            char firstChar = sentence.trim().charAt(0);
            if (!Character.isUpperCase(firstChar)) {
                return false;
            }
        }
        return true;
    }
}
