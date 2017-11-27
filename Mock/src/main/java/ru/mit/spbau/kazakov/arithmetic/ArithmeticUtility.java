package ru.mit.spbau.kazakov.arithmetic;

import org.jetbrains.annotations.NotNull;
import ru.mit.spbau.kazakov.exception.ArithmeticException;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * A class with arithmetic utilities.
 */
public class ArithmeticUtility {
    private static final Map<String, Operator> OPERATORS = new HashMap<>();

    static {
        OPERATORS.put("+", Operator.ADDITION);
        OPERATORS.put("-", Operator.SUBTRACTION);
        OPERATORS.put("*", Operator.MULTIPLICATION);
        OPERATORS.put("/", Operator.DIVISION);
        OPERATORS.put("%", Operator.MODULO);
        OPERATORS.put("^", Operator.EXPONENTIATION);
    }

    private enum Operator {
        ADDITION(0, true) {
            @Override
            public double apply(double augend, double addend) {
                return augend + addend;
            }
        },
        SUBTRACTION(0, true) {
            @Override
            public double apply(double minuend, double subtrahend) {
                return minuend - subtrahend;
            }
        },
        MULTIPLICATION(5, true) {
            @Override
            public double apply(double multiplier, double multiplicand) {
                return multiplier * multiplicand;
            }
        },
        DIVISION(5, true) {
            @Override
            public double apply(double dividend, double divisor) throws ArithmeticException {
                if (divisor == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return dividend / divisor;
            }
        },
        MODULO(5, true) {
            @Override
            public double apply(double dividend, double divisor) {
                return dividend % divisor;
            }
        },
        EXPONENTIATION(10, false) {
            @Override
            public double apply(double base, double exponent) {
                return Math.pow(base, exponent);
            }
        };

        private final int precedence;
        private final boolean isLeftAssociative;

        Operator(int precedence, boolean isLeftAssociative) {
            this.precedence = precedence;
            this.isLeftAssociative = isLeftAssociative;
        }

        public abstract double apply(double operand1, double operand2) throws ArithmeticException;
    }

    private static final Pattern IS_NUMERIC = Pattern.compile("[+-]?\\d+(\\.\\d+)?");

    /**
     * Checks if specified token is a number.
     *
     * @param token to check
     * @return true if specified token is a number, and false otherwise
     */
    public static boolean isNumeric(@NotNull String token) {
        return IS_NUMERIC.matcher(token).matches();
    }

    /**
     * Checks if specified token is an operator.
     *
     * @param token to check
     * @return true if specified token is an operator, and false otherwise
     */
    public static boolean isOperator(@NotNull String token) {
        return OPERATORS.containsKey(token);
    }

    /**
     * Checks if specified operator is left associative.
     *
     * @param operator to check
     * @return true if specified operator is left associative, and false otherwise
     */
    public static boolean isLeftAssociative(@NotNull String operator) throws ArithmeticException {
        if (!ArithmeticUtility.isOperator(operator)) {
            throw new ArithmeticException("Unsupported operator: " + operator);
        }
        return OPERATORS.get(operator).isLeftAssociative;
    }

    /**
     * Compares precedences of specified operators.
     *
     * @param operator1 the first operator
     * @param operator2 the second operator
     * @return negative number if first operator's precedence is smaller than second operator's precedence,
     * zero if precedences are equal, and positive number otherwise.
     */
    public static int comparePrecedences(@NotNull String operator1, @NotNull String operator2) throws ArithmeticException {
        if (!isOperator(operator1)) {
            throw new ArithmeticException("Unsupported operator: " + operator1);
        }
        if (!isOperator(operator2)) {
            throw new ArithmeticException("Unsupported operator: " + operator2);
        }
        return OPERATORS.get(operator1).precedence - OPERATORS.get(operator2).precedence;
    }

    /**
     * Determines which of two operators evaluates first.
     *
     * @param operator1 the first operator
     * @param operator2 the second operator
     * @return true if the second operator evaluates before the first one, and false otherwise
     * @throws ArithmeticException if there is unsupported operator
     */
    public static boolean isGoesAfter(@NotNull String operator1, @NotNull String operator2) throws ArithmeticException {
        return (isLeftAssociative(operator1) && comparePrecedences(operator1, operator2) <= 0)
                || (!isLeftAssociative(operator1) && comparePrecedences(operator1, operator2) < 0);
    }

    /**
     * Applies specified operator to specified arguments.
     *
     * @param operator operator for applying
     * @param operand1 the first argument
     * @param operand2 the second argument
     * @return result of application
     * @throws ArithmeticException if there is unsupported operator
     */
    public static double apply(@NotNull String operator, double operand1, double operand2) throws ArithmeticException {
        if (!isOperator(operator)) {
            throw new ArithmeticException("Unsupported operator: " + operator);
        }
        return OPERATORS.get(operator).apply(operand1, operand2);
    }
}
