package org.project.model.gameengine;

import org.project.model.resource.Purchasable;
import org.project.model.resource.Resources;

public interface ShopDelegate extends ResourceUpdatable {
    boolean canBuy(Purchasable object, int quantity);
    void buy(Purchasable object, int quantity);
    boolean haveEnoughResources(Resources resources);
}
