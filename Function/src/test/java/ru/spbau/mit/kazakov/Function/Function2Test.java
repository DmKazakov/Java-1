package ru.spbau.mit.kazakov.Function;

import org.junit.Test;

import static org.junit.Assert.*;

public class Function2Test {
    @Test
    public void testConstructor() {
        new Function2<Long, Integer, Double>() {
            @Override
            public Double apply(Long firstArgument, Integer secondArgument) {
                return (firstArgument * secondArgument) / 5.0;
            }
        };
    }

    @Test
    public void testApply() {
        Function2<Byte, Character, String> function =
                (firstArgument, secondArgument) -> firstArgument.toString() + secondArgument.toString();
        assertEquals("8j", function.apply((byte) 8, 'j'));
    }

    @Test
    public void testCompose() {
        Function2<Integer, Integer, Boolean> someFunction = Integer::equals;
        Function1<Boolean, Byte> anotherFunction = argument -> {
            if (argument) {
                return (byte) 1;
            } else {
                return (byte) 0;
            }
        };
        Function2<Integer, Integer, Byte> composition = someFunction.compose(anotherFunction);
        assertEquals(1, (byte) composition.apply(979, 979));
    }

    @Test
    public void bind1() {
        Function2<Integer, Integer, Integer> someFunction =
                (firstArgument, secondArgument) -> (int) Math.pow(firstArgument, secondArgument);
        Function1<Integer, Integer> anotherFunction = someFunction.bind1(2);
        assertEquals(32, (int) anotherFunction.apply(5));
    }

    @Test
    public void bind2() {
        Function2<Integer, Integer, Integer> someFunction =
                (firstArgument, secondArgument) -> (int) Math.pow(firstArgument, secondArgument);
        Function1<Integer, Integer> anotherFunction = someFunction.bind2(3);
        assertEquals(64, (int) anotherFunction.apply(4));
    }

    @Test
    public void curry() {
        Function2<Integer, Integer, Integer> function =
                (firstArgument, secondArgument) -> Math.abs(firstArgument) + Math.abs(secondArgument);
        Function1<Integer, Function1<Integer, Integer>> curried = function.curry();
        assertEquals(46, (int) curried.apply(-44).apply(2));
    }

}