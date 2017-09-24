import org.junit.jupiter.api.Test;
import ru.spbau.mit.kazakov.HashTable.LinkedList;

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

    @Test
    void testFirstElementKeyOneElement() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertEquals("some key", list.firstElementKey());
    }

    @Test
    void testFirstElementKeyAfterRemove() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        list.put("another key", "another value");
        list.remove("some key");
        assertEquals("another key", list.firstElementKey());
    }

    @Test
    void testFirstElementKeyOneHundredElements() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            list.put(Integer.toString(i), Integer.toString(i));
        }
        assertEquals("0", list.firstElementKey());
    }

    @Test
    void testFirstElementValueOneElement() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertEquals("some value", list.firstElementValue());
    }

    @Test
    void testFirstElementValueAfterRemove() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        list.put("another key", "another value");
        list.remove("some key");
        assertEquals("another value", list.firstElementValue());
    }

    @Test
    void testFirstElementValueOneHundredElements() {
        LinkedList list = new LinkedList();
        for (int i = 0; i < 100; i++) {
            list.put(Integer.toString(i), Integer.toString(i));
        }
        assertEquals("0", list.firstElementValue());
    }

    @Test
    void testEmptyTrue() {
        LinkedList list = new LinkedList();
        assertTrue(list.empty());
    }

    @Test
    void testEmptyFalse() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        assertFalse(list.empty());
    }

    @Test
    void testEmptyTrueAfterRemove() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        list.remove("some key");
        assertTrue(list.empty());
    }

    @Test
    void testEmptyFalseAfterRemove() {
        LinkedList list = new LinkedList();
        list.put("some key", "some value");
        list.put("another key", "another value");
        list.remove("some key");
        assertFalse(list.empty());
    }
}