package ru.spbau.mit.kazakov.Function;


import org.jetbrains.annotations.NotNull;

/**
 * The interface for representing a function with one argument.
 *
 * @param <T> domain's type
 * @param <U> codomain's type
 */
@FunctionalInterface
public interface Function1<T, U> {
    /**
     * Applies function to specified argument.
     *
     * @param argument for applying to
     * @return mapped value
     */
    U apply(T argument);

    /**
     * Creates composition of functions.
     *
     * @param function for composition
     * @param <V>      specified function's codomain's type
     * @return result of function composition
     */
    default <V> Function1<T, V> compose(@NotNull final Function1<? super U, ? extends V> function) {
        return argument -> function.apply(apply(argument));
    }
}
