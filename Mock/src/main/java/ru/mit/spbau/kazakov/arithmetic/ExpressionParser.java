package ru.mit.spbau.kazakov.arithmetic;

import org.jetbrains.annotations.NotNull;
import ru.mit.spbau.kazakov.util.Stack;
import ru.mit.spbau.kazakov.exception.ArithmeticException;

import java.util.ArrayList;
import java.util.List;

/**
 * A class for parsing mathematical expressions.
 * Operators and operands must be separated by whitespace character.
 * Supported operators: +, -, *, /, %, ^
 * Don't use brackets for unary minus.
 */
public class ExpressionParser {
    /**
     * Parses specified expression to Polish notation.
     *
     * @param expression to parse
     * @return resulting stack
     * @throws ArithmeticException if input is invalid
     */
    public static List<String> toPolishNotation(@NotNull String expression) throws ArithmeticException {
        if (expression.trim().length() == 0) {
            throw new ArithmeticException("Empty expression");
        }

        ArrayList<String> polishNotation = new ArrayList<>();
        Stack<String> processingStack = new Stack<>();

        for (String token : expression.trim().split("\\s+")) {
            if (ArithmeticUtility.isOperator(token)) {
                while (!processingStack.empty() && ArithmeticUtility.isOperator(processingStack.peek())
                        && ArithmeticUtility.isGoesAfter(token, processingStack.peek())) {
                    polishNotation.add(processingStack.pop());
                }
                processingStack.push(token);
            } else if (token.equals("(")) {
                processingStack.push("(");
            } else if (token.equals(")")) {
                while (!processingStack.empty() && !processingStack.peek().equals("(")) {
                    polishNotation.add(processingStack.pop());
                }
                processingStack.pop();
            } else if (ArithmeticUtility.isNumeric(token)) {
                polishNotation.add(token);
            } else {
                throw new ArithmeticException("Unsupported operator: " + token);
            }
        }

        while (!processingStack.empty()) {
            polishNotation.add(processingStack.pop());
        }

        return polishNotation;
    }

}
