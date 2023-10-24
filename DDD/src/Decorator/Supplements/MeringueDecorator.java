package Decorator.Supplements;

import Decorator.Decorator;
import Decorator.Recette;

public class MeringueDecorator extends Decorator {
    public MeringueDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override public String getName() { return super.getName() + " Meringue"; }
}
