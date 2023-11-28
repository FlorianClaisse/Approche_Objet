package org.project.model.gameengine;

import org.jetbrains.annotations.NotNull;
import org.project.model.building.Building;
import org.project.model.building.BuildingFactory;
import org.project.model.resource.Citizen;
import org.project.model.resource.Material;
import org.project.model.resource.Resource;
import org.project.utils.Result;

import java.util.*;

import static org.project.model.resource.Material.Type.FOOD;

public final class Manager {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings;
    private final Map<Integer, Building> underConstruction;
    private final Citizen habitants;
    private final Citizen workers;
    private final ManagerDelegate delegate;

    public Manager(@NotNull ManagerDelegate delegate) {
        this.constructedBuildings = new HashMap<>();
        this.underConstruction = new HashMap<>();
        this.habitants = new Citizen(0);
        this.workers = new Citizen(0);
        this.delegate = delegate;
    }

    public void update() {
        productResources();
        consumeResources();

        List<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Building> entry: this.underConstruction.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();
            if (building.isBuilt()) {
                this.habitants.addQuantity(building.getNbHabitants());
                this.workers.addQuantity(building.getCurrentWorkers());
                toBeRemoved.add(entry.getKey());
                this.constructedBuildings.put(BUILDING_COUNTER, building);
                BUILDING_COUNTER++;
            }
        }
        toBeRemoved.forEach(this.underConstruction::remove);
    }

    // TODO: Fix le changer de tableau une fois construit
    public boolean addBuilding(@NotNull Building.Type type) {
        Building building = getBuilding(type);
        // Si on ne possede pas assez d'habitant libre pour travailler.
        if ((this.freeInhabitant() + building.getNbHabitants()) - building.getMinWorkers() < 0) return false;
        // Le delegate doit verifier qu'il possede assez de resource
        if (!this.delegate.canConstruct(building.getBuildRequirements())) return false;

        this.underConstruction.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        this.delegate.removeFromStock(building.getBuildRequirements());
        return true;
    }

    public boolean removeExistingBuilding(int key) {
        return removeBuilding(false, key);
    }

    public boolean removeUnderBuilding(int key) {
        return removeBuilding(true, key);
    }

    public boolean addWorkers(int key, int value) {
        if (freeInhabitant() - value < 0) return false;
        if (this.constructedBuildings.containsKey(key)) {
            Building building = this.constructedBuildings.get(key);
            if (building.canAddWorkers(value)) {
                this.habitants.removeQuantity(value);
                this.workers.addQuantity(value);
                building.addWorkers(value);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean removeWorkers(int key, int value) {
        if (this.constructedBuildings.containsKey(key)) {
            Building building = this.constructedBuildings.get(key);
            if (building.canRemoveWorkers(value)) {
                this.habitants.addQuantity(value);
                this.workers.removeQuantity(value);
                building.removeWorkers(value);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean removeBuilding(boolean under, int key) {
        Map<Integer, Building> buildings = under ? this.underConstruction : this.constructedBuildings;
        if (buildings.containsKey(key)) {
            Building building = buildings.remove(key);
            this.habitants.removeQuantity(building.getNbHabitants());
            this.workers.removeQuantity(building.getCurrentWorkers());
            if (under) {
                List<Resource> resources = new ArrayList<>();
                building.getBuildRequirements().forEach(it -> {
                    it.removeQuantity(it.getQuantity() / 2);
                });
                this.delegate.addToStock(resources);
            }
            return true;
        }
        return false;
    }

    private int freeInhabitant() { return this.habitants.getQuantity() - this.workers.getQuantity(); }

    private Building getBuilding(Building.Type type) {
        return switch (type) {
            case FARM -> BuildingFactory.makeFarm();
            case HOUSE -> BuildingFactory.makeHouse();
            case QUARRY -> BuildingFactory.makeQuarry();
            case STEEL_MILL -> BuildingFactory.makeSteelMill();
            case LUMBER_MILL -> BuildingFactory.makeLumberMill();
            case CEMENT_PLANT -> BuildingFactory.makeCementPlant();
            case TOOL_FACTORY -> BuildingFactory.makeToolFactory();
            case APARTMENT_BUILDING -> BuildingFactory.makeApartment();
            case WOODEN_CABIN -> BuildingFactory.makeWoodenCabin();
        };
    }

    private void consumeResources() {
        List<Resource> toBeConsumed = new ArrayList<>();
        this.constructedBuildings.values().forEach(it -> toBeConsumed.addAll(it.getConsomation()));
        // Consommation de resource par les habitants
        toBeConsumed.add(new Material(FOOD, this.habitants.getQuantity()));
        this.delegate.removeFromStock(toBeConsumed);
    }

    private void productResources() {
        List<Resource> toBeProducted = new ArrayList<>();
        this.constructedBuildings.values().forEach(it -> toBeProducted.addAll(it.getProduction()));
        this.delegate.removeFromStock(toBeProducted);
    }
}
