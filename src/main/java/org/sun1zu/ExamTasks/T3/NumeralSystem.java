package org.sun1zu.ExamTasks.T3;

/**
 * Поддерживаемые системы счисления с соответствующими основаниями
 */
public enum NumeralSystem {
    BINARY(2, "01"),
    OCTAL(8, "01234567"),
    DECIMAL(10, "0123456789"),
    HEXADECIMAL(16, "0123456789ABCDEF");

    private final int radix;
    private final String allowedChars;

    NumeralSystem(int radix, String chars) {
        this.radix = radix;
        this.allowedChars = chars;
    }

    public int getRadix() { return radix; }
    public String getAllowedChars() { return allowedChars; }

    public boolean isValidChar(char c) {
        return allowedChars.indexOf(Character.toUpperCase(c)) != -1;
    }
}