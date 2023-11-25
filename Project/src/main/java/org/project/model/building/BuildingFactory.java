package org.project.model.building;

import org.project.model.resource.Gold;
import org.project.model.resource.Material;

import static org.project.model.building.Building.Type.*;
import static org.project.model.resource.Material.Type.*;


public class BuildingFactory {

    public static Building makeWoodenCabin() {
        return BuildingBuilder.builder()
                .setType(WOODEN_CABIN)
                .setNbHabitants(2)
                .setNbWorkers(2)
                .setPrice(1)
                .addBuildRequirement(new Material(WOOD, 1))
                .addProduction(new Material(WOOD, 2))
                .addProduction(new Material(FOOD, 2))
                .setBuildTime(2)
                .build();
    }

    public static Building makeHouse() {
        return BuildingBuilder.builder()
                .setType(HOUSE)
                .setNbHabitants(4)
                .setNbWorkers(0)
                .setPrice(1)
                .addBuildRequirement(new Material(WOOD, 2))
                .addBuildRequirement(new Material(STONE, 2))
                .setBuildTime(4)
                .build();
    }

    public static Building makeApartment() {
        return BuildingBuilder.builder()
                .setType(APARTMENT_BUILDING)
                .setNbHabitants(60)
                .setNbWorkers(0)
                .setPrice(4)
                .addBuildRequirement(new Material(WOOD, 50))
                .addBuildRequirement(new Material(STONE, 50))
                .setBuildTime(6)
                .build();

    }

    public static Building makeFarm() {
        return BuildingBuilder.builder()
                .setType(FARM)
                .setNbHabitants(5)
                .setNbWorkers(3)
                .setPrice(4)
                .addBuildRequirement(new Material(WOOD, 5))
                .addBuildRequirement(new Material(STONE, 5))
                .addProduction(new Material(FOOD, 10))
                .setBuildTime(2)
                .build();
    }

    public static Building makeQuarry() {
        return BuildingBuilder.builder()
                .setType(QUARRY)
                .setNbHabitants(2)
                .setNbWorkers(30)
                .setPrice(4)
                .addBuildRequirement(new Material(WOOD, 50))
                .addProduction(new Material(STONE, 4))
                .addProduction(new Material(IRON, 4))
                .addProduction(new Material(COAL, 4))
                .addProduction(new Gold(2))
                .setBuildTime(2)
                .build();
    }

    public static Building makeLumberMill() {
        return BuildingBuilder.builder()
                .setType(LUMBER_MILL)
                .setNbHabitants(0)
                .setNbWorkers(10)
                .setPrice(6)
                .addBuildRequirement(new Material(WOOD, 50))
                .addBuildRequirement(new Material(STONE, 50))
                .addConsomation(new Material(WOOD, 4))
                .addProduction(new Material(LUMBER, 4))
                .setBuildTime(4)
                .build();
    }

    public static Building makeCementPlant() {
        return BuildingBuilder.builder()
                .setType(CEMENT_PLANT)
                .setNbHabitants(0)
                .setNbWorkers(10)
                .setPrice(6)
                .addBuildRequirement(new Material(WOOD, 50))
                .addBuildRequirement(new Material(STONE, 50))
                .addConsomation(new Material(STONE, 4))
                .addConsomation(new Material(COAL, 4))
                .addProduction(new Material(CEMENT, 4))
                .setBuildTime(4)
                .build();
    }

    public static Building makeSteelMill() {
        return BuildingBuilder.builder()
                .setType(STEEL_MILL)
                .setNbHabitants(0)
                .setNbWorkers(40)
                .setPrice(6)
                .addBuildRequirement(new Material(WOOD, 100))
                .addBuildRequirement(new Material(STONE, 50))
                .addConsomation(new Material(IRON, 4))
                .addConsomation(new Material(COAL, 2))
                .addProduction(new Material(STEEL, 2))
                .setBuildTime(6)
                .build();
    }

    public static Building makeToolFactory() {
        return BuildingBuilder.builder()
                .setType(TOOL_FACTORY)
                .setNbHabitants(0)
                .setNbWorkers(12)
                .setPrice(8)
                .addBuildRequirement(new Material(WOOD, 50))
                .addBuildRequirement(new Material(STONE, 50))
                .addConsomation(new Material(STEEL, 4))
                .addConsomation(new Material(COAL, 4))
                .addProduction(new Material(TOOLS, 4))
                .setBuildTime(8)
                .build();
    }
}
