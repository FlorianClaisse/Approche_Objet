package org.project.model.building;

import org.project.model.resource.Buyable;
import org.project.model.resource.Resource;

import java.util.Set;

public class Building extends Buyable {
    private final Type type;
    private int level;
    private final int minHabitants;
    private final int minWorkers;
    private final Set<Resource> buildRequirements;
    private final Set<Resource> consomation;
    private final Set<Resource> production;
    private final int buildTime;

    protected Building(Type type,
                       int minHabitants,
                       int minWorkers,
                       int price,
                       Set<Resource> buildRequirements,
                       Set<Resource> consomation,
                       Set<Resource> production,
                       int buildTime)
    {
        super(price);
        if (type == null) throw new NullPointerException("You are trying to create a building with a null type.");
        this.type = type;
        this.level = 1;
        this.minHabitants = minHabitants;
        this.minWorkers = minWorkers;
        this.buildRequirements = buildRequirements;
        this.consomation = consomation;
        this.production = production;
        this.buildTime = buildTime;
    }

    public Set<Resource> getBuildRequirements() {
        return this.buildRequirements;
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
