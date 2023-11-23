package org.project.model.resource;

/** The representation of a citizen of the game.
 *
 * @implNote A citizen can be a resident just like a worker. */
public class Citizen extends Resource {
    /** Initialize a new Citizen instance with the given quantity. */
    public Citizen(int quantity) {
        addQuantity(quantity);
    }
}
