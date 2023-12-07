package org.projet.model.gameengine;

import org.projet.model.resource.*;
import org.projet.utils.Quantity;

import java.util.Map;

import static org.projet.model.resource.ResourceFactory.*;

public final class Player implements ShopDelegate {
    private final Resources stock = new Resources();

    public Player() {
        // On initialise le stock du joueur avec toutes les resources du jeu à 0.
        stock.initWithAllResources();

        // On lui donne des ressources de base
        stock.get(wood()).add(10);
        stock.get(food()).add(10);
        stock.get(gold()).add(10);

        /* Pour tester...
        for(Material.Type type : Material.Type.values()) {
            stock.get(new Material(type)).add(99999);
        }
        stock.get(gold()).add(99999);
        */
    }

    public Resources getStock() {
        return stock;
    }

    // ShopDelegate
    @Override
    public boolean canBuy(Purchasable object, int quantity) {
        return object.getPrice() * quantity <= this.stock.get(gold()).get();
    }

    @Override
    public void buy(Purchasable object, int quantity) {
        this.stock.get(gold()).remove(object.getPrice() * quantity);
    }

    @Override
    public boolean haveEnoughResources(Resources resources) {
        for (Map.Entry<Resource, Quantity> required: resources.entrySet()) {
            if (this.stock.get(required.getKey()).get() < required.getValue().get())
                return false;
        }
        return true;
    }

    // ResourceManager
    @Override
    public void addToStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).add(q.get()));
    }

    @Override
    public void removeFromStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).remove(q.get()));
    }
}
