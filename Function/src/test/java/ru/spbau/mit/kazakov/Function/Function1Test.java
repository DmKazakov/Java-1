package ru.spbau.mit.kazakov.Function;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function1Test {
    @Test
    public void testConstructor() {
        new Function1<Integer, Double>() {
            @Override
            public Double apply(Integer argument) {
                return argument / 2.0;
            }
        };
    }

    @Test
    public void testApply() {
        Function1<Integer, Integer> function = argument -> argument * 2;
        assertEquals(new Integer(10), function.apply(5));
    }

    @Test
    public void compose() {
        Function1<Integer, Long> someFunction = argument -> (long) (argument / 5);
        Function1<Long, String> anotherFunction = Object::toString;
        Function1<Integer, String> composition = someFunction.compose(anotherFunction);
        assertEquals("6", composition.apply(30));
    }

}