package org.sun1zu.ExamTasks.T3;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * Главный класс приложения - консольный калькулятор
 * Поддерживает 4 системы счисления и 5 арифметических операций
 */
public class CalcApplication {
    private final Scanner input;
    private final PrintStream output;

    public CalcApplication(Scanner scanner, PrintStream printer) {
        this.input = scanner;
        this.output = printer;
    }

    public static void main(String[] args) {
        try (Scanner consoleScanner = new Scanner(System.in)) {
            CalcApplication app = new CalcApplication(consoleScanner, System.out);
            app.start();
        }
    }

    public void start() {
        displayAvailableSystems();

        while (true) {
            NumeralSystem system = promptSystemSelection();
            if (system == null) {
                output.println("Application terminated.");
                break;
            }
            runCalculationSession(system);
        }
    }

    private void displayAvailableSystems() {
        output.println("=== SUPPORTED NUMERAL SYSTEMS ===");
        output.println("  [HEX] - Hexadecimal (0-9, A-F)");
        output.println("  [DEC] - Decimal (0-9)");
        output.println("  [OCT] - Octal (0-7)");
        output.println("  [BIN] - Binary (0-1)");
        output.println("==================================");
    }

    private NumeralSystem promptSystemSelection() {
        while (true) {
            output.print("\nSelect numeral system (or EXIT): ");
            String cmd = input.nextLine().trim().toUpperCase();

            if (cmd.equals("EXIT")) {
                return null;
            }

            try {
                return NumeralSystem.valueOf(cmd);
            } catch (IllegalArgumentException ex) {
                output.println("Unrecognized system! Try again.");
            }
        }
    }

    private void runCalculationSession(NumeralSystem system) {
        int radix = system.getRadix();
        ICalcOperations calculator = new UniversalCalcEngine(radix);

        while (true) {
            try {
                output.print("\nEnter expression: ");
                String line = input.nextLine().trim().toUpperCase();

                MathExpression expr = parseMathExpression(line, system);
                long result = evaluateExpression(expr, calculator);

                displayResult(result, radix);

                output.print("Continue in same system? (y/n): ");
                String answer = input.nextLine().trim().toLowerCase();
                if (!answer.equals("y") && !answer.equals("yes")) {
                    break;
                }
            } catch (ArithmeticOperationException ex) {
                output.println("ERROR: " + ex.getMessage());
            }
        }
    }

    private MathExpression parseMathExpression(String expr, NumeralSystem system) {
        String[] tokens = expr.split("\\s+");

        if (tokens.length != 3) {
            throw new ArithmeticOperationException(
                    "Invalid format. Expected: operand operator operand"
            );
        }

        if (!isValidOperand(tokens[0], system)) {
            throw new ArithmeticOperationException("Invalid left operand: " + tokens[0]);
        }

        if (!isValidOperand(tokens[2], system)) {
            throw new ArithmeticOperationException("Invalid right operand: " + tokens[2]);
        }

        char operator = tokens[1].charAt(0);
        if ("+-*/%".indexOf(operator) == -1) {
            throw new ArithmeticOperationException(
                    "Unsupported operator: " + operator + ". Use + - * / %"
            );
        }

        return new MathExpression(tokens[0], operator, tokens[2]);
    }

    private boolean isValidOperand(String operand, NumeralSystem system) {
        if (operand == null || operand.isEmpty()) return false;

        String normalized = operand.toUpperCase();
        if (normalized.startsWith("+") || normalized.startsWith("-")) {
            normalized = normalized.substring(1);
            if (normalized.isEmpty()) return false;
        }

        for (char ch : normalized.toCharArray()) {
            if (!system.isValidChar(ch)) {
                return false;
            }
        }
        return true;
    }

    private long evaluateExpression(MathExpression expr, ICalcOperations calc) {
        char op = expr.getOperator();
        String left = expr.getLeft();
        String right = expr.getRight();

        return switch (op) {
            case '+' -> calc.addition(left, right);
            case '-' -> calc.subtraction(left, right);
            case '*' -> calc.multiplication(left, right);
            case '/' -> calc.division(left, right);
            case '%' -> calc.remainder(left, right);
            default -> throw new ArithmeticOperationException("Unknown operation: " + op);
        };
    }

    private void displayResult(long value, int currentRadix) {
        output.println("=== RESULT ===");
        output.println(String.format("  In current base (%d): %s",
                currentRadix, ArithmeticCore.toStringBase(value, currentRadix)));

        // доп. вывод в других системах для удобства
        output.println("  Conversions:");
        output.println(String.format("    HEX: %s", ArithmeticCore.toStringBase(value, 16)));
        output.println(String.format("    DEC: %d", value));
        output.println(String.format("    OCT: %s", ArithmeticCore.toStringBase(value, 8)));
        output.println(String.format("    BIN: %s", ArithmeticCore.toStringBase(value, 2)));
        output.println("===============");
    }
}