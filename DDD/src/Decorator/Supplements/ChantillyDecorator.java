package Decorator.Supplements;

import Decorator.*;

public class ChantillyDecorator extends Decorator {
    public ChantillyDecorator(GateauInterface gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Chantilly"; }
}