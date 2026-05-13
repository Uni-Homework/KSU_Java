package org.sun1zu.Lab4.n8_09;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class T1 {
    static void main(String[] args) throws IOException {
        IO.println(Files.lines(Path.of("input.txt"))
                .filter(String::isBlank)
                .count());
    }
}
