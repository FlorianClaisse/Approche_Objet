package AbstractFactory;

import Composite.*;

public class TarteBriseeAbricotMeringueFactory extends GateauFactory {
    @Override
    public Recette make() {
        GateauComposite tarte = new GateauComposite();
        tarte.addFils(new Pate(Pate.Type.BRISEE));
        tarte.addFils(new Fruit(Fruit.Type.ABRICOT));
        tarte.addFils(new Supplement(Supplement.Type.MERINGUE));

        return tarte;
    }
}
