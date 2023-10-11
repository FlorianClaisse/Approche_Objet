package Decorator.Fruit;

import Decorator.*;

public class AbricotsDecorator extends Decorator {
    public AbricotsDecorator(GateauInterface gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Abricots"; }
}