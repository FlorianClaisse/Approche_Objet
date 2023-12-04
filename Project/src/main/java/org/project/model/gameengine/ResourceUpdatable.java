package org.project.model.gameengine;

import org.project.model.resource.Resources;

public interface ResourceUpdatable {
    void removeFromStock(Resources resources);
    void addToStock(Resources resources);
}
