package AbstractFactory;

import Composite.*;

/* ConcreteFactoryD */
public class TarteFeuilleteePommeFactory implements GateauFactory {
    public Recette make() {
        GateauComposite tarte = new GateauComposite();
        tarte.addFils(new Pate(Pate.Type.FEUILLETEE));
        tarte.addFils(new Fruit(Fruit.Type.POMME));

        return tarte;
    }
}
