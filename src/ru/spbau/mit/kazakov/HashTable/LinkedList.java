package ru.spbau.mit.kazakov.HashTable;

/**
 * Linked list for hash table.
 *
 * @author Kazakov Dmitry
 */
class LinkedList {
    Node head = new Node(null, null);
    private int size = 0;

    /**
     * @return list's size
     */
    int size() {
        return size;
    }

    /**
     * Add new node to list.
     */
    void add(Node new_node) {
        Node current = head;

        while (current.next != null)
            current = current.next;

        current.next = new_node;
        size++;
    }

    /**
     * Find node with specified key in list.
     *
     * @return null if there is no such node and found node otherwise
     */
    private Node find(String key) {
        Node current = head;

        while (current.next != null) {
            current = current.next;
            if (current.key.equals(key))
                return current;
        }

        return null;
    }

    /**
     * @return true if list contains node with specified key and false otherwise
     */
    boolean contains(String key) {
        return find(key) != null;
    }

    /**
     * @return the node's value with specified key, or null there is no such node in list
     */
    String get(String key) {
        Node found_node = find(key);

        if (found_node != null)
            return found_node.value;
        else
            return null;
    }

    /**
     * Assigns specified value to node with specified key.
     *
     * @return previous node's value or null if there was no node with specified key
     */
    String put(String key, String value) {
        Node found_node = find(key);

        if (found_node != null) {
            String previous_value = found_node.value;
            found_node.value = value;
            return previous_value;
        } else {
            add(new Node(key, value));
            return null;
        }
    }

    /**
     * Removes node with specified key from list.
     *
     * @return removed node's value or null if there was no node with specified key
     */
    String remove(String key) {
        Node current = head;

        while (current.next != null) {
            if (current.next.key.equals(key)) {
                String return_value = current.next.value;
                current.next = current.next.next;
                size--;
                return return_value;
            }
            current = current.next;
        }

        return null;
    }
}
