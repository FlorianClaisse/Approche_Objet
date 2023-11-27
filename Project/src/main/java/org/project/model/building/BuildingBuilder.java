package org.project.model.building;

import org.project.model.resource.Resource;

import java.util.HashSet;
import java.util.Set;

public class BuildingBuilder {
    private Building.Type type;
    private int minHabitants;
    private int minWorkers;
    private int price;
    private final Set<Resource> buildRequirements;
    private final Set<Resource> consomation;
    private final Set<Resource> production;
    private int buildTime;

    private BuildingBuilder() {
        this.type = null; // Pour être obligé de set le type dans le builder sinon exception
        this.minHabitants = 0;
        this.minWorkers = 0;
        this.price = 0;
        this.buildRequirements = new HashSet<>();
        this.consomation = new HashSet<>();
        this.production = new HashSet<>();
        this.buildTime = 0;
    }

    public static BuildingBuilder builder() {
        return new BuildingBuilder();
    }

    public BuildingBuilder setType(Building.Type type) {
        this.type = type;
        return this;
    }

    public BuildingBuilder setMinHabitants(int minHabitants) {
        if (minHabitants < 0) throw new IllegalArgumentException("The number of minHabitants cannot be less than 0");
        this.minHabitants = minHabitants;
        return this;
    }

    public BuildingBuilder setMinWorkers(int minWorkers) {
        if (minWorkers < 0) throw new IllegalArgumentException("The number of minWorkers cannot be less than 0");
        this.minWorkers = minWorkers;
        return this;
    }

    public BuildingBuilder setPrice(int price) {
        if (price < 0) throw new IllegalArgumentException("The price cannot be less than 0");
        this.price = price;
        return this;
    }

    public BuildingBuilder addBuildRequirement(Resource resources) {
        if (resources.getQuantity() <= 0) throw new IllegalArgumentException("The quantity of a material cannot be less than or equal to 0");
        this.buildRequirements.add(resources);
        return this;
    }

    public BuildingBuilder addConsomation(Resource resources) {
        if (resources.getQuantity() <= 0) throw new IllegalArgumentException("The quantity of a material cannot be less than or equal to 0");
        this.consomation.add(resources);
        return this;
    }

    public BuildingBuilder addProduction(Resource resources) {
        if (resources.getQuantity() <= 0) throw new IllegalArgumentException("The quantity of a material cannot be less than or equal to 0");
        this.production.add(resources);
        return this;
    }

    public BuildingBuilder setBuildTime(int time) {
        if (time < 0) throw new IllegalArgumentException("Build time cannot be less than 0.");
        this.buildTime = time;
        return this;
    }

    public Building build() {
        return new Building(this.type,
                            this.minHabitants,
                            this.minWorkers,
                            this.price,
                            this.buildRequirements,
                            this.consomation,
                            this.production,
                            this.buildTime);
    }
}
