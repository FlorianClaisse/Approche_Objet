package org.project.model.gameengine;

import org.jetbrains.annotations.NotNull;
import org.project.model.building.Building;
import org.project.model.building.BuildingFactory;
import org.project.model.resource.Citizen;
import org.project.model.resource.Material;
import org.project.model.resource.Resources;
import org.project.utils.Pair;
import org.project.utils.Quantity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.project.model.resource.Material.Type.FOOD;

public final class City {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> constructedBuildings;
    private final Resources beConsumed;
    private final Resources beProducted;
    private final Map<Integer, Building> underConstruction;

    private final Pair<Citizen, Quantity> habitants;
    private final Pair<Citizen, Quantity> workers;

    private final CityDelegate player;

    public City(@NotNull CityDelegate player) {
        this.constructedBuildings = new HashMap<>();
        this.beConsumed = new Resources();
        this.beConsumed.initWithAllResources();
        this.beProducted = new Resources();
        this.beProducted.initWithAllResources();
        this.underConstruction = new HashMap<>();
        this.habitants = new Pair<>(new Citizen(), new Quantity());
        this.workers = new Pair<>(new Citizen(), new Quantity());
        this.player = player;
    }

    public void dayEnd() {
        this.player.addToStock(this.beProducted);
        this.player.removeFromStock(this.beConsumed);

        List<Integer> toBeRemoved = new ArrayList<>();
        for (Map.Entry<Integer, Building> entry: this.underConstruction.entrySet()) {
            Building building = entry.getValue();
            building.removeOnePeriod();

            // Quand un édifice est fini de construire, il ne consomme des resources qu'au tour d'après.
            if (building.isBuilt()) {
                this.updateHabitants(building.getNbHabitants());
                this.updateWorkers(building.getCurrentWorkers());

                toBeRemoved.add(entry.getKey());
                this.constructedBuildings.put(BUILDING_COUNTER, building);
                BUILDING_COUNTER++;

                this.beConsumed.get(new Material(FOOD)).add(building.getNbHabitants());
                building.getConsumption().forEach((r, q) -> this.beConsumed.get(r).add(q.get()));
                building.getProduction().forEach((r, q) -> this.beProducted.get(r).add(q.get()));
            }
        }
        toBeRemoved.forEach(this.underConstruction::remove);
    }

    public boolean addBuilding(@NotNull Building.Type type) {
        Building building = this.getBuilding(type);
        // Si on ne possede pas assez d'habitant libre pour travailler.
        if ((this.freeInhabitant() + building.getNbHabitants()) - building.getMinWorkers() < 0) return false;
        // Le joueur doit verifier qu'il peut l'acheter et qu'il possède assez de resource
        if (!this.player.canBuy(building.getPrice()) ||
            !this.player.canConstruct(building.getBuildRequirements())
        ) return false;

        this.underConstruction.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        this.player.removeFromStock(building.getBuildRequirements());
        this.player.buy(building.getPrice());
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
        if (!this.constructedBuildings.containsKey(key)) return false;

        Building building = this.constructedBuildings.get(key);
        if (!building.canAddWorkers(quantity)) return false;

        this.updateHabitants(-quantity);
        this.updateWorkers(quantity);
        building.addWorkers(quantity);
        return true;
    }

    public boolean removeWorkersFromBuilding(int key, int quantity) {
        if (!this.constructedBuildings.containsKey(key)) return false;

        Building building = this.constructedBuildings.get(key);
        if (!building.canRemoveWorkers(quantity)) return false;

        this.updateHabitants(quantity);
        this.updateWorkers(-quantity);
        building.removeWorkers(quantity);
        return true;
    }

    public boolean gameIsOver() {
        return this.player.isInShortage();
    }

    public Map<Integer, Building> getConstructedBuildings() {
        return this.constructedBuildings;
    }

    public Map<Integer, Building> getUnderConstruction() {
        return this.underConstruction;
    }

    public Resources getBeConsumed() {
        return this.beConsumed;
    }

    public Resources getBeProducted() {
        return this.beProducted;
    }

    private boolean removeBuilding(boolean under, int key) {
        Map<Integer, Building> buildings = under ? this.underConstruction : this.constructedBuildings;

        if (!buildings.containsKey(key)) return false;

        Building oldBuilding = buildings.remove(key);
        this.updateHabitants(-(oldBuilding.getNbHabitants()));
        this.updateWorkers(-(oldBuilding.getCurrentWorkers()));
        if (!under) {
            oldBuilding.getConsumption().forEach((r, q) -> this.beConsumed.get(r).remove(q.get()));
            oldBuilding.getProduction().forEach((r, q) -> this.beProducted.get(r).remove(q.get()));
        }
        this.beConsumed.get(new Material(FOOD)).remove(oldBuilding.getNbHabitants());


        if (under) {
            // Le player récupère 50% du coup de construction du building
            Resources resources = new Resources();
            System.out.println(oldBuilding.getBuildRequirements());
            oldBuilding.getBuildRequirements().forEach((r, q) -> resources.put(r, new Quantity(q.get() / 2)));
            System.out.println(resources);
            this.player.addToStock(resources);
        }

        return true;
    }

    private int freeInhabitant() { return this.habitants.getSecond().get() - this.workers.getSecond().get(); }

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

    private void updateHabitants(int value) {
        if (value < 0)
            this.habitants.getSecond().remove(-value);
        else
            this.habitants.getSecond().add(value);
    }

    private void updateWorkers(int value) {
        if (value < 0)
            this.workers.getSecond().remove(-value);
        else
            this.workers.getSecond().add(value);
    }
}
