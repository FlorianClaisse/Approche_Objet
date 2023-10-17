package Decorator.Creme;

import Decorator.*;

public class ChocolatDecorator extends Decorator {
    public ChocolatDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Chocolat"; }
}