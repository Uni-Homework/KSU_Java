package org.sun1zu.Lab4;

public class T4_Palindrome {
    public boolean Solve(String input) {
        for (int i = 0; i < input.length() / 2; i++) {
            int j = input.length() - i - 1;
            if (input.charAt(i) != input.charAt(j)) return false;
        }
        return true;
    }
}
