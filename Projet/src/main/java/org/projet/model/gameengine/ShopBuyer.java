package org.projet.model.gameengine;

import org.projet.model.resource.Purchasable;

public interface ShopBuyer extends ResourcesManager {
    boolean canBuy(Purchasable object, int quantity);
    void buy(Purchasable object, int quantity);
}
