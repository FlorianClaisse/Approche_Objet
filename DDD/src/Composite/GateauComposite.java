package Composite;

import java.util.ArrayList;
import java.util.List;

/* Composite */
public class GateauComposite implements Recette {
    private final List<Recette> composents = new ArrayList<>();

    public void addFils(Recette recette) {
        composents.add(recette);
    }

    public void removeFils(Recette recette) {
        composents.removeIf(r -> r.getName().equals(recette.getName()));
    }

    @Override
    public String getName() {
        StringBuilder returnValue = new StringBuilder();
        for(Recette recette: composents) {
            returnValue.append(recette.getName())
                       .append(" ");
        }

        return returnValue.toString();
    }

    @Override public Ingredients getIngredients() {
        Ingredients totalIngretiens = new Ingredients();
        for (Recette recette: composents) {
            totalIngretiens.addAll(recette.getIngredients());
        }
        return totalIngretiens;
    }
}
