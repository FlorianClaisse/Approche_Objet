package org.projet.model.resource;

import org.projet.model.gameengine.ResourcesConsumer;
import org.projet.model.gameengine.ResourcesManager;
import org.projet.utils.Quantity;

import static org.projet.model.resource.ResourceFactory.food;

/**
 * The city habitants, consuming resources
 */
public final class Citizens implements ResourcesConsumer {
    private final Quantity quantity = new Quantity();

    public void add(int value) {
        quantity.add(value);
    }
    public void remove(int value) {
        quantity.remove(value);
    }
    public int getNumber() {
        return quantity.get();
    }

    // ResourcesConsumer
    @Override
    public Resources getConsumption() {
        Resources resources = new Resources();
        resources.put(food(), new Quantity(quantity));
        return resources;
    }
    @Override
    public void removeConsumption(ResourcesManager player) {
        player.removeFromStock(getConsumption());
    }
}
