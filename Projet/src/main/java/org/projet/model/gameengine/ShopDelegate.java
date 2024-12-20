package org.projet.model.gameengine;

import org.projet.model.resource.Purchasable;

public interface ShopDelegate extends ResourceManager {
    boolean canBuy(Purchasable object, int quantity);
    void buy(Purchasable object, int quantity);
}
