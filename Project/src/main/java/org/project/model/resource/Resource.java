package org.project.model.resource;


public abstract class Resource {
    private int quantity;

    public final int getQuantity() { return this.quantity; }

    public final void addQuantity() { this.quantity++; }
    public final void addQuantity(int value) {
        if (value < 0) throw new IllegalArgumentException("You just tried to add a negative value (use remove method).");
        this.quantity += value;
    }
    public final void removeQuantity() { quantity--; }
    public final void removeQuantity(int value) {
        if (value < 0) throw new IllegalArgumentException("You just tried to remove a negative value (use add method).");
        this.quantity -= value;
    }
}
