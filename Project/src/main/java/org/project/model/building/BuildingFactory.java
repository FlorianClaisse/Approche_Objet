package org.project.model.building;

import org.project.model.resource.Gold;
import org.project.model.resource.Material;

import static org.project.model.building.Building.Type.*;
import static org.project.model.resource.Material.Type.*;


public class BuildingFactory {

    public static Building makeWoodenCabin() {
        return BuildingBuilder.builder()
                .setType(WOODEN_CABIN)
                .setMinHabitants(2)
                .setMinWorkers(2)
                .setPrice(1)
                .addBuildRequirement(new Material(WOOD), 1)
                .addProduction(new Material(WOOD), 2)
                .addProduction(new Material(FOOD), 2)
                .setBuildTime(2)
                .build();
    }

    public static Building makeHouse() {
        return BuildingBuilder.builder()
                .setType(HOUSE)
                .setMinHabitants(4)
                .setMinWorkers(0)
                .setPrice(1)
                .addBuildRequirement(new Material(WOOD), 2)
                .addBuildRequirement(new Material(STONE), 2)
                .setBuildTime(4)
                .build();
    }

    public static Building makeApartment() {
        return BuildingBuilder.builder()
                .setType(APARTMENT_BUILDING)
                .setMinHabitants(60)
                .setMinWorkers(0)
                .setPrice(4)
                .addBuildRequirement(new Material(WOOD), 50)
                .addBuildRequirement(new Material(STONE), 50)
                .setBuildTime(6)
                .build();

    }

    public static Building makeFarm() {
        return BuildingBuilder.builder()
                .setType(FARM)
                .setMinHabitants(5)
                .setMinWorkers(3)
                .setPrice(4)
                .addBuildRequirement(new Material(WOOD), 5)
                .addBuildRequirement(new Material(STONE), 5)
                .addProduction(new Material(FOOD), 10)
                .setBuildTime(2)
                .build();
    }

    public static Building makeQuarry() {
        return BuildingBuilder.builder()
                .setType(QUARRY)
                .setMinHabitants(2)
                .setMinWorkers(30)
                .setPrice(4)
                .addBuildRequirement(new Material(WOOD), 50)
                .addProduction(new Material(STONE), 4)
                .addProduction(new Material(IRON), 4)
                .addProduction(new Material(COAL), 4)
                .addProduction(new Gold(), 2)
                .setBuildTime(2)
                .build();
    }

    public static Building makeLumberMill() {
        return BuildingBuilder.builder()
                .setType(LUMBER_MILL)
                .setMinHabitants(0)
                .setMinWorkers(10)
                .setPrice(6)
                .addBuildRequirement(new Material(WOOD), 50)
                .addBuildRequirement(new Material(STONE), 50)
                .addConsomation(new Material(WOOD), 4)
                .addProduction(new Material(LUMBER), 4)
                .setBuildTime(4)
                .build();
    }

    public static Building makeCementPlant() {
        return BuildingBuilder.builder()
                .setType(CEMENT_PLANT)
                .setMinHabitants(0)
                .setMinWorkers(10)
                .setPrice(6)
                .addBuildRequirement(new Material(WOOD), 50)
                .addBuildRequirement(new Material(STONE), 50)
                .addConsomation(new Material(STONE), 4)
                .addConsomation(new Material(COAL), 4)
                .addProduction(new Material(CEMENT), 4)
                .setBuildTime(4)
                .build();
    }

    public static Building makeSteelMill() {
        return BuildingBuilder.builder()
                .setType(STEEL_MILL)
                .setMinHabitants(0)
                .setMinWorkers(40)
                .setPrice(6)
                .addBuildRequirement(new Material(WOOD), 100)
                .addBuildRequirement(new Material(STONE), 50)
                .addConsomation(new Material(IRON), 4)
                .addConsomation(new Material(COAL), 2)
                .addProduction(new Material(STEEL), 4)
                .setBuildTime(6)
                .build();
    }

    public static Building makeToolFactory() {
        return BuildingBuilder.builder()
                .setType(TOOL_FACTORY)
                .setMinHabitants(0)
                .setMinWorkers(12)
                .setPrice(8)
                .addBuildRequirement(new Material(WOOD), 50)
                .addBuildRequirement(new Material(STONE), 50)
                .addConsomation(new Material(STEEL), 4)
                .addConsomation(new Material(COAL), 4)
                .addProduction(new Material(TOOLS), 4)
                .setBuildTime(8)
                .build();
    }
}
