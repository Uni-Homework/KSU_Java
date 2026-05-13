package org.sun1zu.Lab4.n8_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class T2 {
    public static void main(String[] args) throws IOException {
        String fileName = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ".txt";

        Path filePath = Path.of(fileName);
        Files.createFile(filePath);

        System.out.println("Файл создан: " + filePath.toAbsolutePath());
    }
}
