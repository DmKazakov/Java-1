package ru.srbau.mit.kazakov;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Generic binary search tree.
 *
 * @param <T> type of stored data
 */
public class Tree<T extends Comparable<? super T>> {
    private int size = 0;
    @Nullable
    private Node root = null;

    /**
     * Node for binary search tree. Stores left, right children and data.
     */
    private class Node {
        private Node left = null;
        private Node right = null;
        private T data;

        /**
         * Initializes data with specified value.
         *
         * @param value to initialize with
         */
        private Node(@NotNull T value) {
            data = value;
        }
    }

    /**
     * Returns number of stored elements in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Checks if there is a value equals to specified value.
     *
     * @param value to check
     * @return true if there is such value, and false otherwise
     */
    public boolean contains(@NotNull T value) {
        Node current = root;

        while (current != null) {
            if (current.data.compareTo(value) > 0) {
                current = current.left;
            } else if (current.data.compareTo(value) < 0) {
                current = current.right;
            } else if (current.data.compareTo(value) == 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds new Node storing specified value to the tree.
     *
     * @param value to add
     * @return false if there was specified value, and true otherwise
     */
    public boolean add(@NotNull T value) {
        if (contains(value)) {
            return false;
        }
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (current.data.compareTo(value) > 0) {
                current = current.left;
            } else if (current.data.compareTo(value) < 0) {
                current = current.right;
            }

        }

        if (parent.data.compareTo(value) < 0) {
            parent.right = new Node(value);
        } else if (parent.data.compareTo(value) > 0) {
            parent.left = new Node(value);
        }

        size++;
        return true;
    }
}
