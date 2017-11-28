package ru.mit.spbau.kazakov.util;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.mit.spbau.kazakov.exception.EmptyStackException;

import static org.junit.Assert.*;

public class StackTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testConstructor() {
        new Stack<>();
    }

    @Test
    public void testPushReturnValue() {
        Stack<String> stack = new Stack<>();
        assertEquals("abc", stack.push("abc"));
    }

    @Test
    public void testPeek() {
        Stack<String> stack = new Stack<>();
        stack.push("xyz");
        assertEquals("xyz", stack.peek());
    }

    @Test
    public void testOneHundredPeek() {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < 100; i++) {
            stack.push(Integer.toString(i));
            assertEquals(Integer.toString(i), stack.peek());
        }
    }

    @Test
    public void testPopThrowsEmptyStackException() throws EmptyStackException {
        thrown.expect(EmptyStackException.class);
        new Stack<>().pop();
    }

    @Test
    public void testPopReturnValue() throws EmptyStackException {
        Stack<String> stack = new Stack<>();
        stack.push(".");
        assertEquals(".", stack.pop());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, new Stack<>().size());
    }

    @Test
    public void testSizeOnePushed() {
        Stack<Integer> stack = new Stack<>();
        stack.push(33);
        assertEquals(1, stack.size());
    }

    @Test
    public void testSizeOneHundredPushed() {
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }
        assertEquals(100, stack.size());
    }

    @Test
    public void testSizeAfterPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(21);
        stack.push(76);
        stack.pop();
        assertEquals(1, stack.size());
    }

    @Test
    public void testEmptyEmpty() {
        assertTrue(new Stack<>().empty());
    }

    @Test
    public void testEmptyAfterPush() {
        Stack<String> stack = new Stack<>();
        stack.push("r");
        assertFalse(stack.empty());
    }

    @Test
    public void testEmptyAfterPop() {
        Stack<String> stack = new Stack<>();
        stack.push("r");
        stack.pop();
        assertTrue(stack.empty());
    }
}