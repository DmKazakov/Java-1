package ru.spbau.mit.kazakov.Maybe;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Function;

/**
 * Generic container for storing a value or nothing.
 */
public class Maybe<T> {
    private static final Maybe NOTHING = new Maybe<>(null);

    @Nullable
    private final T value;

    /**
     * Assigns specified value to class's storing field.
     *
     * @param t specified value
     */
    private Maybe(@Nullable T t) {
        value = t;
    }

    /**
     * Creates an object for storing specified value.
     *
     * @param t specified value
     * @return new object
     */
    @NotNull
    public static <T> Maybe<T> just(T t) {
        return new Maybe<>(t);
    }

    /**
     * Creates an object for strong nothing.
     *
     * @return new object
     */
    @NotNull
    public static <T> Maybe<T> nothing() {
        return NOTHING;
    }

    /**
     * Returns stored value or throws exception if there is no value.
     *
     * @return stored value
     * @throws GetNothingException if there is no stored value
     */
    @NotNull
    public T get() throws GetNothingException {
        if (isPresent()) {
            return value;
        } else {
            throw new GetNothingException();
        }
    }

    /**
     * Checks if there is stored value.
     *
     * @return true if there is stored value, and false otherwise
     */
    public boolean isPresent() {
        return value != null;
    }

    /**
     * Maps stored value of type T to another value of type U.
     *
     * @param mapper a function acting from T to V
     * @param <U>    image's type
     * @return Maybe object storing mapped value if there is a storing value, and Maybe object storing nothing otherwise
     */
    @NotNull
    public <U> Maybe<U> map(@NotNull Function<? super T, ? extends U> mapper) {
        if (!isPresent()) {
            return nothing();
        }
        return just(mapper.apply(value));
    }
}
