package Decorator.Gateau;

import Decorator.GateauInterface;
import Decorator.Ingredients;

public abstract class Gateau implements GateauInterface {
    private final String name;
    private Ingredients ingredients;

    public Gateau(String name, Ingredients ingredients) {
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override public String getName() { return name; }
    @Override public Ingredients getIngredients() { return ingredients; }
}
