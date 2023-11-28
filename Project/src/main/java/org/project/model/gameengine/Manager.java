package org.project.model.gameengine;

import org.project.model.building.Building;
import org.project.model.building.BuildingFactory;
import org.project.model.resource.Citizen;
import org.project.model.resource.Resource;
import org.project.utils.Result;

import java.util.*;

public final class Manager {
    private static int BUILDING_COUNTER = 0;
    private static int UNDER_COUNTER = 0;

    private final Map<Integer, Building> buildings;
    private final Map<Integer, Building> underConstruction;
    private final Citizen habitants;
    private final Citizen workers;
    private final ManagerDelegate delegate;

    public Manager(ManagerDelegate delegate) {
        this.buildings = new HashMap<>();
        this.underConstruction = new HashMap<>();
        this.habitants = new Citizen(0);
        this.workers = new Citizen(0);
        this.delegate = delegate;
    }

    public Optional<String> addBuilding(Building.Type buildingType) {
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
            return Optional.of("Impossible de contruire le batiment.");
        }

        this.underConstruction.put(UNDER_COUNTER, building);
        UNDER_COUNTER++;

        this.delegate.removeFromStock(building.getBuildRequirements());
    }

    // TODO: Ajouter le fait de retourner un bout des ressources de contrustion.
    public Result<Set<Resource>, String> removeBuilding(Building.Type type, int index) {
        if (this.buildings.containsKey(type)) {
            this.buildings.get(type).remove(index);
            if (this.buildings.get(type).isEmpty()) {
                this.buildings.remove(type);
            }
            return Result.success(new HashSet<>());
        }
        return Result.failure("Le joueur ne possede aucun type de ce building.");
    }
}
