package org.project;

import javafx.application.Application;
import javafx.stage.Stage;
import org.project.model.building.BuildingFactory;
import org.project.model.gameengine.Player;
import org.project.model.resource.Citizen;
import org.project.model.resource.Material;
import org.project.model.resource.Resource;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Player player = new Player();
        player.printStock();
        System.out.println("");

        ArrayList<Resource> add = new ArrayList<>();
        add.add(new Material(Material.Type.FOOD, 15));
        add.add(new Material(Material.Type.STONE, 10));
        player.addToStock(add);
        player.printStock();
        System.out.println("");

        ArrayList<Resource> remove = new ArrayList<>();
        remove.add(new Material(Material.Type.FOOD, 1));
        remove.add(new Material(Material.Type.STONE, 100));
        player.removeFromStock(remove);
        player.printStock();

        ArrayList<Resource> construct = new ArrayList<>();
        construct.add(new Material(Material.Type.WOOD, 10));
        //construct.add(new Material(Material.Type.CEMENT, 10));
        System.out.println("\n" + player.canConstruct(construct));


        /*System.out.println(BuildingFactory.makeWoodenCabin());
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
        stage.show();*/
    }

    public static void main(String[] args) {
        launch();
    }
}