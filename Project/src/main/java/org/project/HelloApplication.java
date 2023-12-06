package org.project;

import org.jetbrains.annotations.Nullable;
import org.project.model.building.Building;
import org.project.model.gameengine.*;
import org.project.model.resource.Material;
import org.project.model.resource.Purchasable;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;
import org.project.utils.Result;

import java.util.Map;
import java.util.Scanner;

public class HelloApplication /*extends Application*/ {
    private static int dayNumber = 0;
    private static boolean rollback = false;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Player player = new Player();
    private static final Shop shop = new Shop(player);
    private static final City city = new City(player, shop);

    private static void prompt(String message) {
        System.out.println("---------------------------");
        System.out.println(message);
        System.out.println("---------------------------");
    }

    private static int getInt() {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        } else {
            scanner.next();
            return -1;
        }
    }

    public static void main(String[] args) {
        do {
            prompt("Day : " + dayNumber + '\n' +
                   "Total habitants : " + city.getNbHabitants() + '\n' +
                   "Future total : "  + city.getFutureTotalHabitants() + '\n' +
                   "Current workers : " + city.getNbWorkers() + '\n' +
                   "Future workers : " + city.getNbFutureWorkers() + '\n' +
                   "---------------------------" + '\n' +
                   """
                   [1] View your resources
                   [2] See your constructed buildings
                   [3] See your buildings under construction
                   [4] See your resource production
                   [5] See your resource consumption
                   [6] Buy materials with gold
                   [7] Build a new building (*)
                   [8] Upgrade a building (*)
                   [9] Remove constructed building (*)
                   [10] Remove building under construction (*)
                   [11] Add workers to constructed building
                   [12] Remove workers from constructed building
                   [13] Nothing (*)
                   (*) = Go to the next day""");

            int result = getInt();
            switch (result) {
                case 1 -> printResources(player.getStock(), "Current resources :");
                case 2 -> printBuilding(city.getConstructedBuildings(), "Constructed Buildings :");
                case 3 -> printBuilding(city.getUnderConstructionBuildings(), "Buildings under construction :");
                case 4 -> printResources(city.getCurrentProduction(), "Current production :");
                case 5 -> printResources(city.getCurrentConsumption(), "Current consumption :");
                case 6 -> purchaseMaterial();
                case 7 -> buildNewBuilding();
                case 8 -> upgradeBuilding();
                case 9 -> removeBuilding(city.getConstructedBuildings(), false);
                case 10 -> removeBuilding(city.getUnderConstructionBuildings(), true);
                case 11 -> addWorkers();
                case 12 -> removeWorkers();
                case 13 -> {}
                default -> prompt("Please enter a valid number.");
            }

            // Pour éviter de sauter un jour si l'action joueur n'aboutit pas
            // Par exemple s'il entre une valeur invalide
            if(rollback) {
                rollback = false;
                continue;
            }

            if ((result >= 7 && result <= 10) || result == 13) {
                city.dayEnd();
                dayNumber++;
            }
        } while(!gameIsOver());

        System.out.println(isWinning() ? "You Won :)" : "You Lost :(");
    }

    private static void printResources(Resources resources, String message) {
        StringBuilder stringBuilder = new StringBuilder(message + '\n');
        resources.forEach((r, q) -> {
            if (q.get() > 0)
                stringBuilder.append(r.getTypeName())
                        .append("(quantity=")
                        .append(q)
                        .append(")\n");
        });
        prompt(stringBuilder.toString());
    }

    private static void printBuilding(Map<Integer, Building> buildings, String message) {
        StringBuilder stringBuilder = new StringBuilder(message).append('\n');
        buildings.forEach((id, b) -> stringBuilder.append(b.toString())
                                                  .append('\n')
        );
        prompt(stringBuilder.toString());
    }

    private static void purchaseMaterial() {
        Material.Type[] materialTypes = Material.Type.values();
        StringBuilder stringBuilder = new StringBuilder("Which type of materials ?\n");
        for(int i = 0; i < materialTypes.length; i++) {
            stringBuilder.append("[").append(i).append("] ").append(materialTypes[i]).append(" (price: ").append(new Material(materialTypes[i]).getPrice()).append(")\n");
        }
        prompt(stringBuilder.toString());

        int type = getInt();
        if (type < 0 || type >= materialTypes.length) {
            System.out.println("Please provide a valid type");
            return;
        }

        System.out.println("How many ?");
        int quantity = getInt();
        if (quantity < 0) {
            System.out.println("Please provide a valid quantity");
            return;
        }

        if(!shop.buyMaterials(materialTypes[type], quantity))
            System.out.println("You don't have enough gold to buy this many materials.");
    }

    private static void buildNewBuilding() {
        Building.Type[] buildingTypes = Building.Type.values();
        StringBuilder stringBuilder = new StringBuilder("Which type of building\n");
        for (int i = 0; i < buildingTypes.length; i++) {
            stringBuilder.append("[").append(i).append("] ").append(buildingTypes[i]).append('\n');
        }
        prompt(stringBuilder.toString());

        int type = getInt();
        if (type < 0 || type >= buildingTypes.length) {
            System.out.println("Please provide a valid type.");
            rollback = true;
            return;
        }

        if (!city.purchaseBuilding(buildingTypes[type])) {
            System.out.println("You don't have enough resources or free habitants to build this building.");
            rollback = true;
        }
    }

    private static void upgradeBuilding() {
        StringBuilder builder = new StringBuilder("Update building :\nWhich building ?\n");
        Map<Integer, Building> buildings = city.getConstructedBuildings();
        buildings.forEach((id, b) -> builder.append("[").append(id).append("] ").append(b).append('\n'));
        prompt(builder.toString());

        int id = getInt();
        if (!buildings.containsKey(id)) {
            System.out.println("Please provide a valid building id");
            rollback = true;
            return;
        }

        if (!city.upgradeBuilding(id)) {
            System.out.println("This building can't be upgraded further or you don't have enough resources to upgrade it.");
            rollback = true;
        }
    }

    private static void removeBuilding(Map<Integer, Building> buildings, boolean under) {
        StringBuilder stringBuilder = new StringBuilder("Remove building\n");
        buildings.forEach((id, b) -> stringBuilder.append("[").append(id).append("] ").append(b).append('\n'));
        prompt(stringBuilder.toString());

        int id = getInt();
        if (!buildings.containsKey(id)) {
            System.out.println("Please provide a valid building id");
            rollback = true;
            return;
        }

        if(under)
            city.removeUnderConstructionBuilding(id);
        else
            city.removeExistingBuilding(id);
    }

    private static void addWorkers() {
        StringBuilder builder = new StringBuilder("Add workers :\nWhich building ?\n");
        Map<Integer, Building> buildings = city.getConstructedBuildings();
        buildings.forEach((id, b) -> builder.append("[").append(id).append("] ").append(b).append('\n'));
        prompt(builder.toString());

        int res = getInt();
        if (res == -1 || !buildings.containsKey(res)) {
            System.out.println("Please provide a valid entry.");
            return;
        }

        System.out.println("How many ?");
        int quantity = getInt();
        if (res < 0) {
            System.out.println("Please provide a valid entry.");
            return;
        }

        if (!city.addWorkersToBuilding(res, quantity))
            System.out.println("Not enough place for this many workers in the building OR not enough free habitants in the city.");
    }

    private static void removeWorkers() {
        StringBuilder builder = new StringBuilder("Remove workers :\nWhich building ?\n");
        Map<Integer, Building> buildings = city.getConstructedBuildings();
        buildings.forEach((id, b) -> builder.append("[").append(id).append("] ").append(b).append('\n'));
        prompt(builder.toString());

        int id = getInt();
        if (!buildings.containsKey(id)) {
            System.out.println("Please provide a valid building id.");
            return;
        }

        System.out.println("How many ?");
        int quantity = getInt();
        if (quantity < 0) {
            System.out.println("Please provide a valid entry.");
            return;
        }

        if (!city.removeWorkersFromBuilding(id, quantity))
            System.out.println("Unable to delete  workers");
    }

    private static boolean gameIsOver() {
        Resources resources = new Resources();
        resources.initWithAllResources();
        return (!player.haveEnoughResources(resources) || city.isWinning());
    }

    private static boolean isWinning() {
        Resources resources = new Resources();
        resources.initWithAllResources();
        return player.haveEnoughResources(resources);
    }
}