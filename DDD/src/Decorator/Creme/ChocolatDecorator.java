package Decorator.Creme;

import Decorator.Decorator;
import Decorator.Recette;

public class ChocolatDecorator extends Decorator {
    public ChocolatDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Chocolat"; }
}