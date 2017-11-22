package ru.spabu.mit.kazakov.TreeSet;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A generic set implemented using binary search tree.
 *
 * @param <E> type of stored value
 */
public class MyTreeSet<E> extends AbstractSet<E> implements HisTreeSet<E> {
    private Comparator<? super E> comparator;
    private int size = 0;
    private Node root = null;
    private boolean reverse = false;

    /**
     * Makes new myTreeSet containing values from specified myTreeSet, but with reversed order view of the values.
     *
     * @param originalSet specified myTreeSet
     */
    private MyTreeSet(@NotNull MyTreeSet<E> originalSet) {
        root = originalSet.root;
        size = originalSet.size;
        comparator = originalSet.comparator;
        reverse = !originalSet.reverse;
    }

    /**
     * Returns Node storing specified value.
     *
     * @param value specified value
     * @return found Node if there is such Node, and null otherwise.
     */
    @Nullable
    private Node find(@NotNull E value) {
        Node current = root;

        while (current != null) {
            int compareResult = comparator.compare(current.value, value);
            if (compareResult == 0) {
                return current;
            } else if (compareResult > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    /**
     * Returns lowest value.
     *
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    private E normalOrderFirst() {
        Node current = root;
        if (current == null) {
            return null;
        }

        while (current.left != null) {
            current = current.left;
        }

        return current.value;
    }

    /**
     * Returns highest value.
     *
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    private E normalOrderLast() {
        Node current = root;
        if (current == null) {
            return null;
        }

        while (current.right != null) {
            current = current.right;
        }

        return current.value;
    }

    /**
     * Returns the greatest value in this set strictly less than the specified value.
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    private E normalOrderLower(@NotNull E value) {
        Node current = root;
        if (current == null) {
            return null;
        }
        E answer = null;

        while (current != null) {
            int compareResult = comparator.compare(current.value, value);
            if (compareResult >= 0) {
                current = current.left;
            } else {
                answer = current.value;
                current = current.right;
            }
        }

        return answer;
    }

    /**
     * Returns the greatest value in this set less than or equal to the specified value,
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    private E normalOrderFloor(@NotNull E value) {
        Node found = find(value);
        return found != null ? found.value : normalOrderLower(value);
    }

    /**
     * Returns the least value in this set greater than or equal to the specified value.
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    private E normalOrderCeiling(@NotNull E value) {
        Node found = find(value);
        return found != null ? found.value : normalOrderHigher(value);
    }

    /**
     * Returns the least value in this set strictly greater than the given value,
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    private E normalOrderHigher(@NotNull E value) {
        Node current = root;
        E answer = null;

        while (current != null) {
            int compareResult = comparator.compare(current.value, value);
            if (compareResult <= 0) {
                current = current.right;
            } else {
                answer = current.value;
                current = current.left;
            }
        }

        return answer;
    }

    /**
     * Initializes comparator for values ordering by specified comparator.
     *
     * @param comparator specified comparator
     */
    public MyTreeSet(@NotNull Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Initializes comparator for values ordering using value's comparability,
     * therefore generic type must implements Comparable interface.
     */
    public MyTreeSet() {
        comparator = (o1, o2) -> ((Comparable<E>) o1).compareTo(o2);
    }

    /**
     * Checks if there is a value equals to specified value.
     *
     * @param value to check
     * @return true if there is such value, and false otherwise
     */
    @Override
    public boolean contains(@NotNull Object value) {
        return find((E) value) != null;
    }

    /**
     * Adds new Node storing specified value to the tree.
     *
     * @param value to add
     * @return false if there was specified value, and true otherwise
     */
    @Override
    public boolean add(@NotNull E value) {
        if (contains(value)) {
            return false;
        }
        if (root == null) {
            root = new Node(value);
            size = 1;
            return true;
        }

        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            int compareResult = comparator.compare(current.value, value);
            if (compareResult > 0) {
                current = current.left;
            } else if (compareResult < 0) {
                current = current.right;
            }

        }

        int compareResult = comparator.compare(parent.value, value);
        if (compareResult < 0) {
            parent.right = new Node(value);
        } else if (compareResult > 0) {
            parent.left = new Node(value);
        }

        size++;
        return true;
    }

    /**
     * Removes Node storing specified value from tree.
     *
     * @param value to remove
     * @return true if there was specified value, and false otherwise
     */
    @Override
    public boolean remove(@NotNull Object value) {
        if (!contains(value)) {
            return false;
        }
        size--;

        Node current = root;
        Node parent = null;
        int compareResult;
        while ((compareResult = comparator.compare(current.value, (E) value)) != 0) {
            parent = current;
            if (compareResult > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        boolean isLeftChild = parent != null && parent.left == current;
        if (parent != null) {
            if (current.left == null) {
                if (isLeftChild) {
                    parent.left = current.right;
                    return true;
                } else {
                    parent.right = current.right;
                    return true;
                }
            } else if (current.right == null) {
                if (isLeftChild) {
                    parent.left = current.left;
                    return true;
                } else {
                    parent.right = current.left;
                    return true;
                }
            }
        } else {
            if (current.left == null) {
                root = current.right;
                return true;
            } else if (current.right == null) {
                root = current.left;
                return true;
            }
        }

        E nextValue = normalOrderHigher(current.value);
        remove(nextValue);
        current.value = nextValue;

        return true;
    }

    /**
     * Returns an iterator over the values in this set in descending order.
     */
    @NotNull
    public Iterator<E> descendingIterator() {
        return descendingSet().iterator();
    }

    /**
     * Returns a reverse order view of the values contained in this set.
     */
    public MyTreeSet<E> descendingSet() {
        return new MyTreeSet<>(this);
    }

    /**
     * Returns lowest value if order isn't reversed, and highest value otherwise.
     *
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    public E first() {
        if (reverse) {
            return normalOrderLast();
        } else {
            return normalOrderFirst();
        }
    }

    /**
     * Returns highest value if order isn't reversed, and lowest value otherwise.
     *
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    public E last() {
        if (reverse) {
            return normalOrderFirst();
        } else {
            return normalOrderLast();
        }
    }

    /**
     * Returns the greatest value in this set strictly less than the specified value if order isn't reversed, and
     * the least value in this set strictly greater than the specified value otherwise.
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    public E lower(@NotNull E value) {
        if (reverse) {
            return normalOrderHigher(value);
        } else {
            return normalOrderLower(value);
        }
    }

    /**
     * Returns the greatest value in this set less than or equal to the specified value if order isn't reversed, and
     * the least value in this set greater than or equal to the specified value otherwise.
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    public E floor(@NotNull E value) {
        if (reverse) {
            return normalOrderCeiling(value);
        } else {
            return normalOrderFloor(value);
        }
    }

    /**
     * Returns the least value in this set greater than or equal to the specified value if order isn't reversed, and
     * the greatest value in this set less than or equal to the specified value otherwise.
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    public E ceiling(@NotNull E value) {
        if (reverse) {
            return normalOrderLower(value);
        } else {
            return normalOrderCeiling(value);
        }
    }

    /**
     * Returns the least value in this set strictly greater than the given value if order isn't reversed, and
     * the greatest value in this set strictly less than the specified value otherwise.
     *
     * @param value specified value
     * @return found value if there is such value, and null otherwise
     */
    @Nullable
    public E higher(@NotNull E value) {
        if (reverse) {
            return normalOrderLower(value);
        } else {
            return normalOrderHigher(value);
        }
    }

    /**
     * Returns an iterator over the values in this set.
     */
    @NotNull
    public Iterator<E> iterator() {
        return new MyTreeSetIterator();
    }

    /**
     * Returns number of stored values in the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Node for binary search tree. Stores left, right children and value.
     */
    private class Node {
        private Node left = null;
        private Node right = null;
        private E value;

        /**
         * Initializes stored value with specified value.
         *
         * @param value specified value
         */
        private Node(@NotNull E value) {
            this.value = value;
        }

    }

    /**
     * Iterator for ru.spabu.mit.kazakov.TreeSet.MyTreeSet.
     */
    private class MyTreeSetIterator implements Iterator<E> {
        private E next = first();

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (next == null) {
                throw new NoSuchElementException();
            }

            E prev = next;
            next = higher(next);
            return prev;
        }
    }
}
