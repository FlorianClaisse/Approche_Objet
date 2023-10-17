package Decorator.Supplements;

import Decorator.*;

public class AmandesDecorator extends Decorator {
    public AmandesDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Amandes grillées"; }
}