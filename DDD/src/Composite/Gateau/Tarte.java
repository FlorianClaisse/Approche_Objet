package Composite.Gateau;

import Composite.Ingredients;
import Composite.Recette;

public class Tarte implements Recette {
    @Override public String getName() { return "Tarte"; }
    @Override public Ingredients getIngredients() { return null; }
}
