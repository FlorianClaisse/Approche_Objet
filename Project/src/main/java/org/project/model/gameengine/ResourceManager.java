package org.project.model.gameengine;

import org.project.model.resource.Resources;

public interface ResourceManager {
    void removeFromStock(Resources resources);
    void addToStock(Resources resources);
    boolean haveEnoughResources(Resources resources);
}
