package Decorator.Fruit;

import Decorator.*;

public class PommeDecorator extends Decorator {
    public PommeDecorator(GateauInterface gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Pomme"; }
}