package org.sun1zu.ExamTasks.T3;

/**
 * Модель математического выражения: левый операнд, оператор, правый операнд
 */
public final class MathExpression {
    private final String lhs;
    private final String rhs;
    private final char op;

    public MathExpression(String left, char operator, String right) {
        this.lhs = left;
        this.op = operator;
        this.rhs = right;
    }

    public String getLeft() { return lhs; }
    public String getRight() { return rhs; }
    public char getOperator() { return op; }

    @Override
    public String toString() {
        return String.format("%s %c %s", lhs, op, rhs);
    }
}