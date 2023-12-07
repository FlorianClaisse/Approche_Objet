package org.projet.model.building;

import static org.projet.model.building.Building.Type.*;
import static org.projet.model.resource.ResourceFactory.*;


public class BuildingFactory {

    public static Building makeWoodenCabin() {
        return BuildingBuilder.newBuilder()
                .setType(WOODEN_CABIN)
                .setNbHabitants(2)
                .setMinWorkers(2)
                .setPrice(1)
                .addBuildRequirement(wood(), 1)
                .addProduction(wood(), 2)
                .addProduction(food(), 2)
                .setBuildTime(2)
                .build();
    }

    public static Building makeHouse() {
        return BuildingBuilder.newBuilder()
                .setType(HOUSE)
                .setNbHabitants(4)
                .setPrice(1)
                .addBuildRequirement(wood(), 2)
                .addBuildRequirement(stone(), 2)
                .setBuildTime(4)
                .build();
    }

    public static Building makeApartment() {
        return BuildingBuilder.newBuilder()
                .setType(APARTMENT_BUILDING)
                .setNbHabitants(60)
                .setPrice(4)
                .addBuildRequirement(wood(), 50)
                .addBuildRequirement(stone(), 50)
                .setBuildTime(6)
                .build();

    }

    public static Building makeFarm() {
        return BuildingBuilder.newBuilder()
                .setType(FARM)
                .setNbHabitants(5)
                .setMinWorkers(3)
                .setPrice(4)
                .addBuildRequirement(wood(), 5)
                .addBuildRequirement(stone(), 5)
                .addProduction(food(), 10)
                .setBuildTime(2)
                .build();
    }

    public static Building makeQuarry() {
        return BuildingBuilder.newBuilder()
                .setType(QUARRY)
                .setNbHabitants(2)
                .setMinWorkers(30)
                .setPrice(4)
                .addBuildRequirement(wood(), 50)
                .addProduction(stone(), 4)
                .addProduction(iron(), 4)
                .addProduction(coal(), 4)
                .addProduction(gold(), 2)
                .setBuildTime(2)
                .build();
    }

    public static Building makeLumberMill() {
        return BuildingBuilder.newBuilder()
                .setType(LUMBER_MILL)
                .setMinWorkers(10)
                .setPrice(6)
                .addBuildRequirement(wood(), 50)
                .addBuildRequirement(stone(), 50)
                .addConsumption(wood(), 4)
                .addProduction(lumber(), 4)
                .setBuildTime(4)
                .build();
    }

    public static Building makeCementPlant() {
        return BuildingBuilder.newBuilder()
                .setType(CEMENT_PLANT)
                .setMinWorkers(10)
                .setPrice(6)
                .addBuildRequirement(wood(), 50)
                .addBuildRequirement(stone(), 50)
                .addConsumption(stone(), 4)
                .addConsumption(coal(), 4)
                .addProduction(cement(), 4)
                .setBuildTime(4)
                .build();
    }

    public static Building makeSteelMill() {
        return BuildingBuilder.newBuilder()
                .setType(STEEL_MILL)
                .setMinWorkers(40)
                .setPrice(6)
                .addBuildRequirement(wood(), 100)
                .addBuildRequirement(stone(), 50)
                .addConsumption(iron(), 4)
                .addConsumption(coal(), 2)
                .addProduction(steel(), 4)
                .setBuildTime(6)
                .build();
    }

    public static Building makeToolFactory() {
        return BuildingBuilder.newBuilder()
                .setType(TOOL_FACTORY)
                .setMinWorkers(12)
                .setPrice(8)
                .addBuildRequirement(wood(), 50)
                .addBuildRequirement(stone(), 50)
                .addConsumption(steel(), 4)
                .addConsumption(coal(), 4)
                .addProduction(tools(), 4)
                .setBuildTime(8)
                .build();
    }

    public static Building makeLaunchingRocketPlatform() {
        return BuildingBuilder.newBuilder()
                .setType(LAUNCHING_ROCKET_PLATFORM)
                .setMinWorkers(100)
                .setPrice(10)
                .addBuildRequirement(cement(), 50)
                .addBuildRequirement(tools(), 50)
                .addBuildRequirement(steel(), 50)
                .addBuildRequirement(coal(), 50)
                .setBuildTime(10)
                .build();
    }
}
