// TODO: check all classes in folder
package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class T1 {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("input.txt");
        boolean result = checkEmptyLines(filePath);
        System.out.println("В начале одна пустая строка, в конце две: " + result);
    }

    public static boolean checkEmptyLines(Path filePath) throws IOException {
        List<String> lines = Files.readAllLines(filePath);

        if (lines.isEmpty()) return false;

        // Проверяем первую строку
        boolean firstLineEmpty = lines.get(0).isEmpty();

        // Проверяем последние две строки
        boolean lastLineEmpty = lines.get(lines.size() - 1).isEmpty();
        boolean secondLastLineEmpty = lines.size() >= 2 && lines.get(lines.size() - 2).isEmpty();

        return firstLineEmpty && lastLineEmpty && secondLastLineEmpty;
    }
}
