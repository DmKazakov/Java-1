package ru.spbau.mit.kazakov.HashTable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {
    @Test
    void testConstructor() {
        LinkedList list = new LinkedList();
    }

    @Test
    void testPutNew() {
        LinkedList list = new LinkedList();
        assertNull(list.put("some key", "some value"));
        assertEquals("some value", list.get("some key"));
    }

    @Test
    void testPutReassign() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertEquals("some value", list.put("some key", "other value"));
        assertEquals("other value", list.get("some key"));
    }

    @Test
    void testPutOneHundredElements() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            list.put(Integer.toString(i), Integer.toString(i));
        }
    }

    @Test
    void testContainsNotFound() {
        LinkedList list = new LinkedList();
        assertFalse(list.contains("some key"));
    }

    @Test
    void testContainsFound() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertTrue(list.contains("some key"));
    }

    @Test
    void testGetExisting() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertEquals("some value", list.get("some key"));
    }

    @Test
    void testGetNotExisting() {
        LinkedList list = new LinkedList();
        assertNull(list.get("some key"));
    }


    @Test
    void testRemoveEmpty() {
        LinkedList list = new LinkedList();
        assertNull(list.remove("some key"));
    }

    @Test
    void testRemoveNotExisting() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertNull(list.remove("another key"));
    }

    @Test
    void testRemoveExisting() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertEquals("some value", list.remove("some key"));
    }
}