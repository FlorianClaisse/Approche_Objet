package Decorator.Gateau;

import Decorator.Ingredients;

/* ConcreteComponentA*/
public class Chou extends Gateau {
    public Chou(Ingredients ingredients) {
        super("Chou", ingredients);
    }
}
