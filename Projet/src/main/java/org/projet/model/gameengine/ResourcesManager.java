package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.resource.Resources;

/**
 * An entity in charge of managing a stock of resources
 */
public interface ResourcesManager {
    /**
     * Add the given resources to the manager stock
     * @param resources Some resources
     */
    void addToStock(Resources resources);
    /**
     * Remove the given resources from the manager stock
     * @param resources Some resources
     */
    void removeFromStock(Resources resources);

    /**
     * @param resources Some resources
     * @return true if the manager stock has enough of the given resources
     */
    boolean haveEnoughResources(Resources resources);

    // Design Pattern Observer : Publisher
    /**
     * Notifies all of the resource providers (subscribed) to add their production to the manager stock
     */
    void collectProduction();
    /**
     * Notifies all of the resource consumers (subscribed) to remove their consumption from the manager stock
     */
    void provideConsumption();

    /**
     * Subscribes a resource provider to the manager
     * @param provider A resource provider
     */
    void subscribe(ResourcesProvider provider);
    /**
     * Subscribes a resource consumer to the manager
     * @param consumer A resource consumer
     */
    void subscribe(ResourcesConsumer consumer);
    /**
     * Subscribes a building (both as a resource provider and consumer) to the manager
     * @param building A building
     */
    void subscribe(Building building);
    /**
     * Unsubscribes a resource provider from the manager
     * @param provider A resource provider
     */
    void unsubscribe(ResourcesProvider provider);
    /**
     * Unsubscribes a resource consumer from the manager
     * @param consumer A resource consumer
     */
    void unsubscribe(ResourcesConsumer consumer);
    /**
     * Unsubscribes a building (both as resource provider and consumer) from the manager
     * @param building A building
     */
    void unsubscribe(Building building);
}
