package ru.spbau.mit.kazakov.Trie;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void testConstructor() {
        Trie trie = new Trie();
    }

    @Test
    public void testAddReturnTrue() {
        Trie trie = new Trie();
        assertTrue(trie.add("some string"));
    }

    @Test
    public void testAddReturnFalse() {
        Trie trie = new Trie();
        trie.add("some string");
        assertFalse(trie.add("some string"));
    }

    @Test
    public void testRemoveEmptyTrie() {
        Trie trie = new Trie();
        assertFalse(trie.remove("some string"));
    }

    @Test
    public void testRemoveNotExisting() {
        Trie trie = new Trie();
        trie.add("some string");
        assertFalse(trie.remove("another string"));
    }

    @Test
    public void testRemoveExisting() {
        Trie trie = new Trie();
        trie.add("some string");
        assertTrue(trie.remove("some string"));
    }

    @Test
    public void testContainsEmptyTrie() {
        Trie trie = new Trie();
        assertFalse(trie.contains("some string"));
    }

    @Test
    public void testContainsExisting() {
        Trie trie = new Trie();
        trie.add("some string");
        assertTrue(trie.contains("some string"));
    }

    @Test
    public void testContainsNotExisting() {
        Trie trie = new Trie();
        trie.add("some string");
        assertFalse(trie.contains("another string"));
    }

    @Test
    public void testContainsRemoved() {
        Trie trie = new Trie();
        trie.add("some string");
        trie.remove("some string");
        assertFalse(trie.contains("some string"));
    }

    @Test
    public void testHowManyStartsWithPrefixEmptyTrie() {
        Trie trie = new Trie();
        assertEquals(0, trie.howManyStartsWithPrefix(""));
    }

    @Test
    public void testHowManyStartsWithPrefixEmptyString() {
        Trie trie = new Trie();
        trie.add("some string");
        trie.add("another string");
        assertEquals(2, trie.howManyStartsWithPrefix(""));
    }

    @Test
    public void testHowManyStartsWithPrefixAddedString() {
        Trie trie = new Trie();
        trie.add("some string");
        trie.add("another string");
        assertEquals(1, trie.howManyStartsWithPrefix("some string"));
    }

    @Test
    public void testHowManyStartsWithPrefixTwoStrings() {
        Trie trie = new Trie();
        trie.add("super string");
        trie.add("super mega string");
        assertEquals(2, trie.howManyStartsWithPrefix("super "));
    }

    @Test
    public void testHowManyStartsWithPrefixRemovedString() {
        Trie trie = new Trie();
        trie.add("some string");
        trie.remove("some string");
        assertEquals(0, trie.howManyStartsWithPrefix("some"));
    }

    @Test
    public void testSizeEmptyTrie() {
        Trie trie = new Trie();
        assertEquals(0, trie.size());
    }

    @Test
    public void testSizeThree() {
        Trie trie = new Trie();
        trie.add("first string");
        trie.add("second string");
        trie.add("third string");
        assertEquals(3, trie.size());
    }

    @Test
    public void testSizeRemove() {
        Trie trie = new Trie();
        trie.add("first string");
        trie.add("second string");
        trie.add("third string");
        trie.remove("first string");
        assertEquals(2, trie.size());
    }

    @Test
    public void testSerializeDeserializeEmpty() throws IOException, ClassNotFoundException {
        Trie trie = new Trie();
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        trie.serialize(os);
        byte[] serialized = os.toByteArray();

        ByteArrayInputStream is = new ByteArrayInputStream(serialized);
        Trie newTrie = new Trie();
        newTrie.add("some string");
        newTrie.deserialize(is);

        assertFalse(newTrie.contains("some string"));
        assertEquals(0, newTrie.size());
    }

    @Test
    public void testSerializeDeserializeNotEmpty() throws IOException, ClassNotFoundException {
        Trie trie = new Trie();
        trie.add("some string");
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        trie.serialize(os);
        byte[] serialized = os.toByteArray();

        Trie newTrie = new Trie();
        newTrie.add("another string");
        ByteArrayInputStream is = new ByteArrayInputStream(serialized);
        newTrie.deserialize(is);

        assertTrue(newTrie.contains("some string"));
        assertFalse(newTrie.contains("another string"));
    }
}