package Decorator.Gateau;

import Decorator.Ingredients;

/* ConcreteComponentB */
public class Tarte extends Gateau {
    public Tarte(Ingredients ingredients) {
        super("Tarte", ingredients);
    }
}
