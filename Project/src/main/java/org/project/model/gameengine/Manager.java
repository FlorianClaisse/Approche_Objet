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

    // TODO: Fix
    public boolean addBuilding(@NotNull Building.Type buildingType) {
        Building building;
        building = switch (buildingType) {
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

        if (!this.delegate.canConstruct(building.getBuildRequirements())) {
            return false;
        }

        this.underConstruction.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        this.delegate.removeFromStock(building.getBuildRequirements());
        return true;
    }

    public void consumeResources() {
        Set<Resource> toBeConsumed = new HashSet<>();

        for (Building building: this.constructedBuildings.values()) {

        }
    }

    public boolean removeExistingBuilding(int key) {
        return removeBuilding(false, key);
    }

    public boolean removeUnderBuilding(int key) {
        return removeBuilding(true, key);
    }

    public boolean addWorkers(int key, int value) {
        if (this.constructedBuildings.containsKey(key)) {
            Building building = this.constructedBuildings.get(key);
            if (building.canAddWorkers(value)) {
                building.addWorkers(value);
            }
        }
    }

    public boolean removeWorkers(int key, int value) {
        if (this.constructedBuildings.containsKey(key)) {
            Building building = this.constructedBuildings.get(key);
            if (building.canRemoveWorkers(value)) {
                building.removeWorkers(value);
                return true;
            }
            return false;
        }
        return false;
    }

    // TODO: Fix
    private boolean removeBuilding(boolean under, int key) {
        Map<Integer, Building> buildings = under ? this.underConstruction : this.constructedBuildings;
        if (buildings.containsKey(key)) {
            Building building = buildings.remove(key);

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
}
