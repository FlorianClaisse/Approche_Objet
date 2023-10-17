package Composite.Fruit;

import Composite.Ingredients;
import Composite.Recette;

public class Pomme implements Recette {
    @Override public String getName() { return "Pomme"; }
    @Override public Ingredients getIngredients() { return null; }
}