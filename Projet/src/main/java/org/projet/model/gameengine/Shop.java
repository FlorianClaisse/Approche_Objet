package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.resource.Material;
import org.projet.model.resource.Resources;
import org.projet.utils.Quantity;

import org.projet.model.resource.ResourceFactory;

public final class Shop {
    /**
     * Makes a player buy a given quantity of a given material to put it in its stock
     * @param buyer The player
     * @param type The type of material
     * @param quantity The quantity to buy
     * @return false if the player can't buy the given quantity of the given materials
     */
    protected boolean buyMaterials(ShopBuyer buyer, Material.Type type, int quantity) {
        Material material = ResourceFactory.material(type);
        if (!buyer.canBuy(material, quantity)) return false;

        Resources resources = new Resources();
        resources.put(material, new Quantity(quantity));

        buyer.buy(material, quantity);
        buyer.addToStock(resources);
        return true;
    }

    /**
     * Makes a player buy a building and remove the resources required for the construction of this building
     * @param buyer The player
     * @param building The building
     * @return false if the player can't buy the building or if he doesn't have enough resources
     */
    protected boolean buyBuilding(ShopBuyer buyer, Building building) {
        if (!buyer.canBuy(building, 1)) return false;
        if (!buyer.haveEnoughResources(building.getBuildRequirements())) return false;

        buyer.buy(building, 1);
        buyer.removeFromStock(building.getBuildRequirements());
        return true;
    }
}
