package org.project.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/** A value that represents either a success or a failure, including an associated value in each case. */
public final class Result<S, E> {
    private final S success;
    private final E error;
    private final boolean isSuccess;

    private Result(S success, E error, boolean isSuccess) {
        this.success = success;
        this.error = error;
        this.isSuccess = isSuccess;
    }

    /** A success, storing a Success value. */
    @Contract(value = "_ -> new", pure = true)
    public static <S, E> @NotNull Result<S, E>success(S success) {
        return new Result<>(success, null, true);
    }

    /** A failure, storing a Failure value. */
    @Contract(value = "_ -> new", pure = true)
    public static <S, E> @NotNull Result<S, E>failure(E failure) {
        return new Result<>(null, failure, false);
    }

    /** Check if the result is a success. */
    public boolean isSuccess() { return this.isSuccess; }

    /** Get the success value.
     * @throws IllegalStateException if this method is called for an error case */
    public S getSuccess() {
        if (!isSuccess) {
            throw new IllegalStateException("Result is not a success");
        }
        return success;
    }

    /** Get the failure value.
     * @throws IllegalStateException if the method is called an error case. */
    public E getError() {
        if (isSuccess) {
            throw new IllegalStateException("Result is not a error");
        }
        return error;
    }
}
