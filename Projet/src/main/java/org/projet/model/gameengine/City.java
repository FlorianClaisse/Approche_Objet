package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.building.BuildingFactory;
import org.projet.model.resource.Resources;
import org.projet.utils.Quantity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.projet.model.building.BuildingFactory.*;
import static org.projet.model.resource.ResourceFactory.food;

public final class City {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings =  new HashMap<>();
    private final Map<Integer, Building> underConstructionBuildings =  new HashMap<>();

    private final Quantity habitants = new Quantity();
    private final Quantity futureHabitants = new Quantity();


    private final Quantity futureWorkers = new Quantity();
    private final Quantity workers = new Quantity();

    private final Shop shop;
    private final ResourceManager player;

    public City(ResourceManager player, Shop shop) {
        this.player = player;
        this.shop = shop;
    }

    public Map<Integer, Building> getConstructedBuildings() {
        return this.constructedBuildings;
    }
    public Map<Integer, Building> getUnderConstructionBuildings() {
        return this.underConstructionBuildings;
    }
    public int getNbHabitants() {
        return this.habitants.get();
    }
    public int getNbFutureHabitants() {
        return this.futureHabitants.get();
    }
    public int getNbFutureWorkers() {
        return this.futureWorkers.get();
    }
    public int getNbWorkers() {
        return this.workers.get();
    }
    private int getFreeHabitants() {
        return (this.habitants.get() + this.futureHabitants.get()) - (this.futureWorkers.get() + this.workers.get());
    }

    public void dayEnd() {
        this.constructedBuildings.forEach((id, b) -> {
            this.player.addToStock(b.getProduction());
            this.player.removeFromStock(b.getConsumption());
        });
        Resources habitantsConsumption = new Resources();
        habitantsConsumption.initWithAllResources();
        habitantsConsumption.get(food()).add(habitants.get());
        this.player.removeFromStock(habitantsConsumption);

        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Building> entry: this.underConstructionBuildings.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();

            if (building.isBuilt()) {
                this.habitants.add(building.getNbHabitants());
                this.futureHabitants.remove(building.getNbHabitants());
                this.futureWorkers.remove(building.getCurrentWorkers());
                this.workers.add(building.getCurrentWorkers());

                this.constructedBuildings.put(BUILDING_COUNTER, building);
                BUILDING_COUNTER++;

                toBeRemoved.add(entry.getKey());
            }
        }
        toBeRemoved.forEach(this.underConstructionBuildings::remove);
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
        consumption.get(food()).add(habitants.get());
        return consumption;
    }

    public boolean purchaseBuilding(Building.Type type) {
        Building building = this.makeBuilding(type);

        // Il doit y avoir assez d'habitants libres pour travailler
        if (this.getFreeHabitants() + building.getNbHabitants() - building.getMinWorkers() < 0) return false;

        // Le player doit avoir assez de ressources
        if (!this.shop.buyBuilding(building)) return false;

        this.futureHabitants.add(building.getNbHabitants());
        this.futureWorkers.add(building.getMinWorkers());
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
        Building building = this.constructedBuildings.get(key);

        this.habitants.remove(building.getNbHabitants());
        this.workers.remove(building.getCurrentWorkers());

        // Lorsqu'un bâtiment est détruit le player récupère 1/4 des matériaux utilisés pour le construire
        Resources buildingRequirements = building.getBuildRequirements();
        Resources recoveredMaterials = new Resources();
        recoveredMaterials.initWithAllResources();
        buildingRequirements.forEach((r, q) -> recoveredMaterials.get(r).add(q.get() / 4));
        this.player.addToStock(recoveredMaterials);

        this.constructedBuildings.remove(key);
    }

    public void removeUnderConstructionBuilding(int key) {
        if (!this.underConstructionBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        // Lorsqu'un bâtiment en cours de construction est détruit le player récupère 1/2 des matériaux utilisés pour le construire
        Resources buildingRequirements = this.underConstructionBuildings.get(key).getBuildRequirements();
        Resources recoveredMaterials = new Resources();
        recoveredMaterials.initWithAllResources();
        buildingRequirements.forEach((r, q) -> recoveredMaterials.get(r).add(q.get() / 2));
        this.player.addToStock(recoveredMaterials);

        this.underConstructionBuildings.remove(key);
    }

    public boolean addWorkersToBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        // Il doit y avoir assez d'habitants qui ne travaillent pas déjà
        if (getFreeHabitants() - quantity < 0) return false;

        // Il ne doit pas y avoir plus de travailleurs dans le building que son maximum
        Building building = this.constructedBuildings.get(key);
        if (!building.canAddWorkers(quantity)) return false;

        this.workers.add(quantity);
        building.addWorkers(quantity);
        return true;
    }

    public boolean removeWorkersFromBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        Building building = this.constructedBuildings.get(key);
        if (!building.canRemoveWorkers(quantity)) return false;

        this.workers.remove(quantity);
        building.removeWorkers(quantity);
        return true;
    }

    public boolean isWinning() {
        for (Building building : this.constructedBuildings.values()) {
            if (building.getType().equals(Building.Type.LAUNCHING_ROCKET_PLATFORM) && building.isMaxLevel() && building.getCurrentWorkers() == building.getMaxWorkers())
                return true;
        }
        return false;
    }

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
            case LAUNCHING_ROCKET_PLATFORM -> makeLaunchingRocketPlatform();
        };
    }
}
