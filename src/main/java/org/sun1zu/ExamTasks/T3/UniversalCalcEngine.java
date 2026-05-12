package org.sun1zu.ExamTasks.T3;

/**
 * Реализация операций с автоматической конвертацией между системами счисления
 */
public class UniversalCalcEngine implements ICalcOperations {
    private final int currentRadix;

    public UniversalCalcEngine(int radix) {
        this.currentRadix = radix;
    }

    private long parseOperand(String value) {
        return ArithmeticCore.fromStringBase(value, currentRadix);
    }

    @Override
    public long addition(String a, String b) {
        return ArithmeticCore.safeAdd(parseOperand(a), parseOperand(b));
    }

    @Override
    public long subtraction(String a, String b) {
        return ArithmeticCore.safeSub(parseOperand(a), parseOperand(b));
    }

    @Override
    public long multiplication(String a, String b) {
        return ArithmeticCore.safeMul(parseOperand(a), parseOperand(b));
    }

    @Override
    public long division(String a, String b) {
        return ArithmeticCore.safeDiv(parseOperand(a), parseOperand(b));
    }

    @Override
    public long remainder(String a, String b) {
        return ArithmeticCore.safeMod(parseOperand(a), parseOperand(b));
    }
}