package org.sun1zu.Lab4.n8_09;

public class T6 {
    public static void main(String[] args) {
        String result = generatePattern();
        System.out.println(result);
    }

    public static String generatePattern() {
        StringBuilder sb = new StringBuilder("-1-");

        for (int i = 1; i <= 4; i++) {
            sb.append(i).append(i + 1).append("-");
        }

        return sb.toString();
    }
}
