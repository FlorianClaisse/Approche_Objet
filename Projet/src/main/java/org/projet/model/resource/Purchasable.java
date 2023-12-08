package org.projet.model.resource;

/**
 * A resource that can be purchased with money (gold)
 */
public interface Purchasable extends Resource {
    /**
     * @return the price of a resource (amount of gold for one unit)
     */
    int getPrice();
}
