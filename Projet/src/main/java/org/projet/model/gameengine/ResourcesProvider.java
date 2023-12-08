package org.projet.model.gameengine;

import org.projet.model.resource.Resources;

// Design Pattern Observer : Subscriber
/**
 * An entity providing resources for a ResourcesManager
 */
public interface ResourcesProvider {
    /**
     * @return the resources producted by the entity
     */
    Resources getProduction();

    /**
     * Add the resources producted by the entity to the manager stock
     * @param manager The resource manager
     */
    void addProduction(ResourcesManager manager);
}
