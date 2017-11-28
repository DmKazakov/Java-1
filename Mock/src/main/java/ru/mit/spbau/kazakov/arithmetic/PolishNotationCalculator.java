package ru.mit.spbau.kazakov.arithmetic;

import com.google.common.collect.Iterables;
import org.jetbrains.annotations.NotNull;
import ru.mit.spbau.kazakov.exception.ArithmeticException;
import ru.mit.spbau.kazakov.exception.EmptyStackException;
import ru.mit.spbau.kazakov.util.Stack;

import java.util.List;

/**
 * A class for evaluating expressions in Polish notation.
 * Supported operators: +, -, *, /, %, ^
 */
public class PolishNotationCalculator {
    /**
     * Initializes processing stack.
     *
     * @param processingStack a stack for evaluating expression
     */
    public PolishNotationCalculator(@NotNull Stack<Double> processingStack) {
        this.processingStack = processingStack;
    }

    /**
     * Evaluates specified expression.
     *
     * @param polishNotation an expression in Polish notation
     * @return value of the evaluated expression
     */
    public double evaluate(@NotNull List<String> polishNotation) throws ArithmeticException {
        if (polishNotation.size() == 0) {
            throw new ArithmeticException("Empty expression");
        }

        try {
            processingStack.push(Double.parseDouble(polishNotation.get(0)));
            processingStack.push(Double.parseDouble(polishNotation.get(1)));

            for (String token : Iterables.skip(polishNotation, 2)) {
                if (ArithmeticUtility.isOperator(token)) {
                    double operand2 = processingStack.pop();
                    double operand1 = processingStack.pop();
                    double operationResult = ArithmeticUtility.apply(token, operand1, operand2);
                    processingStack.push(operationResult);
                } else if (ArithmeticUtility.isNumeric(token)) {
                    processingStack.push(Double.parseDouble(token));
                } else {
                    throw new ArithmeticException("Unsupported operator: " + token);
                }
            }

            if (processingStack.size() != 1) {
                throw new ArithmeticException("Invalid expression");
            }

            return processingStack.pop();
        }
        catch (EmptyStackException exception) {
            throw new ArithmeticException("Invalid expression");
        }
    }

    private Stack<Double> processingStack;
}
