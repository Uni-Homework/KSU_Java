package org.sun1zu.ExamTasks.T3;

/**
 * Ядро арифметических операций с контролем переполнения
 * @author dev-team
 * @version 2.0
 */
public final class ArithmeticCore {

    private ArithmeticCore() {
        // утилитарный класс - запрещаем создание экземпляров
    }

    /**
     * Безопасное сложение с проверкой переполнения
     */
    public static long safeAdd(long x, long y) {
        try {
            return Math.addExact(x, y);
        } catch (ArithmeticException ex) {
            throw new ArithmeticOperationException(
                    String.format("Overflow detected: %d + %d", x, y)
            );
        }
    }

    public static long safeSub(long x, long y) {
        try {
            return Math.subtractExact(x, y);
        } catch (ArithmeticException ex) {
            throw new ArithmeticOperationException(
                    String.format("Overflow detected: %d - %d", x, y)
            );
        }
    }

    public static long safeMul(long x, long y) {
        try {
            return Math.multiplyExact(x, y);
        } catch (ArithmeticException ex) {
            throw new ArithmeticOperationException(
                    String.format("Overflow detected: %d * %d", x, y)
            );
        }
    }

    public static long safeDiv(long x, long y) {
        if (y == 0L) {
            throw new ArithmeticOperationException("Division by zero is prohibited");
        }
        return x % y == 0 ? x / y : 0L;
    }

    public static long safeMod(long x, long y) {
        if (y == 0L) {
            throw new ArithmeticOperationException("Modulo by zero is prohibited");
        }
        return x % y;
    }

    /**
     * Конвертация числа в строку в указанной системе счисления
     * @param value исходное число
     * @param radix система счисления (2,8,10,16)
     */
    public static String toStringBase(long value, int radix) {
        validateRadix(radix);
        return Long.toString(value, radix).toUpperCase();
    }

    /**
     * Парсинг строки в число с указанной системой счисления
     */
    public static long fromStringBase(String token, int radix) {
        validateRadix(radix);
        if (token == null || token.trim().isEmpty()) {
            throw new ArithmeticOperationException("Empty numeric token");
        }
        try {
            return Long.parseLong(token, radix);
        } catch (NumberFormatException ex) {
            throw new ArithmeticOperationException(
                    String.format("Invalid number format or overflow: '%s'", token)
            );
        }
    }

    private static void validateRadix(int radix) {
        if (radix != 2 && radix != 8 && radix != 10 && radix != 16) {
            throw new ArithmeticOperationException(
                    String.format("Unsupported radix: %d. Allowed: 2,8,10,16", radix)
            );
        }
    }
}