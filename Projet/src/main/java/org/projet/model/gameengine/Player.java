package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.resource.*;
import org.projet.utils.Quantity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.projet.model.resource.ResourceFactory.*;

/**
 * The player (which is a shop buyer, which is a resource manager) and has a stock of resources
 */
public final class Player implements ShopBuyer {
    private final Resources stock = new Resources();
    private List<ResourcesProvider> providers = new ArrayList<>();
    private List<ResourcesConsumer> consumers = new ArrayList<>();

    public Player() {
        // On initialise le stock du joueur avec toutes les resources du jeu à 0.
        stock.initWithAllResources();

        // On lui donne des ressources de base
        stock.get(wood()).add(10);
        stock.get(food()).add(10);
        stock.get(gold()).add(10);

        for(Material.Type type : Material.Type.values()) {
            stock.get(new Material(type)).add(99999);
        }
        stock.get(gold()).add(99999);
    }

    /**
     * @return the player stock of resources
     */
    public Resources getStock() {
        return new Resources(stock);
    }

    // ShopBuyer
    /**
     * @param object A purchasable object (material or building)
     * @param quantity The quantity to buy
     * @return true if the player can afford to buy the given quantity of the objet
     */
    @Override
    public boolean canBuy(Purchasable object, int quantity) {
        return object.getPrice() * quantity <= this.stock.get(gold()).get();
    }
    /**
     * Buy an object by removing its price (multiplied by the quantity wanted) from the player stock
     * @param object A purchasable object (material or building)
     * @param quantity The quantity to buy
     */
    @Override
    public void buy(Purchasable object, int quantity) {
        if(!canBuy(object, quantity)) throw new IllegalStateException("Please use canBuy() before buy()");
        this.stock.get(gold()).remove(object.getPrice() * quantity);
    }


    // ResourcesManager
    /**
     * Add resources to the player stock
     * @param resources The resources to add
     */
    @Override
    public void addToStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).add(q.get()));
    }
    /**
     * Remove resources from the player stock
     * @param resources The resources to remove
     */
    @Override
    public void removeFromStock(Resources resources) {
        resources.forEach((r, q) -> this.stock.get(r).remove(q.get()));
    }

    /**
     * @param resources Some resources
     * @return true if the player has enough of the given resources in its stock
     */
    @Override
    public boolean haveEnoughResources(Resources resources) {
        for (Map.Entry<Resource, Quantity> required: resources.entrySet()) {
            if (this.stock.get(required.getKey()).get() < required.getValue().get())
                return false;
        }
        return true;
    }

    // ResourcesManager
    /**
     * Notifies the different resources provider subscribed to the player to provide resources for the player
     */
    @Override
    public void collectProduction() {
        for(ResourcesProvider provider : providers) {
            provider.addProduction(this);
        }
    }
    /**
     * Notifies the different resources consumer subscribed to the player to consume resources in the player stock
     */
    @Override
    public void provideConsumption() {
        for(ResourcesConsumer resourceConsumer : consumers) {
            resourceConsumer.removeConsumption(this);
        }
    }

    /**
     * Subscribes a resource provider to the player
     * @param provider The resource provider
     */
    @Override
    public void subscribe(ResourcesProvider provider) {
        providers.add(provider);
    }
    /**
     * Subscribes a resource consumer to the player
     * @param consumer The resource consumer
     */
    @Override
    public void subscribe(ResourcesConsumer consumer) {
        consumers.add(consumer);
    }
    /**
     * Subscribes a building both as a resource provider and consumer for the player
     * @param building The building
     */
    @Override
    public void subscribe(Building building) {
        providers.add(building);
        consumers.add(building);
    }
}
