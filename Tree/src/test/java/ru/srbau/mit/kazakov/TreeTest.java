package ru.srbau.mit.kazakov;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeTest {
    @Test
    public void testConstructor() {
        new Tree<Integer>();
    }

    @Test
    public void testAddNotExisting() {
        Tree<Boolean> tree = new Tree<Boolean>();
        assertTrue(tree.add(false));
        assertTrue(tree.contains(false));
    }

    @Test
    public void testAddExisting() {
        Tree<Integer> tree = new Tree<Integer>();
        tree.add(1);
        assertFalse(tree.add(1));
        assertTrue(tree.contains(1));
    }

    @Test
    public void testAddOneHundred() {
        Tree<Integer> tree = new Tree<Integer>();
        for (int i = 0; i < 100; i++) {
            assertTrue(tree.add(i));
        }

        assertTrue(tree.contains(50));
    }

    @Test
    public void sizeEmpty() {
        Tree<String> tree = new Tree<String>();
        assertEquals(0, tree.size());
    }

    @Test
    public void sizeOne() {
        Tree<String> tree = new Tree<String>();
        tree.add("abacaba");
        assertEquals(1, tree.size());
    }

    @Test
    public void testSizeOneHundred() {
        Tree<Integer> tree = new Tree<Integer>();
        for (int i = 0; i < 100; i++) {
            assertTrue(tree.add(i));
        }

        assertEquals(100, tree.size());
    }

    @Test
    public void testContainsEmpty() {
        Tree<Character> tree = new Tree<Character>();
        assertFalse(tree.contains('z'));
    }

    @Test
    public void testContainsNotExisting() {
        Tree<Character> tree = new Tree<Character>();
        tree.add('a');
        assertFalse(tree.contains('z'));
    }

    @Test
    public void testContainsExisting() {
        Tree<Character> tree = new Tree<Character>();
        tree.add('a');
        assertTrue(tree.contains('a'));
    }
}