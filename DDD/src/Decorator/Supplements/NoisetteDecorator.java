package Decorator.Supplements;

import Decorator.*;

public class NoisetteDecorator extends Decorator {
    public NoisetteDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Noisettes"; }
}