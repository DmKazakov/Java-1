package ru.spbau.mit.kazakov.HashTable;

/**
 * HashTable
 * Key: String
 * Value: String
 * Collision resolution: Separate chaining with linked lists
 */
public class HashTable {
    private int capacity = 16;
    private int size = 0;
    private LinkedList[] hashtable = new LinkedList[capacity];

    /**
     * Initialises all linked lists.
     */
    public HashTable() {
        for (int i = 0; i < capacity; i++) {
            hashtable[i] = new LinkedList();
        }
    }

    /**
     * Returns number of keys in hashtable.
     */
    public int size() {
        return size;
    }

    /**
     * Tests if the specified string is a key in this hashtable.
     */
    public boolean contains(String key) {
        int hash = Math.floorMod(key.hashCode(), capacity);
        return hashtable[hash].contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this map contains no mapping for the key.
     */
    public String get(String key) {
        int hash = Math.floorMod(key.hashCode(), capacity);
        return hashtable[hash].get(key);
    }

    /**
     * Maps the specified key to the specified value in this hashtable.
     * Returns previous value to which the specified key was mapped, or null if this map contained no mapping for the key.
     */
    public String put(String key, String value) {
        int hash = Math.floorMod(key.hashCode(), capacity);
        String returnValue = hashtable[hash].put(key, value);

        if (returnValue == null) {
            size++;
            if (size == capacity) {
                rebuild();
            }
        }

        return returnValue;
    }

    /**
     * Removes the specified key and its corresponding value from this hashtable.
     * Returns removed value, or null if this map contained no mapping for the key.
     */
    public String remove(String key) {
        int hash = Math.floorMod(key.hashCode(), capacity);
        String returnValue = hashtable[hash].remove(key);
        if (returnValue != null) {
            size--;
        }
        return returnValue;
    }

    /**
     * Clears this hashtable so that it contains no keys.
     */
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            hashtable[i] = new LinkedList();
        }
        size = 0;
    }

    /**
     * Increases capacity by copying all entries in twice and rehashes all elements.
     */
    private void rebuild() {
        capacity *= 2;
        size = 0;
        LinkedList[] oldHashtable = hashtable;

        hashtable = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            hashtable[i] = new LinkedList();
        }

        for (int i = 0; i < capacity / 2; i++) {
            while (!oldHashtable[i].empty()) {
                put(oldHashtable[i].firstElementKey(), oldHashtable[i].firstElementValue());
                oldHashtable[i].remove(oldHashtable[i].firstElementKey());
            }
        }
    }

}
