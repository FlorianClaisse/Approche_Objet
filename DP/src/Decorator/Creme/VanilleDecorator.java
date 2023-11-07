package Decorator.Creme;

import Decorator.Decorator;
import Decorator.Recette;

/* ConcreteDecoratorB */
public class VanilleDecorator extends Decorator {
    public VanilleDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Vanille"; }
}
