package org.projet.model.gameengine;

import org.projet.model.resource.Resources;

public interface ResourceManager {
    void removeFromStock(Resources resources);
    void addToStock(Resources resources);
    boolean haveEnoughResources(Resources resources);
}
