package org.projet.model.gameengine;

import org.projet.model.resource.Resources;

// Design Pattern Observer : Subscriber
/**
 * An entity consuming resources from the stock of a ResourcesManager
 */
public interface ResourcesConsumer {
    /**
     * @return the resources consumed by the entity
     */
    Resources getConsumption();

    /**
     * Remove the sources consumed by the entity from the manager stock
     * @param manager The resource manager
     */
    void removeConsumption(ResourcesManager manager);
}
