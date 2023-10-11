package Decorator.Creme;

import Decorator.*;

public class VanilleDecorator extends Decorator {
    public VanilleDecorator(GateauInterface gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Vanille"; }
}
