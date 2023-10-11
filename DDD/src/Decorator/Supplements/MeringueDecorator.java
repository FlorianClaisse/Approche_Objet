package Decorator.Supplements;

import Decorator.*;

public class MeringueDecorator extends Decorator {
    public MeringueDecorator(GateauInterface gateauDecorated) {
        super(gateauDecorated);
    }

    @Override public String getName() { return super.getName() + " Meringue"; }
}
