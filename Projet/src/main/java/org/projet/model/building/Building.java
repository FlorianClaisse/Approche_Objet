package org.projet.model.building;

import org.projet.model.gameengine.ResourcesConsumer;
import org.projet.model.gameengine.ResourcesManager;
import org.projet.model.gameengine.ResourcesProvider;
import org.projet.model.resource.Purchasable;
import org.projet.model.resource.Resources;
import org.projet.utils.Quantity;

/**
 * The class representing a building which provides and consumes resources and can be purchased
 */
public class Building implements Purchasable, ResourcesProvider, ResourcesConsumer {
    private static final int maxLevel = 5;

    private int level = 1;

    private final Type type;
    private final int price;
    private final int nbHabitants;
    private final int minWorkers;
    private int currentWorkers;
    private int maxWorkers;
    private final Resources buildRequirements;
    private final Resources updateRequirements;
    private final Resources consumption;
    private final Resources production;
    private final int buildTime;
    private int remainingTime;

    /**
     * @param type Building type
     * @param price Cost in gold to purchase the building
     * @param nbHabitants The number of habitants created with the building
     * @param minWorkers The minimum number of workers needed to create the building
     * @param buildRequirements The materials needed to create the building
     * @param consumption The materials consumed by the building
     * @param production The materials produced by the building
     * @param buildTime The time needed for the building to be operational
     */
    protected Building(Type type, int price, int nbHabitants, int minWorkers, Resources buildRequirements, Resources consumption, Resources production, int buildTime) {
        if (type == null) throw new IllegalArgumentException("Can't use null for building type");
        if (nbHabitants == 0 && minWorkers == 0) throw new IllegalStateException("nbHabitants and minWorkers can't be both equal to 0");
        this.type = type;
        this.price = price;

        this.nbHabitants = nbHabitants;

        this.minWorkers = minWorkers;
        this.currentWorkers = minWorkers;
        this.maxWorkers = minWorkers;

        this.buildRequirements = buildRequirements;
        Resources resources = new Resources();
        buildRequirements.forEach((r, q) -> resources.put(r, new Quantity((int)Math.ceil(q.get() * 1.5))));
        this.updateRequirements = resources;
        this.consumption = consumption;
        this.production = production;

        this.buildTime = buildTime;
        this.remainingTime = buildTime;
    }

    /**
     * @param quantity The quantity of workers to add
     * @return true if the workers can be added to the building
     */
    public boolean canAddWorkers(int quantity) {
        return (this.currentWorkers + quantity) <= this.maxWorkers;
    }

    /**
     * @param quantity The quantity of workers to remove
     * @return true if the workers can be removed from the building
     */
    public boolean canRemoveWorkers(int quantity) {
        return (this.currentWorkers - quantity) >= this.minWorkers;
    }

    /**
     * @param quantity Number of workers to add to the building
     */
    public void addWorkers(int quantity) {
        if (!canAddWorkers(quantity)) throw new IllegalStateException("Can't add workers, please use canAddWorkers()");
        this.currentWorkers += quantity;
    }
    /**
     * @param quantity Number of workers to remove from the building
     */
    public void removeWorkers(int quantity) {
        if (!canRemoveWorkers(quantity)) throw new IllegalStateException("Can't remove workers, please use canRemoveWorkers()");
        this.currentWorkers -= quantity;
    }

    /**
     * @return true if the building can be upgraded
     */
    public boolean canBeUpgraded() {
        return this.level != maxLevel && !(this.type == Type.HOUSE || this.type == Type.APARTMENT_BUILDING);
    }

    /**
     * Increments the level of the building and increase its maximum number of workers and the quantity of materials needed for a future upgrade
     */
    public void upgrade() {
        if (!canBeUpgraded()) throw new IllegalStateException("Please use canBeUpgraded() before upgrade().");
        this.level++;
        this.maxWorkers = (int) Math.ceil(this.maxWorkers * 1.5);

        // New requirements for future upgrade
        this.updateRequirements.forEach((r, q) -> q.set((int)Math.ceil(q.get() * 1.5)));
    }

    /**
     * @return true if the building is at max level
     */
    public boolean isMaxLevel() { return this.level == maxLevel; }

    /**
     * Removes one period of construction time (for the building to be fully operational)
     */
    public void removeOnePeriod() {
        if (this.remainingTime > 0)
            this.remainingTime--;
    }

    /**
     * @return true if the building construction is finished (no construction time remaining)
     */
    public boolean isBuilt() { return this.remainingTime == 0; }

    public Type getType() { return this.type; }
    @Override public String getTypeName() { return this.type.rawValue; }
    @Override public int getPrice() { return this.price; }
    public int getNbHabitants() { return this.nbHabitants; }
    public int getMinWorkers() { return this.minWorkers; }
    public int getCurrentWorkers() { return this.currentWorkers; }
    public int getMaxWorkers() { return this.maxWorkers; }
    public Resources getBuildRequirements() { return new Resources(this.buildRequirements); }
    public Resources getUpdateRequirements() { return new Resources(this.updateRequirements); }

    /**
     * @return the resources consumed by the building (depending partially of its number of workers)
     */
    @Override
    public Resources getConsumption() {
        Resources resources = new Resources();
        this.consumption.forEach((r, q) -> resources.put(r, new Quantity((int)Math.ceil(q.get() * this.currentWorkers / this.minWorkers))));
        return resources;
    }
    /**
     * @return the resources produced by the building (depending partially of its number of workers)
     */
    @Override
    public Resources getProduction() {
        Resources resources = new Resources();
        this.production.forEach((r, q) -> resources.put(r, new Quantity((int)Math.ceil(q.get() * this.currentWorkers / this.minWorkers))));
        return resources;
    }

    /**
     * Add the resources produced by the building to the stock of the given player
     * @param player
     */
    @Override
    public void addProduction(ResourcesManager player) {
        player.addToStock(getProduction());
    }
    /**
     * Remove the resources consumed by the building from the stock of the given player
     * @param player
     */
    @Override
    public void removeConsumption(ResourcesManager player) {
        player.removeFromStock(getConsumption());
    }

    @Override
    public String toString() {
        return type +
                "(level=" + level +
                ", price=" + price +
                ", nbHabitants=" + nbHabitants +
                ", minWorkers=" + minWorkers +
                ", currentWorkers=" + currentWorkers +
                ", maxWorkers=" + maxWorkers +
                ", buildRequirements=" + buildRequirements +
                ", updateRequirements=" + updateRequirements +
                ", consumption=" + getConsumption() +
                ", production=" + getProduction() +
                ", buildTime=" + buildTime +
                ", remainingTime=" + remainingTime +
                ')';
    }

    /**
     * Building types
     */
    public enum Type {
        WOODEN_CABIN("Wooden Cabin"),
        HOUSE ("House"),
        APARTMENT_BUILDING("Apartment Building"),
        FARM("Farm"),
        QUARRY("Quarry"),
        LUMBER_MILL("Lumber Mill"),
        CEMENT_PLANT("Cement Plant"),
        STEEL_MILL("Steel Mill"),
        TOOL_FACTORY("Tool Factory"),
        LAUNCHING_ROCKET_PLATFORM("Launching Rocket Platform");

        private final String rawValue;

        Type(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
