package org.project.model.gameengine;

import org.project.model.resource.Gold;
import org.project.model.resource.Material;
import org.project.model.resource.Resource;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;

import java.util.Map;

import static org.project.model.resource.Material.Type.*;

public final class Player implements CityDelegate {
    private final Resources stock;

    public Player() {
        stock = new Resources();
        // On initialise le jour avec toutes les resources du jeu.
        stock.initWithAllResources();
        stock.get(new Material(WOOD)).add(10);
        stock.get(new Material(FOOD)).add(10);
        stock.get(new Gold()).add(10);
    }

    public Resources getStock() { return this.stock; }

    @Override
    public boolean canConstruct(Resources requirements) {
        // Vérifie que le stock contient assez de tous les types de ressources
        for (Map.Entry<Resource, Quantity> required : requirements.entrySet()) {
            if (stock.get(required.getKey()).get() < required.getValue().get())
                return false;
        }
        return true;
    }

    @Override
    public void removeFromStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).remove(q.get()));
    }

    @Override
    public void addToStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).add(q.get()));
    }

    @Override
    public boolean canBuy(int price) {
        return this.stock.get(new Gold()).get() >= price;
    }

    @Override
    public boolean isInShortage() {
        for (Quantity quantity: this.stock.values())
            if (quantity.get() < 0)
                return true;

        return false;
    }

    @Override
    public void buy(int price) {
        this.stock.get(new Gold()).remove(price);
    }
}
