package org.projet.model.gameengine;

import org.projet.model.building.Building;
import org.projet.model.building.BuildingFactory;
import org.projet.model.resource.Citizens;
import org.projet.model.resource.Material;
import org.projet.model.resource.Resources;
import org.projet.utils.Quantity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.projet.model.building.BuildingFactory.*;
import static org.projet.model.resource.ResourceFactory.food;

/**
 * The city of the game with its buildings, its habitants, its shop and its manager (the player)
 */
public final class City {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings =  new HashMap<>();
    private final Map<Integer, Building> underConstructionBuildings =  new HashMap<>();

    private final Citizens habitants = new Citizens();
    private final Quantity futureHabitants = new Quantity();
    private final Quantity workers = new Quantity();
    private final Quantity futureWorkers = new Quantity();

    private final ShopBuyer manager;
    private final Shop shop;

    /**
     * @param manager The manager of resources
     */
    public City(ShopBuyer manager) {
        this.manager = manager;
        this.manager.subscribe(habitants); // Habitants --> ResourcesConsumer
        this.shop = new Shop();
    }

    /**
     * The end of one day in the city,
     * making the player collect and provide resources,
     * decremeting by one unit the construction time of all the buildings under construction,
     * moving an under construction building to the constructed building list if its construction time is finished
     */
    public void dayEnd() {
        manager.collectProduction();
        manager.provideConsumption();

        ArrayList<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Building> entry: this.underConstructionBuildings.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();

            if (building.isBuilt()) {
                this.manager.subscribe(building);
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

    /**
     * @return the resources produced by all the constructed buildings
     */
    public Resources getCurrentProduction() {
        Resources production = new Resources();
        production.initWithAllResources();
        for(Map.Entry<Integer, Building> entry: this.constructedBuildings.entrySet()) {
            Building building = entry.getValue();
            building.getProduction().forEach((r, q) -> production.get(r).add(q.get()));
        }
        return production;
    }

    /**
     * @return the resources consumed by all the constructed buildings and the citizens of the city
     */
    public Resources getCurrentConsumption() {
        Resources consumption = new Resources();
        consumption.initWithAllResources();
        for(Map.Entry<Integer, Building> entry: this.constructedBuildings.entrySet()) {
            Building building = entry.getValue();
            building.getConsumption().forEach((r, q) -> consumption.get(r).add(q.get()));
        }
        consumption.get(food()).add(habitants.getNumber());
        return consumption;
    }

    /**
     * Let the player buy materials from the city shop
     * @param type The type of the material
     * @param quantity The quantity of the material
     * @return true if the player was able to buy the materials from the shop
     */
    public boolean buyMaterials(Material.Type type, int quantity) {
        return shop.buyMaterials(manager, type, quantity);
    }

    /**
     * Put a new build under construction in the city
     * @param type The type of the new building
     * @return false if the number of free habitants from the city + the number of habitants created by the building is inferior to its minimum workers number
     * @return false if the player can't buy the building
     */
    public boolean buildNewBuilding(Building.Type type) {
        Building building = makeBuilding(type);

        // Il doit y avoir assez d'habitants libres pour travailler
        if (this.getFreeHabitants() + building.getNbHabitants() - building.getMinWorkers() < 0) return false;

        // Le player doit avoir assez de ressources (matériaux et argent)
        if (!this.shop.buyBuilding(manager, building)) return false;

        this.futureHabitants.add(building.getNbHabitants());
        this.futureWorkers.add(building.getMinWorkers());
        this.underConstructionBuildings.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        return true;
    }

    /**
     * Try to upgrade a constructed building
     * @param key The index of a constructed building in the city
     * @return false if the player don't have enough resources to upgrade the building
     * @return false if the building can't be upgraded further
     */
    public boolean upgradeBuilding(int key) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");
        Building building = this.constructedBuildings.get(key);
        if (!this.manager.haveEnoughResources(building.getUpdateRequirements())) return false;
        if (!building.canBeUpgraded()) return false;

        this.manager.removeFromStock(building.getUpdateRequirements());
        building.upgrade();
        return true;
    }

    /**
     * Remove a constructed building
     * @param key The index of a constructed building in the city
     */
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
        this.manager.addToStock(recoveredMaterials);

        this.constructedBuildings.remove(key);
    }

    /**
     * Remove a building under construction
     * @param key The index of a building under construction in the city
     */
    public void removeUnderConstructionBuilding(int key) {
        if (!this.underConstructionBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        // Lorsqu'un bâtiment en cours de construction est détruit le player récupère 1/2 des matériaux utilisés pour le construire
        Resources buildingRequirements = this.underConstructionBuildings.get(key).getBuildRequirements();
        Resources recoveredMaterials = new Resources();
        recoveredMaterials.initWithAllResources();
        buildingRequirements.forEach((r, q) -> recoveredMaterials.get(r).add(q.get() / 2));
        this.manager.addToStock(recoveredMaterials);

        this.underConstructionBuildings.remove(key);
    }

    /**
     * Try to add workers to a constructed building
     * @param key The index of a constructed building in the city
     * @param quantity The quantity of workers to add
     * @return false if the number of free habitants in the city is inferior to the given quantity
     * @return false if the quantity given does not respect the maximum number of workers to respect in the building
     */
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

    /**
     * Try to remove workers from a constructed building
     * @param key The index of a constructed building in the city
     * @param quantity The quantity of workers to remove
     * @return false if the removing the given quantity does not respect the minimum number of workers needed in the building
     */
    public boolean removeWorkersFromBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) throw new IllegalStateException("Can't find building with given key");

        Building building = this.constructedBuildings.get(key);
        if (!building.canRemoveWorkers(quantity)) return false;

        this.workers.remove(quantity);
        building.removeWorkers(quantity);
        return true;
    }

    /**
     * @return the constructed buildings in the city
     */
    public Map<Integer, Building> getConstructedBuildings() {
        return new HashMap<>(this.constructedBuildings);
    }
    /**
     * @return the buildings under construction in the city
     */
    public Map<Integer, Building> getUnderConstructionBuildings() {
        return new HashMap<>(this.underConstructionBuildings);
    }
    /**
     * @return the total number of habitants in the city
     */
    public int getNbHabitants() {
        return this.habitants.getNumber();
    }
    /**
     * @return the number of future habitants "coming" to the city with the construction of some buildings
     */
    public int getNbFutureHabitants() {
        return this.futureHabitants.get();
    }
    /**
     * @return the current number of habitants working in buildings
     */
    public int getNbWorkers() {
        return this.workers.get();
    }
    /**
     * @return the number of current/future habitants that are going to work with the construction of some buildings
     */
    public int getNbFutureWorkers() {
        return this.futureWorkers.get();
    }
    /**
     * @return the number of habitants and future habitants not working/going to work
     */
    private int getFreeHabitants() {
        return (this.habitants.getNumber() + this.futureHabitants.get()) - (this.futureWorkers.get() + this.workers.get());
    }

    /**
     * @return true if there is a launching rocket platform fully upgraded and with the maximum number of workers currently working
     */
    public boolean isWinning() {
        for (Building building : this.constructedBuildings.values()) {
            if (building.getType().equals(Building.Type.LAUNCHING_ROCKET_PLATFORM) && building.isMaxLevel() && building.getCurrentWorkers() == building.getMaxWorkers())
                return true;
        }
        return false;
    }

    /**
     * Use the static BuildingFactory to make a building
     * @param type The type of the building to make
     * @return the building
     */
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
