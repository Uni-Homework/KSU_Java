package org.sun1zu.ExamTasks.T3;

/**
 * Исключение для ошибок при вычислениях
 * Расширяет RuntimeException для unchecked-обработки
 */
public class ArithmeticOperationException extends RuntimeException {

    public ArithmeticOperationException(String msg) {
        super(msg);
    }

    public ArithmeticOperationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}