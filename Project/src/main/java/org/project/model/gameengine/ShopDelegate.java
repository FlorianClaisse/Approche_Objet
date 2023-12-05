package org.project.model.gameengine;

import org.project.model.resource.Purchasable;

public interface ShopDelegate extends ResourceManager {
    boolean canBuy(Purchasable object, int quantity);
    void buy(Purchasable object, int quantity);
}
