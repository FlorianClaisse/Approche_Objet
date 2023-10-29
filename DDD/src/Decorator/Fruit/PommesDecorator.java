package Decorator.Fruit;

import Decorator.Decorator;
import Decorator.Recette;

/* ConcreteDecoratorD */
public class PommesDecorator extends Decorator {
    public PommesDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Pommes"; }
}