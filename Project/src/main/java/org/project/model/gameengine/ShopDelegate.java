package org.project.model.gameengine;

import org.project.model.building.Building;
import org.project.model.resource.Purchasable;
import org.project.model.resource.Resources;

public interface ShopDelegate extends ResourceUpdatable {
    boolean canBuy(Purchasable object);
    void buy(Purchasable object);
    boolean haveEnoughtResources(Resources resources);
}
