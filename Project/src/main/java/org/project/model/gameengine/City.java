package org.project.model.gameengine;

import org.jetbrains.annotations.NotNull;
import org.project.model.building.Building;
import org.project.model.building.BuildingFactory;
import org.project.model.resource.Citizen;
import org.project.model.resource.Resources;
import org.project.utils.Pair;
import org.project.utils.Quantity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.project.model.building.BuildingFactory.*;
import static org.project.model.resource.ResourceFactory.food;

public final class City {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings =  new HashMap<>();
    private final Map<Integer, Building> underConstructionBuildings =  new HashMap<>();

    private final Pair<Citizen, Quantity> habitants = new Pair<>(new Citizen(), new Quantity());
    private final Pair<Citizen, Quantity> workers = new Pair<>(new Citizen(), new Quantity());

    private final Shop shop;
    private final ResourceManager player;

    public City(@NotNull ResourceManager player, Shop shop) {
        this.player = player;
        this.shop = shop;
    }

    public void dayEnd() {
        this.constructedBuildings.forEach((id, b) -> {
            this.player.addToStock(b.getProduction());
            this.player.removeFromStock(b.getConsumption());
        });
        Resources habitantsConsumption = new Resources();
        habitantsConsumption.initWithAllResources();
        habitantsConsumption.get(food()).add(habitants.getSecond().get());
        this.player.removeFromStock(habitantsConsumption);

        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Building> entry: this.underConstructionBuildings.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();

            if (building.isBuilt()) {
                this.habitants.getSecond().add(building.getNbHabitants());
                this.workers.getSecond().add(building.getCurrentWorkers());

                this.constructedBuildings.put(BUILDING_COUNTER, building);
                BUILDING_COUNTER++;

                toBeRemoved.add(entry.getKey());
            }
        }
        toBeRemoved.forEach(this.underConstructionBuildings::remove);
    }

    public boolean isWinning() {
        for (Building building: this.constructedBuildings.values()) {
            if (building.isMaxLevel() && building.getTypeName().equals(BuildingFactory.makeToolFactory().getTypeName()))
                return true;
        }
        return false;
    }

    public Resources getCurrentProduction() {
        Resources production = new Resources();
        production.initWithAllResources();
        for(Map.Entry<Integer, Building> entry: this.constructedBuildings.entrySet()) {
            Building building = entry.getValue();
            building.getProduction().forEach((r, q) -> production.get(r).add(q.get()));
        }
        return production;
    }

    public Resources getCurrentConsumption() {
        Resources consumption = new Resources();
        consumption.initWithAllResources();
        for(Map.Entry<Integer, Building> entry: this.constructedBuildings.entrySet()) {
            Building building = entry.getValue();
            building.getConsumption().forEach((r, q) -> consumption.get(r).add(q.get()));
        }
        consumption.get(food()).add(habitants.getSecond().get());
        return consumption;
    }

    public boolean purchaseBuilding(@NotNull Building.Type type) {
        Building building = this.makeBuilding(type);

        // Il doit y avoir assez d'habitants libres pour travailler
        if (this.freeInhabitant() + building.getNbHabitants() - building.getMinWorkers() < 0) return false;

        // Le player doit avoir assez de ressources
        if (!this.shop.buyBuilding(building)) return false;

        this.underConstructionBuildings.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        return true;
    }

    public boolean upgradeBuilding(int key) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");
        Building building = this.constructedBuildings.get(key);
        if (!this.player.haveEnoughResources(building.getUpdateRequirements())) return false;
        if (!building.canBeUpgraded()) return false;

        this.player.removeFromStock(building.getUpdateRequirements());
        building.upgrade();
        return true;
    }

    public void removeExistingBuilding(int key) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");
        this.habitants.getSecond().remove(this.constructedBuildings.get(key).getNbHabitants());
        this.workers.getSecond().remove(this.constructedBuildings.get(key).getCurrentWorkers());
        this.constructedBuildings.remove(key);
    }

    public void removeUnderConstructionBuilding(int key) {
        if (!this.underConstructionBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");
        this.constructedBuildings.remove(key);
    }

    public boolean addWorkersToBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        // Il doit y avoir assez d'habitants qui ne travaillent pas déjà
        if (freeInhabitant() - quantity < 0) return false;

        // Il ne doit pas y avoir plus de travailleurs dans le building que son maximum
        Building building = this.constructedBuildings.get(key);
        if (!building.canAddWorkers(quantity)) return false;

        this.workers.getSecond().add(quantity);
        building.addWorkers(quantity);
        return true;
    }

    public boolean removeWorkersFromBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        Building building = this.constructedBuildings.get(key);
        if (!building.canRemoveWorkers(quantity)) return false;

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

    public int getNbHabitants() {
        return this.habitants.getSecond().get();
    }

    public int getNbWorkers() {
        return this.workers.getSecond().get();
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
