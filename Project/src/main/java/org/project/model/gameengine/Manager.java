package org.project.model.gameengine;

import org.jetbrains.annotations.NotNull;
import org.project.model.building.Building;
import org.project.model.building.BuildingFactory;
import org.project.model.resource.Citizen;
import org.project.model.resource.Material;
import org.project.model.resource.Resource;
import org.project.model.resource.Resources;
import org.project.utils.Pair;

import java.util.*;

import static org.project.model.resource.Material.Type.FOOD;

public final class Manager {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings;
    private final Map<Integer, Building> underConstruction;
    private final Pair<Citizen, Integer> habitants;
    private final Pair<Citizen, Integer> workers;
    private final ManagerDelegate player;

    public Manager(@NotNull ManagerDelegate player) {
        this.constructedBuildings = new HashMap<>();
        this.underConstruction = new HashMap<>();
        this.habitants = new Pair<>(new Citizen(), 0);
        this.workers = new Pair<>(new Citizen(), 0);
        this.player = player;
    }

    public void update() {
        // FIXME: Attendre de faire le TODO
        //productResources();
        //consumeResources();

        List<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Building> entry: this.underConstruction.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();

            if (building.isBuilt()) {
                this.updateHabitants(building.getNbHabitants());
                this.updateWorkers(building.getCurrentWorkers());
                toBeRemoved.add(entry.getKey());
                this.constructedBuildings.put(BUILDING_COUNTER, building);
                BUILDING_COUNTER++;
            }
        }
        toBeRemoved.forEach(this.underConstruction::remove);
    }

    public boolean addBuilding(@NotNull Building.Type type) {
        Building building = getBuilding(type);
        // Si on ne possede pas assez d'habitant libre pour travailler.
        if ((this.freeInhabitant() + building.getNbHabitants()) - building.getMinWorkers() < 0) return false;
        // Le joueur doit verifier qu'il peut l'acheter et qu'il possède assez de resource
        if (!this.player.canBuy(building.getPrice()) ||
            !this.player.canConstruct(building.getBuildRequirements())
        ) return false;

        this.underConstruction.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        this.player.removeFromStock(building.getBuildRequirements());
        return true;
    }

    public boolean removeExistingBuilding(int key) {
        return removeBuilding(false, key);
    }

    public boolean removeUnderBuilding(int key) {
        return removeBuilding(true, key);
    }

    public boolean addWorkersToBuilding(int key, int quantity) {
        if (freeInhabitant() - quantity < 0) return false;

        if (this.constructedBuildings.containsKey(key)) {
            Building building = this.constructedBuildings.get(key);

            if (building.canAddWorkers(quantity)) {
                this.updateHabitants(-quantity);
                this.updateWorkers(quantity);
                building.addWorkers(quantity);
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean removeWorkersFromBuilding(int key, int quantity) {
        if (this.constructedBuildings.containsKey(key)) {
            Building building = this.constructedBuildings.get(key);
            if (building.canRemoveWorkers(quantity)) {
                this.updateHabitants(quantity);
                this.updateWorkers(-quantity);
                building.removeWorkers(quantity);
                return true;
            }
            return false;
        }
        return false;
    }

    private boolean removeBuilding(boolean under, int key) {
        Map<Integer, Building> buildings = under ? this.underConstruction : this.constructedBuildings;

        if (buildings.containsKey(key)) {
            Building oldBuilding = buildings.remove(key);
            this.updateHabitants(-(oldBuilding.getNbHabitants()));
            this.updateWorkers(-(oldBuilding.getCurrentWorkers()));

            if (under) {
                Resources resources = new Resources();
                oldBuilding.getBuildRequirements().forEach((r, q) -> resources.put(r, q / 2));
                this.player.addToStock(resources);
            }
            return true;
        }
        return false;
    }

    private int freeInhabitant() { return this.habitants.getSecond() - this.workers.getSecond(); }

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

    // TODO: Faire en sorte de stocker et de mettre à jour les resources a consomer et produire pour ne pas avoir a les calculer à chaque tour.
    /*private void consumeResources() {
        Resources toBeConsumed = new Resources();
        for (Building building: this.constructedBuildings.values()) {
            for (Map.Entry<Resource, Integer> entry: building.getBuildRequirements().entrySet()) {
                if (toBeConsumed.containsKey(entry.getKey())) {
                    toBeConsumed.put(entry.getKey(), entry.getValue() + toBeConsumed.get(entry.getKey()));
                } else {
                    toBeConsumed.put(entry.getKey(), entry.getValue());
                }
            }
        }
        // Consommation de resource par les habitants
        if (toBeConsumed.containsKey(new Material(FOOD))) {
            toBeConsumed.put(new Material(FOOD), toBeConsumed.get(new Material(FOOD)) + this.habitants.getSecond());
        } else {
            toBeConsumed.put(new Material(FOOD), this.habitants.getSecond());
        }
        this.player.removeFromStock(toBeConsumed);
    }

    private void productResources() {
        List<Resource> toBeProducted = new ArrayList<>();
        this.constructedBuildings.values().forEach(it -> toBeProducted.addAll(it.getProduction()));
        this.player.removeFromStock(toBeProducted);
    }*/

    private void updateHabitants(int value) {
        this.habitants.setSecond(this.habitants.getSecond() + value);
    }

    private void updateWorkers(int value) {
        this.workers.setSecond(this.workers.getSecond() + value);
    }
}
