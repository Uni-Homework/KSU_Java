package org.sun1zu.Lab4.n8_09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

public class T4 {
    private static final List<String> IMAGE_EXTENSIONS = List.of(".jpg", ".jpeg", ".png", ".gif", ".bmp", ".webp", ".svg");

    public static void main(String[] args) throws IOException {
        Path sourceFolder = Paths.get("/home/sun1zu/Desktop/Lectures");

        // create this folder
        Path targetFolder = Paths.get("/home/sun1zu/Desktop/Images");

        moveAllImages(sourceFolder, targetFolder);
    }

    public static void moveAllImages(Path source, Path target) throws IOException {
        if (!Files.exists(target)) {
            Files.createDirectories(target);
        }

        Files.walk(source)
                .filter(Files::isRegularFile)
                .filter(T4::isImageFile)
                .forEach(image -> moveImageWithUniqueName(image, target));

        System.out.println("Все картинки перемещены в: " + target);
    }

    private static boolean isImageFile(Path file) {
        String fileName = file.toString().toLowerCase();
        return IMAGE_EXTENSIONS.stream().anyMatch(fileName::endsWith);
    }

    private static void moveImageWithUniqueName(Path source, Path targetFolder) {
        try {
            String extension = getExtension(source);
            String uniqueName = UUID.randomUUID() + extension;
            Path target = targetFolder.resolve(uniqueName);

            Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Перемещён: " + source.getFileName() + " -> " + uniqueName);
        } catch (IOException e) {
            System.err.println("Ошибка при перемещении: " + source);
        }
    }

    private static String getExtension(Path path) {
        String name = path.toString();
        int lastDot = name.lastIndexOf('.');
        return lastDot > 0 ? name.substring(lastDot) : "";
    }
}
