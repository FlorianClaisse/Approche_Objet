package org.project.model.building;

import org.project.model.resource.Resource;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;

public class BuildingBuilder {
    private Building.Type type = null; // Pour être obligé de set le type dans le builder sinon exception
    private int price = 0;
    private int nbHabitants = 0;
    private int minWorkers = 0;
    private final Resources buildRequirements = new Resources();
    private final Resources consumption = new Resources();
    private final Resources production = new Resources();
    private int buildTime = 0;

    private BuildingBuilder() { }

    public static BuildingBuilder newBuilder() {
        return new BuildingBuilder();
    }

    public BuildingBuilder setType(Building.Type type) {
        this.type = type;
        return this;
    }

    public BuildingBuilder setPrice(int price) {
        if (price < 0) throw new IllegalArgumentException("The price cannot be less than 0");
        this.price = price;
        return this;
    }

    public BuildingBuilder setNbHabitants(int nbHabitants) {
        if (nbHabitants < 0) throw new IllegalArgumentException("The number of nbHabitants cannot be less than 0");
        this.nbHabitants = nbHabitants;
        return this;
    }

    public BuildingBuilder setMinWorkers(int minWorkers) {
        if (minWorkers < 0) throw new IllegalArgumentException("The number of minWorkers cannot be less than 0");
        this.minWorkers = minWorkers;
        return this;
    }

    public BuildingBuilder addBuildRequirement(Resource resources, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("The quantity of a material cannot be less than or equal to 0");
        this.buildRequirements.put(resources, new Quantity(quantity));
        return this;
    }

    public BuildingBuilder addConsumption(Resource resources, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("The quantity of a material cannot be less than or equal to 0");
        this.consumption.put(resources, new Quantity(quantity));
        return this;
    }

    public BuildingBuilder addProduction(Resource resources, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("The quantity of a material cannot be less than or equal to 0");
        this.production.put(resources, new Quantity(quantity));
        return this;
    }

    public BuildingBuilder setBuildTime(int time) {
        if (time < 0) throw new IllegalArgumentException("Build time cannot be less than 0.");
        this.buildTime = time;
        return this;
    }

    public Building build() {
        return new Building(this.type,
                            this.price,
                            this.nbHabitants,
                            this.minWorkers,
                            this.buildRequirements,
                            this.consumption,
                            this.production,
                            this.buildTime);
    }
}
