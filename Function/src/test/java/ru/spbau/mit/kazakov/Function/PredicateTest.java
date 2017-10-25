package ru.spbau.mit.kazakov.Function;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredicateTest {
    @Test
    public void testConstructor() {
        new Predicate<Double>(){
            @Override
            public Boolean apply(Double argument) {
                return argument > 7;
            }
        };
    }

    @Test
    public void testALWAYS_TRUE() {
        assertTrue((boolean) Predicate.ALWAYS_TRUE.apply(new Object()));
    }

    @Test
    public void testALWAYS_FALSE() {
        assertFalse((boolean) Predicate.ALWAYS_FALSE.apply(new Object()));
    }

    @Test
    public void testOr() {
        Predicate<Boolean> somePredicate = argument -> argument;
        Predicate<Integer> anotherPredicate = argument -> argument >= 111;
        Function2<Boolean, Integer, Boolean> or = somePredicate.or(anotherPredicate);

        assertFalse(or.apply(false, 1));
        assertTrue(or.apply(true, 110));
        assertTrue(or.apply(false, 111));
        assertTrue(or.apply(true, 1000));
    }

    @Test
    public void testAnd() {
        Predicate<Boolean> somePredicate = argument -> argument;
        Predicate<Integer> anotherPredicate = argument -> argument >= 111;
        Function2<Boolean, Integer, Boolean> and = somePredicate.and(anotherPredicate);

        assertFalse(and.apply(false, 1));
        assertFalse(and.apply(true, 110));
        assertFalse(and.apply(false, 111));
        assertTrue(and.apply(true, 1000));
    }

    @Test
    public void testNot() {
        Predicate<String> predicate = argument -> argument.equals("Math is painful");
        Predicate<String> not = predicate.not();

        assertTrue(not.apply("Math is wonderful"));
        assertFalse(not.apply("Math is painful"));
    }

}