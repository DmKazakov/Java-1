package ru.spbau.mit.kazakov.HashTable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    @Test
    void testConstructor(){
        HashTable hashtable = new HashTable();
    }

    @Test
    void testPutNew() {
        HashTable hashtable = new HashTable();
        assertNull(hashtable.put("some key", "some value"));
        assertEquals("some value", hashtable.get("some key"));
    }

    @Test
    void testPutReassign() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        assertEquals("some value", hashtable.put("some key", "another value"));
        assertEquals("another value", hashtable.get("some key"));
    }

    @Test
    void testPutOneHundredElements() {
        HashTable hashtable = new HashTable();
        for (int i = 0; i < 100; i++) {
            hashtable.put(Integer.toString(i), Integer.toString(i));
        }
    }

    @Test
    void testContainsNotFound() {
        HashTable hashtable = new HashTable();
        assertFalse(hashtable.contains("some key"));
    }

    @Test
    void testContainsFound() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        assertTrue(hashtable.contains("some key"));
    }

    @Test
    void testGetExisting() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        assertEquals("some value", hashtable.get("some key"));
    }

    @Test
    void testGetNotExisting() {
        HashTable hashtable = new HashTable();
        assertNull(hashtable.get("some key"));
    }


    @Test
    void testRemoveEmpty() {
        HashTable hashtable = new HashTable();
        assertNull(hashtable.remove("some key"));
    }

    @Test
    void testRemoveNotExisting() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        assertNull(hashtable.remove("another key"));
    }

    @Test
    void testRemoveExisting() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        assertEquals("some value", hashtable.remove("some key"));
    }
    
    @Test
    void testSizeEmpty() {
        HashTable hashtable = new HashTable();
        assertEquals(0, hashtable.size());
    }

    @Test
    void testSizePutNew() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        assertEquals(1, hashtable.size());
    }

    @Test
    void testSizePutReassign() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        hashtable.put("some key", "another value");
        assertEquals(1, hashtable.size());
    }

    @Test
    void testSizeOneHundredElements() {
        HashTable hashtable = new HashTable();
        for (int i = 0; i < 100; i++) {
            hashtable.put(Integer.toString(i), Integer.toString(i));
        }
        assertEquals(100, hashtable.size());
    }

    @Test
    void testSizeRemoveNotExisting() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        hashtable.remove("another key");
        assertEquals(1, hashtable.size());
    }

    @Test
    void testSizeRemoveExisting() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        hashtable.remove("some key");
        assertEquals(0, hashtable.size());
    }

    @Test
    void testClearEmpty() {
        HashTable hashtable = new HashTable();
        hashtable.clear();
        assertEquals(0, hashtable.size());
    }

    @Test
    void testClearNotEmpty() {
        HashTable hashtable = new HashTable();
        hashtable.put("some key", "some value");
        hashtable.clear();
        assertEquals(0, hashtable.size());
    }

    @Test
    void testClearOneHundredElements() {
        HashTable hashtable = new HashTable();
        for (int i = 0; i < 100; i++) {
            hashtable.put(Integer.toString(i), Integer.toString(i));
        }
        hashtable.clear();
        assertEquals(0, hashtable.size());
    }

}