package Composite.Supplements;

import Composite.Ingredients;
import Composite.Recette;

public class Amandes implements Recette {
    @Override public String getName() { return "Amandes"; }
    @Override public Ingredients getIngredients() { return null; }
}
