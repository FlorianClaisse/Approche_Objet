package org.projet.model.resource;

import org.projet.utils.Quantity;

import java.util.HashMap;

import static org.projet.model.resource.ResourceFactory.gold;

public final class Resources extends HashMap<Resource, Quantity> {

    public Resources(Resources resources) {
        super(resources);
    }

    public Resources() {
        super();
    }

    public void initWithAllResources() {
        this.put(gold(), new Quantity());

        for (Material.Type type: Material.Type.values()) {
            this.put(new Material(type), new Quantity());
        }
    }
}
