package ru.spbau.mit.kazakov.Maybe;

import org.junit.Test;

import static org.junit.Assert.*;

public class MaybeTest {
    @Test
    public void testJustInteger() throws getNothingException {
        Maybe<Integer> maybeInteger = Maybe.just(5);
        int storedValue = maybeInteger.get();
        assertEquals(5, storedValue);
    }

    @Test
    public void testNothingBoolean() {
        Maybe<Boolean> maybeBoolean = Maybe.nothing();
        assertFalse(maybeBoolean.isPresent());
    }

    @Test
    public void testGetDouble() throws getNothingException {
        Maybe<Double> maybeDouble = Maybe.just(-7.0);
        double storedValue = maybeDouble.get();
        assertEquals(-7.0, storedValue, 0.000000001);
    }

    @Test(expected = getNothingException.class)
    public void testGetCharacterThrowsException() throws getNothingException {
        Maybe<Character> maybeCharacter = Maybe.nothing();
        char storedValue = maybeCharacter.get();
    }

    @Test
    public void testIsPresentFalse() {
        Maybe<Byte> maybeBoolean = Maybe.nothing();
        assertFalse(maybeBoolean.isPresent());
    }

    @Test
    public void testIsPresentTrue() {
        Maybe<String> maybeString = Maybe.just("abacaba");
        assertTrue(maybeString.isPresent());
    }

    @Test
    public void testMapJust() throws getNothingException {
        Maybe<Integer> maybeInteger = Maybe.just(10);
        Maybe<Integer> mappedMaybeInteger = maybeInteger.map(value -> value * value);
        int mappedValue = mappedMaybeInteger.get();
        assertEquals(100, mappedValue);
    }

    @Test
    public void testMapNothing() throws getNothingException {
        Maybe<Integer> maybeInteger = Maybe.nothing();
        Maybe<Integer> mappedMaybeInteger = maybeInteger.map(value -> value * value);
        assertFalse(mappedMaybeInteger.isPresent());
    }
}