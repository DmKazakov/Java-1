package ru.spbau.mit.kazakov.Function;


import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Class providing methods for working with collections using functions.
 */
public class Collections {
    /**
     * Applies specified function to specified container's elements and adds mapped elements in new list.
     *
     * @param function specified function
     * @param elements specified container
     * @param <T>      type of elements in specified container
     * @param <U>      type of elements in result list
     * @return result list
     */
    static public <T, U> List<U> map(@NotNull final Function1<? super T, ? extends U> function, @NotNull final Iterable<T> elements) {
        List<U> resultList = new ArrayList<>();

        for (T element : elements) {
            resultList.add(function.apply(element));
        }

        return resultList;
    }

    /**
     * Creates new list composed of elements from specified container that satisfy specified predicate.
     *
     * @param predicate specified predicate
     * @param elements  specified container
     * @param <T>       type of elements in specified container
     * @return result list
     */
    static public <T> List<T> filter(@NotNull final Predicate<? super T> predicate, @NotNull final Iterable<T> elements) {
        List<T> resultList = new ArrayList<>();

        for (T element : elements) {
            if (predicate.apply(element)) {
                resultList.add(element);
            }
        }

        return resultList;
    }

    /**
     * Creates new list by adding elements from specified container while they satisfy specified predicate.
     *
     * @param predicate specified predicate
     * @param elements  specified container
     * @param <T>       type of elements in specified container
     * @return result list
     */
    static public <T> List<T> takeWhile(@NotNull final Predicate<? super T> predicate, @NotNull final Iterable<T> elements) {
        List<T> resultList = new ArrayList<>();

        for (T element : elements) {
            if (!predicate.apply(element)) {
                break;
            }
            resultList.add(element);
        }

        return resultList;
    }

    /**
     * Creates new list by adding elements from specified container unless they satisfy specified predicate.
     *
     * @param predicate specified predicate
     * @param elements  specified container
     * @param <T>       type of elements in specified container
     * @return result list
     */
    static public <T> List<T> takeUnless(@NotNull final Predicate<? super T> predicate, @NotNull final Iterable<T> elements) {
        return takeWhile(predicate.not(), elements);
    }

    /**
     * Left-associative fold of specified collection. Reduces the list using specified function from left to right.
     *
     * @param function   specified function
     * @param startValue first value for function's first element
     * @param elements   specified collection
     * @param <T>        type of elements in specified collection
     * @param <U>        specified function's codomain
     * @return result value
     */
    static public <T, U> U foldl(@NotNull final Function2<? super U, ? super T, ? extends U> function,
                                 U startValue, @NotNull final Collection<T> elements) {
        U resultValue = startValue;

        for (T element : elements) {
            resultValue = function.apply(resultValue, element);
        }

        return resultValue;
    }

    /**
     * Right-associative fold of specified collection. Reduces the list using specified function from right to left.
     *
     * @param function   specified function
     * @param startValue first value for function's second element
     * @param elements   specified collection
     * @param <T>        type of elements in specified collection
     * @param <U>        specified function's codomain
     * @return result value
     */
    static public <T, U> U foldr(@NotNull final Function2<? super T, ? super U, ? extends U> function,
                                 U startValue, @NotNull final Collection<T> elements) {
        U resultValue = startValue;

        for (T element : Lists.reverse(new ArrayList<>(elements))) {
            resultValue = function.apply(element, resultValue);
        }

        return resultValue;
    }
}
