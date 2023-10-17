package Composite.Creme;

import Composite.Ingredients;
import Composite.Recette;

public class Vanille implements Recette {
    @Override public String getName() { return "Vanille"; }
    @Override public Ingredients getIngredients() { return null; }
}
