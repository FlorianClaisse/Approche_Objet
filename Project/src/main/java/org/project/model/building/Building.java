package org.project.model.building;

import org.project.exceptions.BuildingException;
import org.project.model.Resource;

import java.util.Set;

public class Building {
    private final Type type;
    private final int nbHabitants;
    private final int nbWorkers;
    private final Set<Resource> buildRequirements;
    private final Set<Resource> consomation;
    private final Set<Resource> production;
    private final int buildTime;

    protected Building(Type type,
                       int nbHabitants,
                       int nbWorkers,
                       Set<Resource> buildRequirements,
                       Set<Resource> consomation,
                       Set<Resource> production,
                       int buildTime)
    {
        if (type == null) throw new BuildingException("You are trying to create a building with a null type.");
        this.type = type;
        this.nbHabitants = nbHabitants;
        this.nbWorkers = nbWorkers;
        this.buildRequirements = buildRequirements;
        this.consomation = consomation;
        this.production = production;
        this.buildTime = buildTime;
    }

    @Override
    public String toString() {
        return "Building(type=" + type +
                ", nbHabitants=" + nbHabitants +
                ", nbWorkers=" + nbWorkers +
                ", buildRequirements=" + buildRequirements +
                ", consomation=" + consomation +
                ", production=" + production +
                ", buildTime=" + buildTime + ')';
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

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }
}
