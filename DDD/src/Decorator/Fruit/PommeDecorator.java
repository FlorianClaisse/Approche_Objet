package Decorator.Fruit;

import Decorator.Decorator;
import Decorator.Recette;

public class PommeDecorator extends Decorator {
    public PommeDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Pomme"; }
}