package org.project;

import org.project.model.building.Building;
import org.project.model.gameengine.*;
import org.project.model.resource.Material;
import org.project.model.resource.Purchasable;
import org.project.model.resource.Resources;
import org.project.utils.Quantity;

import java.util.Map;
import java.util.Scanner;

public class HelloApplication /*extends Application*/ {
    private static int dayNumber = 0;
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

            prompt("Day : " + dayNumber + "\n" +
                    """
                    [1] View your resources
                    [2] See your buildings constructed
                    [3] See your buildings under construction
                    [4] See your resource production
                    [5] See your resource consumption
                    [6] Buy materials (with gold)
                    [7] Build a new building
                    [8] Remove constructed building
                    [9] Remove building under construction
                    [10] Add workers to constructed building
                    [11] Remove workers from constructed building
                    [12] Nothing""");

            int result = getInt();
            switch (result) {
                case 1 -> printResources(player.getStock());
                case 2 -> printBuilding(city.getConstructedBuildings(), "Constructed Buildings");
                case 3 -> printBuilding(city.getUnderConstructionBuildings(), "Buildings under construction");
                case 4 -> printResources(city.getCurrentProduction());
                case 5 -> printResources(city.getCurrentConsumption());
                case 6 -> purchaseMaterial();
                case 7 -> buildNewBuilding();
                case 8 -> removeBuilding(city.getConstructedBuildings(), false);
                case 9 -> removeBuilding(city.getUnderConstructionBuildings(), true);
                case 10 -> addWorkers();
                case 11 -> removeWorkers();
                case 12 -> {}
                default -> prompt("Please enter a valid number.");
            }

            if (result == 6 || result == 7 || result == 8 || result == 9 || result == 10 || result == 11 || result == 12) {
                city.dayEnd();
                dayNumber++;
            }
        } while(!gameIsOver());

        // Savoir si il a gagné ou perdu
    }

    private static boolean gameIsOver() {
        Resources resources = new Resources();
        resources.initWithAllResources();
        return !player.haveEnoughResources(resources);
    }

    private static void printResources(Resources resources) {
        StringBuilder stringBuilder = new StringBuilder("Resources :\n");
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
            System.out.println("Please provide a valid entry");
            return;
        }

        System.out.println("How many ?");
        int quantity = getInt();
        if (quantity < 0) {
            System.out.println("Please provide a valid quantity");
            return;
        }

        if(!shop.buyMaterials(materialTypes[type], quantity)) {
            System.out.println("You don't have enough gold to buy this many materials.");
        }
    }

    private static void buildNewBuilding() {
        Building.Type[] buildingTypes = Building.Type.values();
        StringBuilder stringBuilder = new StringBuilder("Which type of building\n");
        for (int i = 0; i < buildingTypes.length; i++) {
            stringBuilder.append("[").append(i).append("] ").append(buildingTypes[i]).append('\n');
        }
        prompt(stringBuilder.toString());

        int res = getInt();
        if (res >= 0 && res < buildingTypes.length) {
            if (!city.purchaseBuilding(buildingTypes[res]))
                System.out.println("You don't have enough resources for this building.");
        } else {
            System.out.println("Please provide a valid entry.");
        }
    }

    private static void removeBuilding(Map<Integer, Building> buildings, boolean under) {
        StringBuilder stringBuilder = new StringBuilder("Remove building\n");
        buildings.forEach((id, b) -> stringBuilder.append("[").append(id).append("] ").append(b).append('\n'));
        System.out.println(buildings);
        prompt(stringBuilder.toString());

        int res = getInt();
        if (res == -1)
            System.out.println("Please provide a valid entry.");
        else
            if (under && !city.removeUnderConstructionBuilding(res))
                System.out.println("Please provide a valid entry.");
            else if (!under && !city.removeExistingBuilding(res))
                System.out.println("Please provide a valid entry.");
    }

    private static void removeWorkers() {
        StringBuilder builder = new StringBuilder("Remove workers\nWhich building ?");
        Map<Integer, Building> buildings = city.getConstructedBuildings();
        buildings.forEach((id, b) -> builder.append("[").append(id).append("] ").append(b).append('\n'));
        prompt(builder.toString());

        int res = getInt();
        if (res == -1 || !buildings.containsKey(res))
            System.out.println("Please provide a valid entry.");

        System.out.println("How many ?");
        int quantity = getInt();
        if (res == -1)
            System.out.println("Please provide a valid entry.");

        if (!city.removeWorkersFromBuilding(res, quantity))
            System.out.println("Unable to delete workers");
    }

    private static void addWorkers() {
        StringBuilder builder = new StringBuilder("Add workers\nWhich building ?");
        Map<Integer, Building> buildings = city.getConstructedBuildings();
        buildings.forEach((id, b) -> builder.append("[").append(id).append("] ").append(b).append('\n'));
        prompt(builder.toString());

        int res = getInt();
        if (res == -1 || !buildings.containsKey(res))
            System.out.println("Please provide a valid entry.");

        System.out.println("How much ?");
        int quantity = getInt();
        if (res == -1)
            System.out.println("Please provide a valid entry.");

        if (!city.addWorkersToBuilding(res, quantity))
            System.out.println("Unable to add workers");
    }
}