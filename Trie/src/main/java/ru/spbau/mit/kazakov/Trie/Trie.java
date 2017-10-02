package ru.spbau.mit.kazakov.Trie;

import java.io.*;
import java.util.Hashtable;

/**
 * Prefix tree for storing strings.
 */
public class Trie implements Serializable {
    private Node root = new Node();

    /**
     * Checks if there is specified string in trie.
     *
     * @param element a string to check
     * @return true if there is specified string in trie, and false otherwise
     */
    public boolean contains(String element) {
        Node current = root;

        for (int i = 0; i < element.length(); i++) {
            if (!current.edges.containsKey(element.charAt(i))) {
                return false;
            }
            current = current.edges.get(element.charAt(i));
        }

        return current.isTerminal;
    }

    /**
     * Adds specified string to trie.
     *
     * @param element a string to add
     * @return true if there was no specified string in trie, and false otherwise
     */
    public boolean add(String element) {
        if (contains(element)) {
            return false;
        }

        Node current = root;
        for (int i = 0; i < element.length(); i++) {
            current.numOfContinuations++;
            if (!current.edges.containsKey(element.charAt(i))) {
                current.edges.put(element.charAt(i), new Node());
            }
            current = current.edges.get(element.charAt(i));
        }
        current.numOfContinuations++;
        current.isTerminal = true;

        return true;
    }

    /**
     * Counts how many strings in trie starts with specified string.
     */
    public int howManyStartsWithPrefix(String prefix) {
        Node current = root;

        for (int i = 0; i < prefix.length(); i++) {
            if (!current.edges.containsKey(prefix.charAt(i))) {
                return 0;
            }
            current = current.edges.get(prefix.charAt(i));
        }

        return current.numOfContinuations;
    }

    /**
     * Returns number of added strings in trie.
     */
    public int size() {
        return root.numOfContinuations;
    }

    /**
     * Removes specified string from trie.
     *
     * @param element a string to remove
     * @return true if there was specified string in trie, and false otherwise
     */
    public boolean remove(String element) {
        if (!contains(element)) {
            return false;
        }

        Node current = root;
        for (int i = 0; i < element.length(); i++) {
            current.numOfContinuations--;
            Node next = current.edges.get(element.charAt(i));
            if (next.numOfContinuations == 1) {
                current.edges.remove(element.charAt(i));
            }
            current = next;
        }
        current.numOfContinuations--;
        current.isTerminal = false;

        return true;
    }

    /**
     * Serializes current trie to given stream.
     *
     * @param out stream for writing
     */
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(this);
    }

    /**
     * Deserializes trie from given stream and replaces current trie with new one.
     *
     * @param in stream for reading
     */
    public void deserialize(InputStream in) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(in));
        root = ((Trie) objectInputStream.readObject()).root;
    }

    /**
     * Vertex in trie. Stores outgoing edges with corresponding characters.
     */
    private class Node implements Serializable {
        private Hashtable<Character, Node> edges = new Hashtable<Character, Node>();
        private boolean isTerminal = false;
        private int numOfContinuations = 0;
    }
}
