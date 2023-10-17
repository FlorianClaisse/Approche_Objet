package Decorator.Fruit;

import Decorator.*;

public class AbricotsDecorator extends Decorator {
    public AbricotsDecorator(Recette gateauDecorated) {
        super(gateauDecorated);
    }

    @Override
    public String getName() { return super.getName() + " Abricots"; }
}