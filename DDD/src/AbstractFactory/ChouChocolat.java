package AbstractFactory;

import Composite.*;

public class ChouChocolat extends GateauFactory {
    @Override
    public Recette make() {
        GateauComposite chou = new GateauComposite();
        chou.addFils(new Pate(Pate.Type.CHOUX));
        chou.addFils(new Creme(Creme.Type.CHOCOLAT));

        return chou;
    }
}
