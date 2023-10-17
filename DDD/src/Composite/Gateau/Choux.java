package Composite.Gateau;

import Composite.Ingredients;
import Composite.Recette;

public class Choux implements Recette {
    @Override public String getName() { return "Choux"; }
    @Override public Ingredients getIngredients() { return null; }
}
