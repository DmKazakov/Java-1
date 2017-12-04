package ru.spbau.mit.kazakov.Function;


import org.jetbrains.annotations.NotNull;

/**
 * The interface for representing a predicate with one argument.
 *
 * @param <T> domain's type
 */
@FunctionalInterface
public interface Predicate<T> extends Function1<T, Boolean> {
    /**
     * Predicate always returning true.
     */
    Predicate ALWAYS_TRUE = argument -> true;

    /**
     * Predicate always returning false.
     */
    Predicate ALWAYS_FALSE = ALWAYS_TRUE.not();

    /**
     * Makes new predicate with two argument composed of logical OR of current and specified predicates.
     *
     * @param predicate a specified predicate
     * @param <U>       specified predicate's argument's type
     * @return new predicate with two variables
     */
    default <U> Function2<T, U, Boolean> or(@NotNull final Predicate<U> predicate) {
        return (firstArgument, secondArgument) -> Predicate.this.apply(firstArgument) || predicate.apply(secondArgument);
    }

    /**
     * Makes new predicate with two argument composed of logical AND of current and specified predicates.
     *
     * @param predicate a specified predicate
     * @param <U>       specified predicate's argument's type
     * @return new predicate with two variables
     */
    default <U> Function2<T, U, Boolean> and(@NotNull final Predicate<U> predicate) {
        return (firstArgument, secondArgument) -> Predicate.this.apply(firstArgument) && predicate.apply(secondArgument);
    }

    /**
     * Makes new predicate composed of logical NOT of current predicate.
     *
     * @return new predicate
     */
    default Predicate<T> not() {
        return argument -> !apply(argument);
    }
}
