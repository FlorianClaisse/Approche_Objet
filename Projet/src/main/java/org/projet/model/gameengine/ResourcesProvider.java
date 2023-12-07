package org.projet.model.gameengine;

import org.projet.model.resource.Resources;

// Design Pattern Observer : Subscriber
public interface ResourcesProvider {
    Resources getProduction();
    void addProduction(Player player);
}
