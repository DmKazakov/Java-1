package ru.spbau.mit.kazakov.Function;


import org.jetbrains.annotations.NotNull;

/**
 * The interface for representing a function with two arguments.
 *
 * @param <T> first argument's type
 * @param <U> second argument's type
 * @param <K> codomain's type
 */
@FunctionalInterface
public interface Function2<T, U, K> {
    /**
     * Applies function to specified argument.
     *
     * @param firstArgument  for applying to
     * @param secondArgument for applying to
     * @return mapped value
     */
    K apply(T firstArgument, U secondArgument);

    /**
     * Creates composition of functions.
     *
     * @param <R> specified function's codomain's type
     * @return result of function composition
     */
    default <R> Function2<T, U, R> compose(@NotNull final Function1<? super K, ? extends R> function) {
        return (firstArgument, secondArgument) -> function.apply(apply(firstArgument, secondArgument));
    }

    /**
     * Binds first argument to specified value.
     *
     * @param firstArgument specified value
     * @return function of one argument
     */
    default Function1<U, K> bind1(T firstArgument) {
        return secondArgument -> apply(firstArgument, secondArgument);
    }

    /**
     * Binds second argument to specified value.
     *
     * @param secondArgument specified value
     * @return function of one argument
     */
    default Function1<T, K> bind2(U secondArgument) {
        return firstArgument -> apply(firstArgument, secondArgument);
    }

    /**
     * Converts current function with two arguments into two functions with one argument.
     *
     * @return new function with one argument
     */
    default Function1<T, Function1<U, K>> curry() {
        return this::bind1;
    }
}