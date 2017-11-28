package ru.mit.spbau.kazakov.util;

import ru.mit.spbau.kazakov.exception.EmptyStackException;

/**
 * An implementation of stack interface for arithmetic calculator.
 *
 * @param <T> type of stored data
 */
public class Stack<T> {
    /**
     * Pushes new element to the top of stack.
     *
     * @param value to push
     * @return pushed value
     */
    public T push(T value) {
        top = new Node<>(value, top);
        size++;

        return value;
    }

    /**
     * Returns top element.
     */
    public T peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return top.value;
    }

    /**
     * Deletes top element from stack.
     *
     * @return deleted element
     */
    public T pop() {
        if (empty()) {
            throw new EmptyStackException();
        }

        T returnValue = top.value;
        top = top.prev;
        size--;

        return returnValue;
    }

    /**
     * Checks if stack is empty.
     *
     * @return true if stack is empty, and false otherwise.
     */
    public boolean empty() {
        return top == null;
    }

    public int size() {
        return size;
    }

    private Node<T> top = null;
    private int size = 0;

    /**
     * A stack entry for storing a value and previous entry.
     *
     * @param <T> type of stored data
     */
    private static class Node<T> {
        private T value;
        private Node<T> prev;

        /**
         * Initializes stored value and reference to previous Node.
         *
         * @param value to initialize with
         * @param prev  a previous Node
         */
        Node(T value, Node<T> prev) {
            this.value = value;
            this.prev = prev;
        }
    }
}
