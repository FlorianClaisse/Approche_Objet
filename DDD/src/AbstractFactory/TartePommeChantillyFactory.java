package AbstractFactory;

import Composite.*;

public class TartePommeChantillyFactory extends GateauFactory {
    @Override
    public Recette create() {
        GateauComposite tarte = new GateauComposite();
        tarte.addFils(new Pate(Pate.Type.SABLEE));
        tarte.addFils(new Fruit(Fruit.Type.POMME));
        tarte.addFils(new Supplement(Supplement.Type.CHANTILLY));

        return tarte;
    }
}
