package org.projet.model.resource;

import org.projet.utils.Quantity;

import java.util.HashMap;

import static org.projet.model.resource.ResourceFactory.gold;

/**
 * The class used to associate resources with a quantity
 */
public final class Resources extends HashMap<Resource, Quantity> {

    public Resources(Resources resources) {
        super(resources);
    }

    public Resources() {
        super();
    }

    /**
     * Initialize the HashMap with all the resources and their quantities at 0
     */
    public void initWithAllResources() {
        this.put(gold(), new Quantity());

        for (Material.Type type: Material.Type.values()) {
            this.put(new Material(type), new Quantity());
        }
    }
}
