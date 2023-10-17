package Decorator.Fruit;

import Decorator.*;

public class PommeDecorator extends Decorator {
    public PommeDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Pomme"; }
}