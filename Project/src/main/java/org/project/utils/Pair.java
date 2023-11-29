package org.project.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Pair<K, V> {
    private @NotNull K first;
    private @NotNull V second;

    public Pair(@NotNull K first, @NotNull V second) {
        this.first = first;
        this.second = second;
    }

    public @NotNull K getFirst() { return this.first; }
    public void setFirst(@NotNull K first) { this.first = first; }

    public @NotNull V getSecond() { return this.second; }
    public void setSecond(@NotNull V second) { this.second = second; }

    @Override public String toString() { return "Pair(first=" + first + ", second=" + second + ')'; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override public int hashCode() { return Objects.hash(first, second); }
}
