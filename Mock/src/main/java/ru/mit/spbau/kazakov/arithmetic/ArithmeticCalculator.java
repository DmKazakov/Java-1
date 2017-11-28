package ru.mit.spbau.kazakov.arithmetic;

import ru.mit.spbau.kazakov.exception.ArithmeticException;
import ru.mit.spbau.kazakov.util.Stack;

import java.util.List;

/**
 * A class for evaluating arithmetic expressions.
 * Operators and operands must be separated by whitespace character.
 * Supported operators: +, -, *, /, %, ^
 * Don't use brackets for unary minus.
 */
public class ArithmeticCalculator {
    public static void main(String[] args) {
        String expression = String.join(" ", args);
        PolishNotationCalculator calculator = new PolishNotationCalculator(new Stack<>());

        try {
            List<String> polishNotation = ExpressionParser.toPolishNotation(expression);
            System.out.println(calculator.evaluate(polishNotation));
        } catch (ArithmeticException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
