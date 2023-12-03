package org.project.model.resource;

import org.project.utils.Quantity;

import java.util.HashMap;

import static org.project.model.resource.ResourceFactory.gold;

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
