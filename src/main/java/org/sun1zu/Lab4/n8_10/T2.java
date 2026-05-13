package org.sun1zu.Lab4.n8_10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class T2 {
    public static void main(String[] args) throws IOException {
        Path folder = Paths.get("/home/sun1zu/Desktop");
        long size = getFolderSize(folder);
        System.out.println("Размер папки: " + size + " байт");
        System.out.println("Размер папки: " + formatSize(size));
    }

    public static long getFolderSize(Path folder) throws IOException {
        return Files.walk(folder)
                .filter(Files::isRegularFile)
                .mapToLong(file -> {
                    try {
                        return Files.size(file);
                    } catch (IOException e) {
                        return 0;
                    }
                })
                .sum();
    }

    public static String formatSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f MB", bytes / (1024.0 * 1024));
        return String.format("%.2f GB", bytes / (1024.0 * 1024 * 1024));
    }
}
