package ru.mit.spbau.kazakov.arithmetic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.mit.spbau.kazakov.exception.ArithmeticException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpressionParserTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testToPolishNotation1() throws ArithmeticException {
        List<String> polishNotation = ExpressionParser.toPolishNotation("( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 + 6 )");
        assertEquals(Arrays.asList("1", "2", "+", "3", "4", "/", "5", "6", "+", "^", "*"), polishNotation);
    }

    @Test
    public void testToPolishNotation2() throws ArithmeticException {
        List<String> polishNotation = ExpressionParser.toPolishNotation("( -4 + 6 ) % 3 - ( 5 - 4 ) * ( 11 + 54 )");
        assertEquals(Arrays.asList("-4", "6", "+", "3", "%", "5", "4", "-", "11", "54", "+", "*", "-"), polishNotation);
    }

    @Test
    public void testToPolishNotationThrowsArithmeticException() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Unsupported operator: xor");
        ExpressionParser.toPolishNotation("( 1 + 2 ) * ( 3 / 4 ) ^ ( 5 xor 6 )");
    }

}