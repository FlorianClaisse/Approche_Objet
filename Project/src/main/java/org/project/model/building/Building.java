package org.project.model.building;

import org.jetbrains.annotations.NotNull;
import org.project.model.resource.Purchasable;
import org.project.model.resource.Resources;

public class Building implements Purchasable {
    private int level = 1;
    
    private final Type type;
    private final int price;
    private final int nbHabitants;
    private final int minWorkers;
    private int currentWorkers;
    private int maxWorkers;
    private final Resources buildRequirements;
    private final Resources consumption;
    private final Resources production;
    private final int buildTime;
    private int remainingTime;

    protected Building(@NotNull Type type, int price, int nbHabitants, int minWorkers, Resources buildRequirements, Resources consumption, Resources production, int buildTime) {
        this.type = type;
        this.price = price;

        this.nbHabitants = nbHabitants;

        this.minWorkers = minWorkers;
        this.currentWorkers = minWorkers;
        this.maxWorkers = minWorkers;

        this.buildRequirements = buildRequirements;
        this.consumption = consumption;
        this.production = production;

        this.buildTime = buildTime;
        this.remainingTime = buildTime;
    }

    public int getNbHabitants() { return this.nbHabitants; }
    public int getMinWorkers() { return this.minWorkers; }
    public int getCurrentWorkers() { return this.currentWorkers; }
    public Resources getBuildRequirements() { return this.buildRequirements; }
    public Resources getConsumption() { return this.consumption; }
    public Resources getProduction() { return this.production; }

    @Override public int getPrice() { return this.price; }
    @Override public String getTypeName() { return this.type.rawValue; }

    public void removeOnePeriod() { this.remainingTime--; }
    public boolean isBuilt() { return this.remainingTime == 0; }

    public boolean canAddWorkers(int value) {
        return (this.currentWorkers + value) <= this.maxWorkers;
    }

    public boolean canRemoveWorkers(int value) {
        return (this.currentWorkers - value) >= this.minWorkers;
    }

    public void addWorkers(int value) {
        if (!canAddWorkers(value)) throw new IllegalStateException("Can't add workers please use canAddWorkers()");
        this.currentWorkers += value;
    }

    public void removeWorkers(int value) {
        if (!canRemoveWorkers(value)) throw new IllegalStateException("Can't remove workers please use canRemoveWorkers()");
        this.currentWorkers -= value;
    }

    @Override
    public String toString() {
        return type +
                "(level=" + level +
                ", nbHabitants=" + nbHabitants +
                ", minWorkers=" + minWorkers +
                ", currentWorkers=" + currentWorkers +
                ", maxWorkers=" + maxWorkers +
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
