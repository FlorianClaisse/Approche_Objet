package Decorator.Gateau;

import Decorator.Ingredients;
import Decorator.Recette;

public abstract class Gateau implements Recette {
    private final String name;
    private Ingredients ingredients;

    public Gateau(String name, Ingredients ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override public String getName() { return name; }
    @Override public Ingredients getIngredients() { return ingredients; }
}
