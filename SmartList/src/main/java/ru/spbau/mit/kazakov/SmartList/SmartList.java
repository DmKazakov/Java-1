package ru.spbau.mit.kazakov.SmartList;

import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Smart list storage. Store one element using one reference, 2-5 element using an array, and more elements using ArrayList.
 * @param <E> generic type
 */
public class SmartList<E> extends AbstractList<E> implements List<E> {
    private int size;
    private Object data;

    /**
     * Creates new empty list.
     */
    public SmartList() {
        size = 0;
        data = null;
    }

    /**
     * Creates new list containing elements from specified collection.
     *
     * @param collection specified collection
     */
    public SmartList(@NotNull Collection<? extends E> collection) {
        for (E elem : collection) {
            add(elem);
        }
    }

    /**
     * Returns elements by specified index.
     *
     * @param i specified index
     */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int i) {
        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 1) {
            return (E) data;
        } else if (size <= 5) {
            return (E) ((Object[]) data)[i];
        } else {
            return (E) ((ArrayList<Object>) data).get(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Removes element by specified index
     *
     * @param i specified index
     * @return removed element
     * @throws IndexOutOfBoundsException if there is no element with specified index
     */
    @Override
    @SuppressWarnings("unchecked")
    public E remove(int i) throws IndexOutOfBoundsException {
        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        E returnValue;
        if (size == 1) {
            returnValue = (E) data;
            data = null;
        } else if (size == 2) {
            returnValue = (E) ((Object[]) data)[i];
            data = i == 0 ? ((Object[]) data)[1] : ((Object[]) data)[0];
        } else if (size <= 5) {
            returnValue = (E) ((Object[]) data)[i];
        } else if (size == 6) {
            returnValue = (E) ((ArrayList<Object>) data).get(i);
            Object[] array = new Object[5];
            int j = 0;
            while (j != 4) {
                int cur = 0;
                if (j != i) {
                    array[cur] = ((ArrayList<Object>) data).get(j);
                    cur++;
                }
                j++;
            }
            data = array;
        } else {
            returnValue = (E) ((ArrayList<Object>) data).remove(i);
        }

        size--;
        return returnValue;
    }

    /**
     * Adds specified element to list.
     *
     * @param elem specified element
     * @return true
     */
    @Override
    public boolean add(@NotNull E elem) {
        size++;

        if (size == 1) {
            data = elem;
        } else if (size == 2) {
            Object[] array = new Object[5];
            array[0] = this.data;
            array[1] = elem;
            data = array;
        } else if (size <= 5) {
            ((Object[]) data)[size - 1] = elem;
        } else if (size == 6) {
            ArrayList<Object> list = new ArrayList<>(Arrays.asList((Object[]) data));
            list.add(elem);
            data = list;
        } else {
            //noinspection unchecked
            ((ArrayList<Object>) data).add(elem);
        }

        return true;
    }

    /**
     * Checks if there is specified element in list
     *
     * @param elem specified element
     * @return true if there is such element, and false otherwise
     */
    @Override
    public boolean contains(@NotNull Object elem) {
        if (size == 1) {
            return data.equals(elem);
        } else if (size <= 5) {
            boolean result = false;
            for (Object i : (Object[]) data) {
                result |= i.equals(elem);
            }
            return result;
        } else {
            boolean result = false;
            //noinspection unchecked
            for (Object i : (ArrayList<Object>) data) {
                result |= i.equals(elem);
            }
            return result;
        }
    }

    /**
     * Replaces the element at the specified index with the specified element.
     *
     * @param i    specified position
     * @param elem specified element
     * @return the element previously at the specified index
     */
    @Override
    @SuppressWarnings("unchecked")
    public E set(int i, @NotNull E elem) {
        if (i >= size) {
            throw new IndexOutOfBoundsException();
        }

        E returnValue;
        if (size == 1) {
            returnValue = (E) data;
            data = elem;
        } else if (size <= 5) {
            returnValue = (E) ((Object[]) data)[i];
            ((Object[]) data)[i] = elem;
        } else {
            returnValue = (E) ((ArrayList<Object>) data).get(i);
            ((ArrayList<Object>) data).set(i, elem);
        }

        return returnValue;
    }

    @Override
    @NotNull
    @SuppressWarnings("unchecked")
    public Iterator<E> iterator() {
        if(size == 0) {
            return EmptyIterator.emptyIterator();
        } else if(size == 1) {
            return new SingletonIterator<>((E) data);
        } else if(size <= 5){
            return (Iterator<E>) Arrays.stream((Object[]) data).limit(size).iterator();
        } else {
            return (Iterator<E>) ((ArrayList<Object>) data).iterator();
        }
    }
}
