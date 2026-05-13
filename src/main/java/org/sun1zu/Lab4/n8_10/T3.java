package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class T3 {
    public static void main(String[] args) throws IOException {
        Path sourceFolder = Paths.get("/home/sun1zu/Desktop");
        Path outputFile = Paths.get("file_paths.txt");

        writeAllFilePaths(sourceFolder, outputFile);
        System.out.println("Пути к файлам записаны в: " + outputFile);
    }

    public static void writeAllFilePaths(Path folder, Path outputFile) throws IOException {
        StringBuilder result = new StringBuilder();

        Files.walk(folder)
//                .filter(Files::isRegularFile)
                .forEach(path -> result.append(path.toAbsolutePath()).append("\n"));

        Files.writeString(outputFile, result.toString());
    }
}
