package org.project.model.gameengine;

import org.project.model.resource.Material;
import org.project.model.resource.Resource;
import org.project.model.resource.Resources;

import java.util.*;

public final class Player implements ManagerDelegate {
    private final Resources stock;

    public Player() {
        stock = new Resources();
        stock.put(new Material(Material.Type.FOOD), 10);
        stock.put(new Material(Material.Type.WOOD), 10);
    }

    @Override public boolean canConstruct(Resources requirements) {
        // Vérifie que le stock contient assez de tous les types de ressources
        for (Map.Entry<Resource, Integer> required: requirements.entrySet()) {
            if (!stock.containsKey(required.getKey()))
                return false;

            if (stock.get(required.getKey()) < required.getValue())
                return false;
        }
        return true;
    }

    @Override public void removeFromStock(Resources resources) {
        for (Map.Entry<Resource, Integer> toRemove: resources.entrySet()) {
            stock.put(toRemove.getKey(), stock.get(toRemove.getKey()) - toRemove.getValue());
        }
    }

    public void addToStock(Resources resources) {
        for (Map.Entry<Resource, Integer> toAdd: resources.entrySet()) {
            if (stock.containsKey(toAdd.getKey())) {
                stock.put(toAdd.getKey(), toAdd.getValue() + stock.get(toAdd.getKey()));
            } else {
                stock.put(toAdd.getKey(), toAdd.getValue());
            }
        }
    }

    @Override
    public boolean canBuy(int price) { return false; }

    public void printStock() {
        System.out.println(stock);
    }
}
