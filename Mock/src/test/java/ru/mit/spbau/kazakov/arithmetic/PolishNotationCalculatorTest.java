package ru.mit.spbau.kazakov.arithmetic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InOrder;
import ru.mit.spbau.kazakov.exception.ArithmeticException;
import ru.mit.spbau.kazakov.util.Stack;

import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PolishNotationCalculatorTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluateEasy() throws ArithmeticException {
        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(2.0, -3.0, -1.0);
        when(stack.size()).thenReturn(1);

        PolishNotationCalculator calculator = new PolishNotationCalculator(stack);
        assertEquals(-1.0,
                calculator.evaluate(Arrays.asList("-3", "2", "%")), 0.00001);

        InOrder inOrder = inOrder(stack);
        inOrder.verify(stack).push(-3.0);
        inOrder.verify(stack).push(2.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(-1.0);
        inOrder.verify(stack).size();
        inOrder.verify(stack).pop();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluateMedium() throws ArithmeticException {
        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(2.0, 1.0, 3.0, 4.0, 1.3333333333333333, 3.0, 6.0, 5.0, 11.0, 4.0, 4194304.0);
        when(stack.size()).thenReturn(1);

        PolishNotationCalculator calculator = new PolishNotationCalculator(stack);
        assertEquals(4194304.0,
                calculator.evaluate(Arrays.asList("1", "2", "+", "4", "3", "/", "*", "5", "6", "+", "^")), 0.00001);

        InOrder inOrder = inOrder(stack);
        inOrder.verify(stack).push(1.0);
        inOrder.verify(stack).push(2.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(3.0);
        inOrder.verify(stack).push(4.0);
        inOrder.verify(stack).push(3.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(1.3333333333333333);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(4.0);
        inOrder.verify(stack).push(5.0);
        inOrder.verify(stack).push(6.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(11.0);
        inOrder.verify(stack, times(2)).pop();
        inOrder.verify(stack).push(4194304.0);
        inOrder.verify(stack).size();
        inOrder.verify(stack).pop();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluateInvalidExpression() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Invalid expression");

        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(2.0, 1.0, 1.0, 5.0);
        when(stack.size()).thenReturn(2);

        PolishNotationCalculator calculator = new PolishNotationCalculator(stack);
        calculator.evaluate(Arrays.asList("1", "2", "%", "5"));

    }

    @Test
    @SuppressWarnings("unchecked")
    public void testEvaluateUnsupportedOperator() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Unsupported operator: div");

        Stack<Double> stack = mock(Stack.class);
        when(stack.pop()).thenReturn(2.0, 1.0);

        PolishNotationCalculator calculator = new PolishNotationCalculator(stack);
        calculator.evaluate(Arrays.asList("1", "2", "div"));

    }

}