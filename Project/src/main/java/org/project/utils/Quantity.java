package org.project.utils;

import java.util.Objects;

public final class Quantity {
    private int value;

    public Quantity() { this(0); }
    public Quantity(int value) { this.value = value; }

    public int get() { return this.value; }
    public void set(int value) { this.value = value; }

    public void add() { this.update(1); }
    public void add(int value) { this.update(value); }

    public void remove() { this.update(-1); }
    public void remove(int value) { this.update(-value); }

    private void update(int value) { this.value += value; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity quantity = (Quantity) o;
        return value == quantity.value;
    }

    @Override public int hashCode() { return Objects.hash(value); }

    @Override public String toString() { return String.valueOf(value); }
}
