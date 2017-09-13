package ru.spbau.mit.kazakov.HashTable;

/**
 * Node for linked list.
 * Store hashtable's key and value
 */
class Node {
    Node next;
    String key, value;

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
