package ru.spbau.mit.kazakov.HashTable;

/**
 * HashTable
 *
 * @author Kazakov Dmitry
 */
public class HashTable {
    private int capacity = 16;
    private int size = 0;
    private LinkedList[] hashtable = new LinkedList[capacity];

    /**
     * HashTable constructor
     */
    public HashTable() {
        for (int i = 0; i < capacity; i++)
            hashtable[i] = new LinkedList();
    }

    /**
     * @return number of keys in hashtable
     */
    public int size() {
        return size;
    }

    /**
     * Tests if the specified string is a key in this hashtable.
     */
    public boolean contains(String key) {
        int hash = key.hashCode() % capacity;
        return hashtable[hash].contains(key);
    }

    /**
     * @return the value to which the specified key is mapped, or null if this map contains no mapping for the key
     */
    public String get(String key) {
        int hash = key.hashCode() % capacity;
        return hashtable[hash].get(key);
    }

    /**
     * Maps the specified key to the specified value in this hashtable
     *
     * @return previous value to which the specified key was mapped, or null if this map contained no mapping for the key
     */
    public String put(String key, String value) {
        int hash = key.hashCode() % capacity;
        String return_value = hashtable[hash].put(key, value);

        if (return_value == null) {
            size++;
            if (size == capacity)
                rebuild();
        }

        return return_value;
    }

    /**
     * Removes the specified key and its corresponding value from this hashtable.
     *
     * @return removed value, or null if this map contained no mapping for the key
     */
    public String remove(String key) {
        int hash = key.hashCode() % capacity;
        String return_value = hashtable[hash].remove(key);
        if (return_value != null)
            size--;
        return return_value;
    }

    /**
     * Clears this hashtable so that it contains no keys.
     */
    public void clear() {
        for (int i = 0; i < capacity; i++)
            hashtable[i] = new LinkedList();
        size = 0;
    }

    /**
     * Increases capacity in twice and rehashes all elements
     */
    private void rebuild() {
        capacity *= 2;
        size = 0;
        LinkedList[] old_hashtable = hashtable;

        hashtable = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++)
            hashtable[i] = new LinkedList();

        for (int i = 0; i < capacity / 2; i++) {
            Node current = old_hashtable[i].head.next;
            while (current != null) {
                put(current.key, current.value);
                current = current.next;
            }
        }
    }

}
