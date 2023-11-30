package org.project.utils;

public final class Quantity {
    private int value;

    public Quantity() { this(0); }
    public Quantity(int value) { this.value = value; }

    public int get() { return this.value; }
    public void set(int value) { this.value = value; }

    public void add() { this.update(1); }
    public void add(int value) {
        if (value < 0) throw new IllegalArgumentException("You try to add a negative value please use 'remove'");
        this.update(value);
    }

    public void remove() { this.update(-1); }
    public void remove(int value) {
        if (value < 0) throw new IllegalArgumentException("You try to add a negative value please use 'add'");
        this.update(-value);
    }

    private void update(int value) { this.value += value; }
}
