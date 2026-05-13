package org.sun1zu.Lab4.n8_09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class T5 {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("input.txt"));
        String result = shuffleWordsInSentences(content);
        Files.writeString(Path.of("shuffled.txt"), result);
        System.out.println("Результат записан в shuffled.txt");
    }

    public static String shuffleWordsInSentences(String text) {
        // Разбиваем на предложения, сохраняя разделители
        String[] parts = text.split("(?<=[.!?])\\s*");
        StringBuilder result = new StringBuilder();

        for (String part : parts) {
            if (part.trim().isEmpty()) {
                result.append(part);
                continue;
            }
            result.append(shuffleWordsInSentence(part));
        }
        return result.toString();
    }

    private static String shuffleWordsInSentence(String sentence) {
        // Разбиваем на слова, сохраняя пробелы и знаки препинания
        String[] words = sentence.split("\\s+");

        // Перемешиваем слова
        List<String> wordList = Arrays.asList(words);
        Collections.shuffle(wordList);

        // Собираем обратно
        return String.join(" ", wordList);
    }
}
