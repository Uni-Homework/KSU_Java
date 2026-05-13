package org.sun1zu.Lab4.n8_09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class T3 {
    public static void main(String[] args) {
        long count = countFilesInFolder(Paths.get("/home/sun1zu/Desktop"));
        System.out.println("Количество файлов: " + count);
    }

    public static long countFilesInFolder(Path folder) {
        try {
            return Files.walk(folder)
                    .filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
