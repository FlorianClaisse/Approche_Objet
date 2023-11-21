package org.project.model.building;

import org.project.model.Resource;
import static org.project.model.Resource.Type.*;
import static org.project.model.building.Building.Type.*;


public class BuildingFactory {

    public static Building makeWoodenCabin() {
        return BuildingBuilder.builder()
                .setType(WOODEN_CABIN)
                .setNbHabitants(2)
                .setNbWorkers(2)
                .addBuildRequirement(new Resource(GOLD, 1))
                .addBuildRequirement(new Resource(WOOD, 1))
                .addProduction(new Resource(WOOD, 2))
                .addProduction(new Resource(FOOD, 2))
                .setBuildTime(2)
                .build();
    }

    public static Building makeHouse() {
        return BuildingBuilder.builder()
                .setType(HOUSE)
                .setNbHabitants(4)
                .setNbWorkers(0)
                .addBuildRequirement(new Resource(GOLD, 1))
                .addBuildRequirement(new Resource(WOOD, 2))
                .addBuildRequirement(new Resource(STONE, 2))
                .setBuildTime(4)
                .build();
    }

    public static Building makeApartment() {
        return BuildingBuilder.builder()
                .setType(APARTMENT_BUILDING)
                .setNbHabitants(60)
                .setNbWorkers(0)
                .addBuildRequirement(new Resource(GOLD, 4))
                .addBuildRequirement(new Resource(WOOD, 50))
                .addBuildRequirement(new Resource(STONE, 50))
                .setBuildTime(6)
                .build();

    }

    public static Building makeFarm() {
        return BuildingBuilder.builder()
                .setType(FARM)
                .setNbHabitants(5)
                .setNbWorkers(3)
                .addBuildRequirement(new Resource(GOLD, 4))
                .addBuildRequirement(new Resource(WOOD, 5))
                .addBuildRequirement(new Resource(STONE, 5))
                .addProduction(new Resource(FOOD, 10))
                .setBuildTime(2)
                .build();
    }

    public static Building makeQuarry() {
        return BuildingBuilder.builder()
                .setType(QUARRY)
                .setNbHabitants(2)
                .setNbWorkers(30)
                .addBuildRequirement(new Resource(GOLD, 4))
                .addBuildRequirement(new Resource(WOOD, 50))
                .addProduction(new Resource(STONE, 4))
                .addProduction(new Resource(IRON, 4))
                .addProduction(new Resource(COAL, 4))
                .addProduction(new Resource(GOLD, 2))
                .setBuildTime(2)
                .build();
    }

    public static Building makeLumberMill() {
        return BuildingBuilder.builder()
                .setType(LUMBER_MILL)
                .setNbHabitants(0)
                .setNbWorkers(10)
                .addBuildRequirement(new Resource(GOLD, 6))
                .addBuildRequirement(new Resource(WOOD, 50))
                .addBuildRequirement(new Resource(STONE, 50))
                .addConsomation(new Resource(WOOD, 4))
                .addProduction(new Resource(LUMBER, 4))
                .setBuildTime(4)
                .build();
    }

    public static Building makeCementPlant() {
        return BuildingBuilder.builder()
                .setType(CEMENT_PLANT)
                .setNbHabitants(0)
                .setNbWorkers(10)
                .addBuildRequirement(new Resource(GOLD, 6))
                .addBuildRequirement(new Resource(WOOD, 50))
                .addBuildRequirement(new Resource(STONE, 50))
                .addConsomation(new Resource(STONE, 4))
                .addConsomation(new Resource(COAL, 4))
                .addProduction(new Resource(CEMENT, 4))
                .setBuildTime(4)
                .build();
    }

    public static Building makeSteelMill() {
        return BuildingBuilder.builder()
                .setType(STEEL_MILL)
                .setNbHabitants(0)
                .setNbWorkers(40)
                .addBuildRequirement(new Resource(GOLD, 6))
                .addBuildRequirement(new Resource(WOOD, 100))
                .addBuildRequirement(new Resource(STONE, 50))
                .addConsomation(new Resource(IRON, 4))
                .addConsomation(new Resource(COAL, 2))
                .addProduction(new Resource(STEEL, 2))
                .setBuildTime(6)
                .build();
    }

    public static Building makeToolFactory() {
        return BuildingBuilder.builder()
                .setType(TOOL_FACTORY)
                .setNbHabitants(0)
                .setNbWorkers(12)
                .addBuildRequirement(new Resource(GOLD, 8))
                .addBuildRequirement(new Resource(WOOD, 50))
                .addBuildRequirement(new Resource(STONE, 50))
                .addConsomation(new Resource(STEEL, 4))
                .addConsomation(new Resource(COAL, 4))
                .addProduction(new Resource(TOOLS, 4))
                .setBuildTime(8)
                .build();
    }
}
