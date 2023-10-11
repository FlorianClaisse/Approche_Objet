package Decorator.Creme;

import Decorator.*;

public class ChocolatDecorator extends Decorator {
    public ChocolatDecorator(GateauInterface gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Chocolat"; }
}