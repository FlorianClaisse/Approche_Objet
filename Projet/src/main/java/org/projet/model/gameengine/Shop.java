package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.resource.Material;
import org.projet.model.resource.Resources;
import org.projet.utils.Quantity;

import org.projet.model.resource.ResourceFactory;

public final class Shop {
    protected boolean buyMaterials(ShopBuyer buyer, Material.Type type, int quantity) {
        Material material = ResourceFactory.material(type);
        if (!buyer.canBuy(material, quantity)) return false;

        Resources resources = new Resources();
        resources.put(material, new Quantity(quantity));

        buyer.buy(material, quantity);
        buyer.addToStock(resources);
        return true;
    }

    protected boolean buyBuilding(ShopBuyer buyer, Building building) {
        if (!buyer.canBuy(building, 1)) return false;
        if (!buyer.haveEnoughResources(building.getBuildRequirements())) return false;

        buyer.buy(building, 1);
        buyer.removeFromStock(building.getBuildRequirements());
        return true;
    }
}
