package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.*;

public class T6 {
    public static void main(String[] args) throws IOException {
        Path source = Paths.get("/home/user/source_folder");
        Path target = Paths.get("/home/user/copy_folder");

        copyFolder(source, target);
        System.out.println("Папка скопирована из " + source + " в " + target);
    }

    public static void copyFolder(Path source, Path target) throws IOException {
        Files.walk(source).forEach(sourcePath -> {
            try {
                Path targetPath = target.resolve(source.relativize(sourcePath));
                if (Files.isDirectory(sourcePath)) {
                    if (!Files.exists(targetPath)) {
                        Files.createDirectories(targetPath);
                    }
                } else {
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                }
            } catch (IOException e) {
                System.err.println("Ошибка копирования: " + sourcePath);
            }
        });
    }
}
