package org.project.model.gameengine;

import org.project.model.resource.*;
import org.project.utils.Quantity;

import java.util.Map;

import static org.project.model.resource.ResourceFactory.*;

public final class Player implements ShopDelegate {
    private final Resources stock = new Resources();

    public Player() {
        // On initialise le joueur avec toutes les resources du jeu à 0.
        stock.initWithAllResources();
        stock.get(wood()).add(10);
        stock.get(food()).add(10);
        stock.get(gold()).add(10);
    }


    // ShopDelegate

    @Override
    public boolean canBuy(Purchasable object) {
        return object.getPrice() <= this.stock.get(gold()).get();
    }

    @Override
    public void buy(Purchasable object) {
        this.stock.get(gold()).remove(object.getPrice());
    }

    @Override
    public boolean haveEnoughtResources(Resources resources) {
        for (Map.Entry<Resource, Quantity> required: resources.entrySet()) {
            if (this.stock.get(required.getKey()).get() < required.getValue().get())
                return false;
        }
        return true;
    }

    // ResourceUpdatable

    @Override
    public void addToStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).add(q.get()));
    }

    @Override
    public void removeFromStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).remove(q.get()));
    }
}
