import org.junit.Test;
import ru.spabu.mit.kazakov.TreeSet.MyTreeSet;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.*;

public class MyTreeSetTest {
    @Test
    public void testConstructor() {
        new MyTreeSet<String>();
    }

    @Test
    public void testConstructorWithComparator() {
        new MyTreeSet<Character>((o1, o2) -> -1 * o1.compareTo(o2));
    }

    @Test
    public void testAddReturnTrue() {
        MyTreeSet<Long> mySet = new MyTreeSet<>();
        assertTrue(mySet.add(1L));
    }

    @Test
    public void testAddReturnFalse() {
        MyTreeSet<Long> mySet = new MyTreeSet<>();
        mySet.add(1L);

        assertFalse(mySet.add(1L));
    }

    @Test
    public void testAdd() {
        MyTreeSet<Long> mySet = new MyTreeSet<>();
        mySet.add(17L);
        mySet.add(13L);
        mySet.add(2L);

        assertArrayEquals(new Long[]{2L, 13L, 17L}, mySet.toArray());
    }

    @Test
    public void testAddTheSame() {
        MyTreeSet<Long> mySet = new MyTreeSet<>();
        mySet.add(17L);
        mySet.add(13L);
        mySet.add(2L);
        mySet.add(13L);

        assertArrayEquals(new Long[]{2L, 13L, 17L}, mySet.toArray());
    }

    @Test
    public void testAddRandom() {
        MyTreeSet<Integer> mySet = new MyTreeSet<>();
        TreeSet<Integer> random = new TreeSet<>();

        for (int i = 0; i < 100; i++) {
            Integer valueToAdd = ThreadLocalRandom.current().nextInt(0, 30);
            random.add(valueToAdd);
            mySet.add(valueToAdd);
        }

        assertArrayEquals(random.toArray(), mySet.toArray());
    }

    @Test
    public void testRemoveReturnTrue() {
        MyTreeSet<Character> mySet = new MyTreeSet<>((o1, o2) -> -1 * o1.compareTo(o2));
        mySet.add((char) 1);

        assertTrue(mySet.remove((char) 1));
    }

    @Test
    public void testRemoveReturnFalse() {
        MyTreeSet<Character> mySet = new MyTreeSet<>((o1, o2) -> -1 * o1.compareTo(o2));
        mySet.add((char) 1);

        assertFalse(mySet.remove((char) 2));
    }

    @Test
    public void testRemove() {
        MyTreeSet<Character> mySet = new MyTreeSet<>();
        mySet.add('a');
        mySet.add('z');
        mySet.add('v');
        mySet.add('t');
        mySet.remove('t');
        mySet.remove('a');

        assertArrayEquals(new Character[]{'v', 'z'}, mySet.toArray());
    }

    @Test
    public void testRemoveTheSame() {
        MyTreeSet<Character> mySet = new MyTreeSet<>();
        mySet.add('a');
        mySet.add('z');
        mySet.add('v');
        mySet.add('t');
        mySet.remove('t');
        mySet.remove('a');
        mySet.remove('t');

        assertArrayEquals(new Character[]{'v', 'z'}, mySet.toArray());
    }

    @Test
    public void testSizeEmpty() {
        assertEquals(0, new MyTreeSet<Integer>().size());
    }

    @Test
    public void testSizeAdd() {
        MyTreeSet<Integer> mySet = new MyTreeSet<>();
        mySet.add(5);
        mySet.add(2);

        assertEquals(2, mySet.size());
    }

    @Test
    public void testSizeAddTheSame() {
        MyTreeSet<Integer> mySet = new MyTreeSet<>();
        mySet.add(2);
        mySet.add(2);

        assertEquals(1, mySet.size());
    }

    @Test
    public void testSizeRemove() {
        MyTreeSet<Integer> mySet = new MyTreeSet<>();
        mySet.add(5);
        mySet.add(2);
        mySet.remove(2);

        assertEquals(1, mySet.size());
    }

    @Test
    public void testSizeRemoveTheSame() {
        MyTreeSet<Integer> mySet = new MyTreeSet<>();
        mySet.add(5);
        mySet.add(2);
        mySet.remove(2);
        mySet.remove(2);

        assertEquals(1, mySet.size());
    }

    @Test
    public void testSizeOneHundred() {
        MyTreeSet<Integer> mySet = new MyTreeSet<>();
        for (int i = 0; i < 100; i++) {
            mySet.add(i);
        }

        assertEquals(100, mySet.size());
    }

    @Test
    public void testContainsNotExisting() {
        MyTreeSet<Byte> mySet = new MyTreeSet<>();
        mySet.add((byte) 1);

        assertFalse(mySet.contains((byte) 2));
    }

    @Test
    public void testContainsAdd() {
        MyTreeSet<Byte> mySet = new MyTreeSet<>();
        mySet.add((byte) 55);

        assertTrue(mySet.contains((byte) 55));
    }

