package org.projet.model.gameengine;

import org.projet.model.resource.Resources;

// Design Pattern Observer : Subscriber
public interface ResourcesConsumer {
    Resources getConsumption();
    void removeConsumption(Player player);
}
