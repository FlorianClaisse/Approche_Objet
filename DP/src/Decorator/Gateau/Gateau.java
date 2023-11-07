package Decorator.Gateau;

import Decorator.Ingredients;
import Decorator.Recette;

/* ConcreteComponent */
public abstract class Gateau implements Recette {
    private final String name;
    private Ingredients ingredients;

    public Gateau(String name) {
        this.name = name;
    }

    @Override public String getName() { return name; }
    @Override public Ingredients getIngredients() { return null; }
}
