package org.project.model.gameengine;

import org.project.model.building.Building;
import org.project.model.resource.Material;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;

import org.project.model.resource.ResourceFactory;

public final class Shop {
    private final ShopDelegate buyer;

    public Shop(ShopDelegate player) {
        this.buyer = player;
    }

    public boolean buyMaterials(Material.Type type, int quantity) {
        Material material = ResourceFactory.material(type);
        if (!this.buyer.canBuy(material, quantity)) return false;

        Resources resources = new Resources();
        resources.put(material, new Quantity(quantity));

        this.buyer.buy(material, quantity);
        this.buyer.addToStock(resources);
        return true;
    }

    protected boolean buyBuilding(Building building) {
        if (!this.buyer.canBuy(building, 1)) return false;
        if (!this.buyer.haveEnoughResources(building.getBuildRequirements())) return false;

        this.buyer.buy(building, 1);
        this.buyer.removeFromStock(building.getBuildRequirements());
        return true;
    }
}
