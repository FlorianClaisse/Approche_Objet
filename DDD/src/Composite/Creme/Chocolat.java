package Composite.Creme;

import Composite.Ingredients;
import Composite.Recette;

public class Chocolat implements Recette {
    @Override public String getName() { return "Chocolat"; }
    @Override public Ingredients getIngredients() { return null; }
}