    @Test
    public void testContainsRemove() {
        MyTreeSet<Byte> mySet = new MyTreeSet<>();
        mySet.add((byte) 111);
        mySet.remove((byte) 111);

        assertFalse(mySet.contains((byte) 111));
    }

    @Test
    public void testFirstEmptySet() {
        assertNull(new MyTreeSet<Short>((o1, o2) -> -1 * o1.compareTo(o2)).first());
    }

    @Test
    public void testFirst() {
        MyTreeSet<Short> mySet = new MyTreeSet<>((o1, o2) -> -1 * o1.compareTo(o2));
        mySet.addAll(Arrays.asList((short) 100, (short) -2, (short) -33, (short) 4));
        assertEquals(100, mySet.first().shortValue());
    }

    @Test
    public void testFirstDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>((o1, o2) -> -1 * o1.compareTo(o2));
        mySet.addAll(Arrays.asList((short) 100, (short) -2, (short) -33, (short) 4));
        assertEquals(-33, mySet.descendingSet().first().shortValue());
    }

    @Test
    public void testLastEmptySet() {
        assertNull(new MyTreeSet<Short>((o1, o2) -> -1 * o1.compareTo(o2)).last());
    }

    @Test
    public void testLast() {
        MyTreeSet<Short> mySet = new MyTreeSet<>((o1, o2) -> -1 * o1.compareTo(o2));
        mySet.addAll(Arrays.asList((short) 100, (short) -2, (short) -33, (short) 4));
        assertEquals(-33, mySet.last().shortValue());
    }

    @Test
    public void testLastDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>((o1, o2) -> -1 * o1.compareTo(o2));
        mySet.addAll(Arrays.asList((short) 100, (short) -2, (short) -33, (short) 4));
        assertEquals(100, mySet.descendingSet().last().shortValue());
    }

    @Test
    public void testEmptyHigher() {
        assertNull(new MyTreeSet<Short>().higher((short) 15));
    }

    @Test
    public void testNotExistingHigher() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.higher((short) 15));
    }

    @Test
    public void testNotExistingHigherDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.descendingSet().higher((short) 1));
    }

    @Test
    public void testHigher() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(10, mySet.higher((short) 3).shortValue());
    }

    @Test
    public void testHigherDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(3, mySet.descendingSet().higher((short) 10).shortValue());
    }

    @Test
    public void testEmptyLower() {
        assertNull(new MyTreeSet<Short>().lower((short) 15));
    }

    @Test
    public void testNotExistingLower() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.lower((short) 1));
    }

    @Test
    public void testNotExistingLowerDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.descendingSet().lower((short) 15));
    }

    @Test
    public void testLower() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(1, mySet.lower((short) 2).shortValue());
    }

    @Test
    public void testLowerDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(10, mySet.descendingSet().lower((short) 4).shortValue());
    }

    @Test
    public void testEmptyFloor() {
        assertNull(new MyTreeSet<>().floor((short) 15));
    }

    @Test
    public void testNotExistingFloor() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.floor((short) 0));
    }

    @Test
    public void testNotExistingFloorDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.descendingSet().floor((short) 17));
    }

    @Test
    public void testFloor() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(15, mySet.floor((short) 15).shortValue());
    }

    @Test
    public void testFloorDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(10, mySet.descendingSet().lower((short) 4).shortValue());
    }

    @Test
    public void testEmptyCeiling() {
        assertNull(new MyTreeSet<>().ceiling((short) 15));
    }

    @Test
    public void testNotExistingCeiling() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.ceiling((short) 100));
    }

    @Test
    public void testNotExistingCeilingDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertNull(mySet.descendingSet().ceiling((short) -17));
    }

    @Test
    public void testCeiling() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(15, mySet.ceiling((short) 15).shortValue());
    }

    @Test
    public void testCeilingDescendingSet() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 15, (short) 10));
        assertEquals(3, mySet.descendingSet().ceiling((short) 4).shortValue());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratorThrowsNoSuchElementException() {
        Iterator<Short> iterator = new MyTreeSet<Short>().iterator();
        iterator.next();
    }

    @Test
    public void testIterator() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 2, (short) 0));
        Iterator<Short> iterator = mySet.iterator();

        for (byte i = 0; i < 4; i++) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next().byteValue());
        }

        assertFalse(iterator.hasNext());
    }

    @Test
    public void testDescendingIterator() {
        MyTreeSet<Short> mySet = new MyTreeSet<>();
        mySet.addAll(Arrays.asList((short) 1, (short) 3, (short) 2, (short) 0));
        Iterator<Short> iterator = mySet.descendingIterator();

        for (byte i = 3; i >= 0; i--) {
            assertTrue(iterator.hasNext());
            assertEquals(i, iterator.next().byteValue());
        }

        assertFalse(iterator.hasNext());
    }

}