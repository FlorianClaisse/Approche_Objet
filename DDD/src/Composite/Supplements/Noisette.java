package Composite.Supplements;

import Composite.Ingredients;
import Composite.Recette;

public class Noisette implements Recette {
    @Override public String getName() { return "Noisette"; }
    @Override public Ingredients getIngredients() { return null; }
}
