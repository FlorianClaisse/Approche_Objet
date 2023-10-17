package Composite.Supplements;

import Composite.Ingredients;
import Composite.Recette;

public class Chantilly implements Recette {
    @Override public String getName() { return "Chantilly"; }
    @Override public Ingredients getIngredients() { return null; }
}
