package Decorator.Gateau;

import Decorator.Gateau.Gateau;
import Decorator.Ingredients;

public class Tarte extends Gateau {
    public Tarte(Ingredients ingredients) {
        super("Tarte", ingredients);
    }
}
