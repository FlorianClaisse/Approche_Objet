package org.project.model.gameengine;

import org.jetbrains.annotations.NotNull;
import org.project.model.building.Building;
import org.project.model.resource.Citizen;
import org.project.model.resource.Material;
import org.project.model.resource.Resources;
import org.project.utils.Pair;
import org.project.utils.Quantity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.project.model.building.BuildingFactory.*;
import static org.project.model.resource.Material.Type.FOOD;

public final class City {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings =  new HashMap<>();
    private final Map<Integer, Building> underConstructionBuildings =  new HashMap<>();

    private final Pair<Citizen, Quantity> habitants = new Pair<>(new Citizen(), new Quantity());
    private final Pair<Citizen, Quantity> workers = new Pair<>(new Citizen(), new Quantity());

    private final Shop shop;
    private final ResourceUpdatable player;

    public City(@NotNull ResourceUpdatable player, Shop shop) {
        this.player = player;
        this.shop = shop;
    }

    public void dayEnd() {
        this.constructedBuildings.forEach((id, b) -> {
            this.player.addToStock(b.getProduction());
            this.player.removeFromStock(b.getConsumption());
        });

        for (Map.Entry<Integer, Building> entry: this.underConstructionBuildings.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();

            if (building.isBuilt()) {
                this.habitants.getSecond().add(building.getNbHabitants());
                this.workers.getSecond().add(building.getCurrentWorkers());

                this.underConstructionBuildings.remove(entry.getKey());
                this.constructedBuildings.put(BUILDING_COUNTER, building);
                BUILDING_COUNTER++;
            }
        }
    }

    public Resources getCurrentProduction() {
        Resources production = new Resources();
        for(Map.Entry<Integer, Building> entry: this.constructedBuildings.entrySet()) {
            Building building = entry.getValue();
            production.putAll(building.getProduction());
        }
        return production;
    }

    public Resources getCurrentConsumption() {
        Resources consumption = new Resources();
        for(Map.Entry<Integer, Building> entry: this.constructedBuildings.entrySet()) {
            Building building = entry.getValue();
            consumption.putAll(building.getConsumption());
        }
        consumption.get(FOOD).add(habitants.getSecond().get());
        return consumption;
    }

    public boolean purchaseBuilding(@NotNull Building.Type type) {
        Building building = this.makeBuilding(type);

        // Si il n'y a pas assez d'habitant libre pour travailler :
        if (this.freeInhabitant() + building.getNbHabitants() - building.getMinWorkers() < 0) return false;

        // Le player
        if (!this.shop.buyBuilding(building)) return false;

        this.underConstructionBuildings.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        return true;
    }

    public boolean removeExistingBuilding(int key) {
        if (!this.constructedBuildings.containsKey(key)) return false;
        this.habitants.getSecond().remove(this.constructedBuildings.get(key).getNbHabitants());
        this.workers.getSecond().remove(this.constructedBuildings.get(key).getCurrentWorkers());
        this.constructedBuildings.remove(key);
        return true;
    }

    public boolean removeUnderConstructionBuilding(int key) {
        if (!this.underConstructionBuildings.containsKey(key)) return false;
        this.constructedBuildings.remove(key);
        return true;
    }

    public boolean addWorkersToBuilding(int key, int quantity) {
        if (freeInhabitant() - quantity < 0) return false;
        if (!this.constructedBuildings.containsKey(key)) return false;

        Building building = this.constructedBuildings.get(key);
        if (!building.canAddWorkers(quantity)) return false;

        this.habitants.getSecond().remove(quantity);
        this.workers.getSecond().add(quantity);
        building.addWorkers(quantity);
        return true;
    }

    public boolean removeWorkersFromBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) return false;

        Building building = this.constructedBuildings.get(key);
        if (!building.canRemoveWorkers(quantity)) return false;

        this.habitants.getSecond().add(quantity);
        this.workers.getSecond().remove(quantity);
        building.removeWorkers(quantity);
        return true;
    }

    public Map<Integer, Building> getConstructedBuildings() {
        return this.constructedBuildings;
    }

    public Map<Integer, Building> getUnderConstructionBuildings() {
        return this.underConstructionBuildings;
    }

    private int freeInhabitant() { return this.habitants.getSecond().get() - this.workers.getSecond().get(); }

    private Building makeBuilding(Building.Type type) {
        return switch (type) {
            case APARTMENT_BUILDING -> makeApartment();
            case WOODEN_CABIN -> makeWoodenCabin();
            case TOOL_FACTORY -> makeToolFactory();
            case QUARRY -> makeQuarry();
            case CEMENT_PLANT -> makeCementPlant();
            case FARM -> makeFarm();
            case HOUSE -> makeHouse();
            case LUMBER_MILL -> makeLumberMill();
            case STEEL_MILL -> makeSteelMill();
        };
    }
}
