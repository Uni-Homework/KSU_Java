package org.sun1zu.Lab4.n8_06;

import java.util.Arrays;

public class T5 {
    public static void main(String[] args) {
        String[] array = new String[5];

        for (int i = 1; i <= 5; i++) {
            array[i - 1] = "x".repeat(i);
        }

        System.out.println(Arrays.toString(array));

        // Красивый вывод
        System.out.println("{");
        for (String s : array) {
            System.out.println("    \"" + s + "\",");
        }
        System.out.println("}");
    }
}
