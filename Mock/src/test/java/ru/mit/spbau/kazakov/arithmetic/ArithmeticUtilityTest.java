package ru.mit.spbau.kazakov.arithmetic;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.mit.spbau.kazakov.exception.ArithmeticException;

import static org.junit.Assert.*;

public class ArithmeticUtilityTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testIsNumericEmpty() {
        assertFalse(ArithmeticUtility.isNumeric(""));
    }

    @Test
    public void testIsNumericMinus() {
        assertFalse(ArithmeticUtility.isNumeric("+"));
    }

    @Test
    public void testIsNumericPlus() {
        assertFalse(ArithmeticUtility.isNumeric("-"));
    }

    @Test
    public void testIsNumericInt() {
        assertTrue(ArithmeticUtility.isNumeric("54576545332"));
    }

    @Test
    public void testIsNumericDouble() {
        assertTrue(ArithmeticUtility.isNumeric("32312.54576545332"));
    }

    @Test
    public void testIsOperatorPlus() {
        assertTrue(ArithmeticUtility.isOperator("+"));
    }

    @Test
    public void testIsOperatorMinus() {
        assertTrue(ArithmeticUtility.isOperator("-"));
    }

    @Test
    public void testIsOperatorMultiply() {
        assertTrue(ArithmeticUtility.isOperator("*"));
    }

    @Test
    public void testIsOperatorDivide() {
        assertTrue(ArithmeticUtility.isOperator("/"));
    }

    @Test
    public void testIsOperatorModulo() {
        assertTrue(ArithmeticUtility.isOperator("%"));
    }

    @Test
    public void testIsOperatorPow() {
        assertTrue(ArithmeticUtility.isOperator("^"));
    }

    @Test
    public void testIsOperatorIncrement() {
        assertFalse(ArithmeticUtility.isOperator("++"));
    }

    @Test
    public void testIsOperatorXor() {
        assertFalse(ArithmeticUtility.isOperator("xor"));
    }

    @Test
    public void testIsLeftAssociativeThrowsArithmeticException() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Unsupported operator: xor");
        ArithmeticUtility.isLeftAssociative("xor");
    }

    @Test
    public void testIsLeftAssociativePlus() throws ArithmeticException {
        assertTrue(ArithmeticUtility.isLeftAssociative("+"));
    }

    @Test
    public void testIsLeftAssociativeMinus() throws ArithmeticException {
        assertTrue(ArithmeticUtility.isLeftAssociative("-"));
    }

    @Test
    public void testIsLeftAssociativeMultiply() throws ArithmeticException {
        assertTrue(ArithmeticUtility.isLeftAssociative("*"));
    }

    @Test
    public void testIsLeftAssociativeDivide() throws ArithmeticException {
        assertTrue(ArithmeticUtility.isLeftAssociative("/"));
    }

    @Test
    public void testIsLeftAssociativeModulo() throws ArithmeticException {
        assertTrue(ArithmeticUtility.isLeftAssociative("%"));
    }

    @Test
    public void testIsLeftAssociativePow() throws ArithmeticException {
        assertFalse(ArithmeticUtility.isLeftAssociative("^"));
    }

    @Test
    public void testComparePrecedencesThrowsArithmeticException() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Unsupported operator: ++");
        ArithmeticUtility.comparePrecedences("++", "-");
    }

    @Test
    public void testComparePrecedencesEqual() throws ArithmeticException {
        assertTrue(ArithmeticUtility.comparePrecedences("+", "-") == 0);
    }

    @Test
    public void testComparePrecedencesLess() throws ArithmeticException {
        assertTrue(ArithmeticUtility.comparePrecedences("+", "*") < 0);
    }

    @Test
    public void testComparePrecedencesGreater() throws ArithmeticException {
        assertTrue(ArithmeticUtility.comparePrecedences("^", "%") > 0);
    }

    @Test
    public void testIsGoesAfterThrowsArithmeticException() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Unsupported operator: **");
        ArithmeticUtility.isGoesAfter("+", "**");
    }

    @Test
    public void testIsGoesAfterTrue() throws ArithmeticException {
        assertTrue(ArithmeticUtility.isGoesAfter("+", "%"));
    }

    @Test
    public void testIsGoesAfterFalse() throws ArithmeticException {
        assertFalse(ArithmeticUtility.isGoesAfter("/", "-"));
    }

    @Test
    public void testApplyThrowsArithmeticException() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Unsupported operator: xor");
        ArithmeticUtility.apply("xor", 5.0, 0.5);
    }

    @Test
    public void testApplyPlus() throws ArithmeticException {
        assertEquals(5.5, ArithmeticUtility.apply("+", 5.0, 0.5), 0.000001);
    }

    @Test
    public void testApplyMinus() throws ArithmeticException {
        assertEquals(103.3, ArithmeticUtility.apply("-", 103.4, 0.1), 0.000001);
    }

    @Test
    public void testApplyMultiply() throws ArithmeticException {
        assertEquals(10.0, ArithmeticUtility.apply("*", 5.0, 2.0), 0.000001);
    }

    @Test
    public void testApplyDivide() throws ArithmeticException {
        assertEquals(5.0, ArithmeticUtility.apply("/", 40.0, 8.0), 0.000001);
    }

    @Test
    public void testApplyDivideThrowsArithmeticException() throws ArithmeticException {
        thrown.expect(ArithmeticException.class);
        thrown.expectMessage("Division by zero");
        assertEquals(5.0, ArithmeticUtility.apply("/", 40.0, 0.0), 0.000001);
    }

    @Test
    public void testApplyModulo() throws ArithmeticException {
        assertEquals(1.0, ArithmeticUtility.apply("%", 15.0, 7.0), 0.000001);
    }

    @Test
    public void testApplyPow() throws ArithmeticException {
        assertEquals(64.0, ArithmeticUtility.apply("^", 2.0, 6.0), 0.000001);
    }

}