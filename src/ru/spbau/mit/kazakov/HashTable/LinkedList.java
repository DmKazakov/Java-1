package ru.spbau.mit.kazakov.HashTable;

/**
 * Singly linked list for hashtable.
 */
public class LinkedList {
    private Node head = new Node(null, null);

    /**
     * Adds new node to list, increments size.
     */
    private void add(Node newNode) {
        Node current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    /**
     * Find node with specified key in list.
     * Returns null if there is no such node and found node otherwise.
     */
    private Node find(String key) {
        Node current = head;

        while (current.next != null) {
            current = current.next;
            if (current.key.equals(key)) {
                return current;
            }
        }

        return null;
    }

    /**
     * Returns first element's key.
     */
    public String firstElementKey() {
        return head.next.key;
    }

    /**
     * Returns first element's value.
     */
    public String firstElementValue() {
        return head.next.value;
    }

    /**
     * Returns false if there is no elements in list and true otherwise,
     */
    public boolean empty() {
        return head.next == null;
    }

    /**
     * Returns true if list contains node with specified key and false otherwise.
     */
    public boolean contains(String key) {
        return find(key) != null;
    }

    /**
     * Returns the node's value with specified key, or null there is no such node in list.
     */
    public String get(String key) {
        Node foundNode = find(key);

        if (foundNode != null) {
            return foundNode.value;
        } else {
            return null;
        }
    }

    /**
     * Assigns specified value to node with specified key.
     * Returns previous node's value or null if there was no node with specified key.
     */
    public String put(String key, String value) {
        Node foundNode = find(key);

        if (foundNode != null) {
            String previousValue = foundNode.value;
            foundNode.value = value;
            return previousValue;
        } else {
            add(new Node(key, value));
            return null;
        }
    }

    /**
     * Removes node with specified key from list.
     * Returns removed node's value or null if there was no node with specified key.
     */
    public String remove(String key) {
        Node current = head;

        while (current.next != null) {
            if (current.next.key.equals(key)) {
                String returnValue = current.next.value;
                current.next = current.next.next;
                return returnValue;
            }
            current = current.next;
        }

        return null;
    }

    /**
     * Node for linked list.
     * Store hashtable's key and value
     */
    class Node {
        private Node next;
        private String key, value;

        /**
         * Node constructor
         *
         * @param key   key in hash table
         * @param value value in hash table
         */
        Node(String key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}
