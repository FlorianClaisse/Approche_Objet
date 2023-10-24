package Decorator.Supplements;

import Decorator.Decorator;
import Decorator.Recette;

public class NoisetteDecorator extends Decorator {
    public NoisetteDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Noisettes"; }
}