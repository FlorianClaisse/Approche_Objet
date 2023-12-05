package org.project.model.building;

import org.project.model.resource.Purchasable;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;

import java.util.stream.StreamSupport;

public class Building implements Purchasable {
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

    public boolean canUpgrade() {
        return this.level != maxLevel && this.type != Type.HOUSE && this.type != Type.APARTMENT_BUILDING;
    }

    public void upgrade() {
        if (!canUpgrade()) throw new IllegalStateException("Please use canUpgrade before upgrade.");
        this.level++;
        this.maxWorkers = (int) Math.ceil(this.maxWorkers * 1.5);
        this.updateRequirements.forEach((r, q) -> q.add((int)Math.ceil(q.get() * 1.5)));
    }

    public boolean isMaxLevel() { return this.level == maxLevel; }

    public boolean canAddWorkers(int quantity) {
        return (this.currentWorkers + quantity) <= this.maxWorkers;
    }

    public boolean canRemoveWorkers(int quantity) {
        return (this.currentWorkers - quantity) >= this.minWorkers;
    }

    // TODO: Ajouter l'augmentation de la prodution et de la consommation
    public void addWorkers(int quantity) {
        if (!canAddWorkers(quantity)) throw new IllegalStateException("Can't add workers please use canAddWorkers()");
        this.currentWorkers += quantity;
    }

    // TODO: Ajouter la diminution de la production et de la consommation.
    public void removeWorkers(int quantity) {
        if (!canRemoveWorkers(quantity)) throw new IllegalStateException("Can't remove workers please use canRemoveWorkers()");
        this.currentWorkers -= quantity;
    }

    public void removeOnePeriod() {
        if (this.remainingTime > 0)
            this.remainingTime--;
    }

    public boolean isBuilt() { return this.remainingTime == 0; }

    public int getLevel() { return this.level; }
    @Override public String getTypeName() { return this.type.rawValue; }
    @Override public int getPrice() { return this.price; }
    public int getNbHabitants() { return this.nbHabitants; }
    public int getMinWorkers() { return this.minWorkers; }
    public int getCurrentWorkers() { return this.currentWorkers; }
    public int getMaxWorkers() { return this.maxWorkers; }
    public Resources getBuildRequirements() { return this.buildRequirements; }
    public Resources getUpdateRequirements() { return this.updateRequirements; }

    public Resources getConsumption() {
        Resources resources = new Resources();
        double ratio = this.currentWorkers / (double)this.minWorkers;
        this.consumption.forEach((r, q) -> resources.put(r, new Quantity((int)Math.ceil(q.get() * ratio))));
        return resources;
    }
    public Resources getProduction() {
        Resources resources = new Resources();
        double ratio = this.currentWorkers / (double)this.minWorkers;
        this.production.forEach((r, q) -> resources.put(r, new Quantity((int)Math.ceil(q.get() * ratio))));
        return resources;
    }
    public int getBuildTime() { return this.buildTime; }

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
                ", consumption=" + consumption +
                ", production=" + production +
                ", buildTime=" + buildTime +
                ", remainingTime=" + remainingTime +
                ')';
    }

    public enum Type {
        WOODEN_CABIN("Wooden Cabin"),
        HOUSE ("House"),
        APARTMENT_BUILDING("Apartment Building"),
        FARM("Farm"),
        QUARRY("Quarry"),
        LUMBER_MILL("Lumber Mill"),
        CEMENT_PLANT("Cement Plant"),
        STEEL_MILL("Steel Mill"),
        TOOL_FACTORY("Tool Factory");

        private final String rawValue;

        Type(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
