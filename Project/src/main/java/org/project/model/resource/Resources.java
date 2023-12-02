package org.project.model.resource;

import org.project.utils.Quantity;

import java.util.HashMap;

public final class Resources extends HashMap<Resource, Quantity> {

    public Resources() {
        super();
    }

    public void initWithAllResources() {
        this.put(new Gold(), new Quantity());

        for (Material.Type type: Material.Type.values()) {
            this.put(new Material(type), new Quantity());
        }
    }
}
