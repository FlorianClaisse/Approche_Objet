package org.project.model.building;

import org.jetbrains.annotations.NotNull;
import org.project.model.resource.Buyable;
import org.project.model.resource.Resource;

import java.util.List;
import java.util.Set;

public class Building extends Buyable {
    private final Type type;
    private int level;
    private final int nbHabitants;
    private final int minWorkers;
    private int maxWorkers;
    private int currentWorkers;
    private final Set<Resource> buildRequirements;
    private final Set<Resource> consomation;
    private final Set<Resource> production;
    private final int buildTime;
    private int remainingTime;

    protected Building(@NotNull Type type, int nbHabitants, int minWorkers, int price, Set<Resource> buildRequirements, Set<Resource> consomation, Set<Resource> production, int buildTime) {
        super(price);
        this.type = type;
        this.level = 1;
        this.nbHabitants = nbHabitants;
        this.minWorkers = minWorkers;
        this.maxWorkers = minWorkers;
        this.currentWorkers = minWorkers;
        this.buildRequirements = buildRequirements;
        this.consomation = consomation;
        this.production = production;
        this.buildTime = buildTime;
        this.remainingTime = buildTime;
    }

    public int getNbHabitants() { return this.nbHabitants; }
    public int getMinWorkers() { return this.minWorkers; }
    public int getCurrentWorkers() { return this.currentWorkers; }
    public List<Resource> getBuildRequirements() { return this.buildRequirements.stream().toList(); }
    public List<Resource> getConsomation() { return this.consomation.stream().toList(); }
    public List<Resource> getProduction() { return this.production.stream().toList(); }

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
