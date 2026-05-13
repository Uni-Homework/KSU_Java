package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class T4 {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("input.txt"));
        String result = shuffleWordsInSentences(content);
        Files.writeString(Path.of("shuffled.txt"), result);
        System.out.println("Результат записан в shuffled.txt");
    }

    public static String shuffleWordsInSentences(String text) {
        String[] sentences = text.split("(?<=[.!?])\\s*");
        StringBuilder result = new StringBuilder();

        for (String sentence : sentences) {
            if (sentence.trim().isEmpty()) {
                result.append(sentence);
                continue;
            }
            result.append(shuffleSentence(sentence));
        }
        return result.toString();
    }

    private static String shuffleSentence(String sentence) {
        String[] words = sentence.split("\\s+");
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        // Сохраняем первую букву заглавной, если была
        boolean startsCapital = !words[0].isEmpty() && Character.isUpperCase(words[0].charAt(0));

        Collections.shuffle(wordList);

        String result = String.join(" ", wordList);

        if (startsCapital && !result.isEmpty()) {
            result = Character.toUpperCase(result.charAt(0)) + result.substring(1).toLowerCase();
        }

        return result;
    }
}
