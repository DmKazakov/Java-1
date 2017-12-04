package ru.spbau.mit.kazakov.Function;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class CollectionsTest {
    @Test
    public void testMapEmpty() {
        Function1<Integer, Integer> function = argument -> argument * argument;
        List<Integer> list = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.map(function, list));
    }

    @Test
    public void testMap() {
        Function1<Integer, Integer> function = argument -> argument * argument;
        List<Integer> list = asList(-2, -1, 0, 4, 10);
        List<Integer> expected = asList(4, 1, 0, 16, 100);

        assertEquals(expected, Collections.map(function, list));
    }

    @Test
    public void testFilterEmptyInput() {
        Predicate<Integer> function = argument -> Math.abs(argument) > 10;
        List<Integer> list = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.filter(function, list));
    }

    @Test
    public void testFilterEmptyOutput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 1;
        List<Integer> list = asList(10, 3, -10, -99, 0, -23, 1001);
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.filter(function, list));
    }

    @Test
    public void testFilterAllListOutput() {
        Predicate<Integer> function = argument -> Math.abs(argument) != 1010;
        List<Integer> list = asList(10, 3, -10, -99, 0, -23, 1001);
        List<Integer> expected = asList(10, 3, -10, -99, 0, -23, 1001);

        assertEquals(expected, Collections.filter(function, list));
    }

    @Test
    public void testFilter() {
        Predicate<Integer> function = argument -> Math.abs(argument) > 10;
        List<Integer> list = asList(10, 3, -10, -99, 0, -23, 1001);
        List<Integer> expected = asList(-99, -23, 1001);

        assertEquals(expected, Collections.filter(function, list));
    }

    @Test
    public void testTakeWhileEmptyInput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.takeWhile(function, list));
    }

    @Test
    public void testTakeWhileEmptyOutput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = asList(1, -77, 77, 77, 0, 77, -77, 198);
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.takeWhile(function, list));
    }

    @Test
    public void testTakeWhileAllListOutput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = asList(-77, 77, 77, 77, -77);
        List<Integer> expected = asList(-77, 77, 77, 77, -77);

        assertEquals(expected, Collections.takeWhile(function, list));
    }

    @Test
    public void testTakeWhile() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = asList(-77, 77, 77, 0, 77, -77, 198);
        List<Integer> expected = asList(-77, 77, 77);

        assertEquals(expected, Collections.takeWhile(function, list));
    }

    @Test
    public void testTakeUnlessEmptyInput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = new ArrayList<>();
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.takeUnless(function, list));
    }

    @Test
    public void testTakeUnlessEmptyOutput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = asList(-77, 77, 77, 0, 77, -77, 198);
        List<Integer> expected = new ArrayList<>();

        assertEquals(expected, Collections.takeUnless(function, list));
    }

    @Test
    public void testTakeUnlessAllListOutput() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = asList(0, 1, -99, 121, 89898, 32423);
        List<Integer> expected = asList(0, 1, -99, 121, 89898, 32423);

        assertEquals(expected, Collections.takeUnless(function, list));
    }

    @Test
    public void testTakeUnless() {
        Predicate<Integer> function = argument -> Math.abs(argument) == 77;
        List<Integer> list = asList(0, 1, -99, 121, -77, 89898, 77, 32423);
        List<Integer> expected = asList(0, 1, -99, 121);

        assertEquals(expected, Collections.takeUnless(function, list));
    }

    @Test
    public void testFoldlEmpty() {
        Function2<Integer, Integer, Integer> function = (firstArgument, secondArgument) -> secondArgument / firstArgument;
        List<Integer> list = new ArrayList<>();
        assertEquals(1, (int) Collections.foldl(function, 1, list));
    }

    @Test
    public void testFoldl() {
        Function2<Integer, Integer, Integer> function = (firstArgument, secondArgument) -> secondArgument / firstArgument;
        List<Integer> list = asList(10, 20, 100);
        assertEquals(50, (int) Collections.foldl(function, 1, list));
    }

    @Test
    public void testFoldrEmpty() {
        Function2<Integer, Integer, Integer> function = (firstArgument, secondArgument) -> firstArgument / secondArgument;
        List<Integer> list = new ArrayList<>();
        assertEquals(1, (int) Collections.foldr(function, 1, list));
    }

    @Test
    public void testFoldr() {
        Function2<Integer, Integer, Integer> function = (firstArgument, secondArgument) -> firstArgument / secondArgument;
        List<Integer> list = asList(100, 20, 10);
        assertEquals(50, (int) Collections.foldr(function, 1, list));
    }
}