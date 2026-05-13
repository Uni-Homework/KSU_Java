package org.sun1zu.Lab4.n8_06;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class T1 {
    public static void main(String[] args) throws IOException {
        String content = Files.readString(Path.of("input.txt"));

        Map<Character, Integer> charCount = new HashMap<>();
        int totalChars = 0;

        for (char c : content.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            totalChars++;
        }

        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : charCount.entrySet()) {
            double percentage = (entry.getValue() * 100.0) / totalChars;
            result.append(String.format("'%c': %d раз (%.2f%%)\n",
                    entry.getKey(), entry.getValue(), percentage));
        }

        Files.writeString(Path.of("char_percentage.txt"), result.toString());
        System.out.println("Результат записан в char_percentage.txt");
    }
}
