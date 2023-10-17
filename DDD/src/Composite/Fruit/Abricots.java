package Composite.Fruit;

import Composite.Ingredients;
import Composite.Recette;

public class Abricots implements Recette {
    @Override public String getName() { return "Abricots"; }
    @Override public Ingredients getIngredients() { return null; }
}
