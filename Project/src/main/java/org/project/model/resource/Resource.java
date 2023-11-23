package org.project.model.resource;

/** A primary resource of the game. */
public abstract class Resource {
    /** The quantity of this resource. */
    private int quantity;

    /** Returns the quantity of the resource. */
    public int getQuantity() { return quantity; }

    /** Add one to the resource quantity. */
    public void addQuantity() { quantity++; }

    /** Add the value of value to the quantity of the resource.
     * @param value The value to add.
     * @throws IllegalArgumentException If value parameter is less than 0. */
    public void addQuantity(int value) {
        if (value < 0) throw new IllegalArgumentException("You just tried to add a negative value (use remove method).");
        quantity += value;
    }
    /** Remove one to the resource quantity. */
    public void removeQuantity() { quantity--; }
    /** Remove the value of value to the quantity of the resource.
     * @param value The value to remove.
     * @throws IllegalArgumentException If value parameter is less than 0. */
    public void removeQuantity(int value) {
        if (value < 0) throw new IllegalArgumentException("You just tried to remove a negative value (use add method).");
        quantity -= value;
    }
}
