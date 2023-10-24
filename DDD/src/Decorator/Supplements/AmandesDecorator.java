package Decorator.Supplements;

import Decorator.Decorator;
import Decorator.Recette;

public class AmandesDecorator extends Decorator {
    public AmandesDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Amandes grillées"; }
}