package Composite.Supplements;

import Composite.Ingredients;
import Composite.Recette;

public class Meringue implements Recette {
    @Override public String getName() { return "Meringue"; }
    @Override public Ingredients getIngredients() { return null; }
}
