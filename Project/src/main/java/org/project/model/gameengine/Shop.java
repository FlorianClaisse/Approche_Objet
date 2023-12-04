package org.project.model.gameengine;

import org.project.model.building.Building;
import org.project.model.resource.Material;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;

import static org.project.model.building.BuildingFactory.*;
import static org.project.model.resource.ResourceFactory.*;

public final class Shop {
    private final ShopDelegate delegate;

    public Shop(ShopDelegate delegate) {
        this.delegate = delegate;
    }

    public boolean buyMaterial(Material.Type type) {
        Material material = this.getMaterial(type);
        if (!this.delegate.canBuy(material)) return false;

        Resources resources = new Resources();
        resources.put(material, new Quantity(1));

        this.delegate.buy(material);
        this.delegate.addToStock(resources);
        return true;
    }

    public boolean buyBuilding(Building building) {
        if (!this.delegate.canBuy(building)) return false;
        if (!this.delegate.haveEnoughtResources(building.getBuildRequirements())) return false;

        this.delegate.buy(building);
        this.delegate.removeFromStock(building.getBuildRequirements());
        return true;
    }

    private Material getMaterial(Material.Type type) {
        return switch (type) {
            case LUMBER -> lumber();
            case CEMENT -> cement();
            case TOOLS -> tools();
            case STEEL -> steel();
            case IRON -> iron();
            case FOOD -> food();
            case COAL -> coal();
            case STONE -> stone();
            case WOOD -> wood();
        };
    }
}
