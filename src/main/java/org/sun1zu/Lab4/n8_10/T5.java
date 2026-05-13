package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

public class T5 {
    public static void main(String[] args) throws IOException {
        Path folder = Path.of("/home/user/folder_to_delete");
        deleteFolder(folder);
        System.out.println("Папка удалена: " + folder);
    }

    public static void deleteFolder(Path folder) throws IOException {
        if (Files.exists(folder)) {
            Files.walk(folder)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            System.err.println("Не удалось удалить: " + path);
                        }
                    });
        }
    }
}
