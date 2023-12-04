package org.project;

import org.project.model.building.Building;
import org.project.model.gameengine.City;
import org.project.model.gameengine.Player;
import org.project.model.resource.Resources;

import java.util.Map;
import java.util.Scanner;

public class HelloApplication /*extends Application*/ {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Player player = new Player();
    private static final City city = new City(player);
    /*@Override
    public void start(Stage stage) throws IOException {

        System.out.println(BuildingFactory.makeWoodenCabin());
        var cite = new Citizen(45);

        // Charger l'image d'herbe depuis les ressources
        Image imageHerbe = new Image(getClass().getResource("/textures/background.jpeg").toExternalForm());

        // Créer un ImageView pour l'image d'herbe
        ImageView imageView = new ImageView(imageHerbe);

        // Redimensionner l'image pour qu'elle remplisse la fenêtre
        imageView.setFitWidth(stage.getWidth());
        imageView.setFitHeight(stage.getHeight());
        imageView.setPreserveRatio(false);

        // Créer un conteneur StackPane pour placer l'ImageView
        StackPane root = new StackPane();
        root.getChildren().add(imageView);

        // Créer la scène et définir le conteneur comme racine
        Scene scene = new Scene(root, 800, 600); // Taille de la fenêtre (modifiable)

        // Gérer le redimensionnement de la fenêtre pour ajuster l'image
        stage.widthProperty().addListener((observable, oldValue, newValue) -> imageView.setFitWidth(newValue.doubleValue()));

        stage.heightProperty().addListener((observable, oldValue, newValue) -> imageView.setFitHeight(newValue.doubleValue()));

        // Définir la scène pour la fenêtre principale
        stage.setScene(scene);
        stage.setTitle("Fenêtre avec texture d'herbe"); // Titre de la fenêtre
        stage.show();
    }*/

    public static void main(String[] args) {
        do {
            prompt("""
                    [1] View your resources
                    [2] See your buildings constructed
                    [3] See your buildings under construction
                    [4] Build a new building
                    [5] See your resource production
                    [6] See your resource consumption
                    [7] Remove building
                    [8] Remove building under construction
                    [9] Remove workers to building
                    [10] Add workers to building
                    [11] Nothing""");

            int result = getInt();
            switch (result) {
                case 1 -> printResources(player.getStock());
                case 2 -> printBuilding(city.getConstructedBuildings(), "Constructed Buildings");
                case 3 -> printBuilding(city.getUnderConstruction(), "Building under construction");
                case 4 -> buildNewBuilding();
                case 5 -> printResources(city.getBeProducted());
                case 6 -> printResources(city.getBeConsumed());
                case 7 -> removeBuilding(city.getConstructedBuildings(), false);
                case 8 -> removeBuilding(city.getUnderConstruction(), true);
                case 9 -> removeWorkers();
                case 10 -> addWorkers();
                case 11 -> {}
                default -> prompt("Please enter a valid number.");
            }

            if (result == 4 || result == 7 || result == 8 || result == 9 || result == 10 || result == 11)
                city.dayEnd();
        } while(!city.gameIsOver());
    }

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

    private static void printResources(Resources resources) {
        StringBuilder stringBuilder = new StringBuilder("Your resources\n");
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

    private static void buildNewBuilding() {
        Building.Type[] buildintTypes = Building.Type.values();
        StringBuilder stringBuilder = new StringBuilder("What type of building\n");
        for (int i = 0; i < buildintTypes.length; i++) {
            stringBuilder.append("[").append(i).append("] ").append(buildintTypes[i]).append('\n');
        }
        prompt(stringBuilder.toString());

        int res = getInt();
        if (res >= 0 && res < buildintTypes.length) {
            if (!city.addBuilding(buildintTypes[res]))
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
            if (under && !city.removeUnderBuilding(res))
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

        System.out.println("How much ?");
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