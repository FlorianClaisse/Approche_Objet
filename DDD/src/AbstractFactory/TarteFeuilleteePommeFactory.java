package AbstractFactory;

import Composite.*;

public class TarteFeuilleteePommeFactory extends GateauFactory {
    @Override
    public Recette make() {
        GateauComposite tarte = new GateauComposite();
        tarte.addFils(new Pate(Pate.Type.FEUILLETEE));
        tarte.addFils(new Fruit(Fruit.Type.POMME));

        return tarte;
    }
}
