package org.sun1zu.ExamTasks.T3;

/**
 * Контракт для операций калькулятора
 */
public interface ICalcOperations {
    long addition(String a, String b);
    long subtraction(String a, String b);
    long multiplication(String a, String b);
    long division(String a, String b);
    long remainder(String a, String b);
}