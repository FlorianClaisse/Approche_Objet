package org.project.model.gameengine;

import org.project.model.resource.Material;
import org.project.model.resource.Resource;

import java.util.*;

public final class Player implements ManagerDelegate {
    private final Set<Resource> stock;

    public Player() {
        stock = new HashSet<>();
        stock.add(new Material(Material.Type.FOOD, 10));
        stock.add(new Material(Material.Type.WOOD, 10));
    }

    public boolean canConstruct(List<Resource> requirements) {
        // Vérifie SEULEMENT que le stock contient tout les types de ressources
        if(!stock.containsAll(requirements))
            return false;

        // Vérifie que le stock contient assez de tout les types de ressources
        for(Resource required : requirements) {
            boolean enoughRequired = false;
            for(Resource stocked : stock) {
                if(required.equals(stocked)) {
                    if(required.getQuantity() <= stocked.getQuantity())
                        enoughRequired = true;
                    break;
                }
            }
            if(enoughRequired == false)
                return false;
        }

        return true;
    }

    public void removeFromStock(List<Resource> resources) {
        for(Resource toRemove : resources) {
            for(Resource stocked : stock) {
                if(toRemove.equals(stocked)) {
                    int toRemoveQuantity = toRemove.getQuantity();
                    if(toRemoveQuantity >= stocked.getQuantity())
                        stock.remove(stocked);
                    else
                        stocked.removeQuantity(toRemoveQuantity);
                    break;
                }
            }
        }
    }

    public void addToStock(List<Resource> resources) {
        for(Resource toAdd : resources) {
            boolean alreadyStocked = false;
            for(Resource stocked : stock) {
                if(toAdd.equals(stocked)) {
                    alreadyStocked = true;
                    stocked.addQuantity(toAdd.getQuantity());
                    break;
                }
            }
            if(!alreadyStocked)
                stock.add(toAdd);
        }
    }

    public void printStock() {
        for(Resource stocked : stock) {
            System.out.print(stocked + "\n");
        }
    }
}
