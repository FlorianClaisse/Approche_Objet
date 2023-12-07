package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.resource.Resources;

public interface ResourcesManager {
    void addToStock(Resources resources);
    void removeFromStock(Resources resources);
    boolean haveEnoughResources(Resources resources);


    // Design Pattern Observer : Publisher
    void collectProduction();
    void provideConsumption();
    void subscribe(ResourcesProvider provider);
    void subscribe(ResourcesConsumer consumer);
    void subscribe(Building building);
}
