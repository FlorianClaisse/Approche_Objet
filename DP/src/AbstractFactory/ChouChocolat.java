package AbstractFactory;

import Composite.*;

/* ConcreteFactoryA */
public class ChouChocolat implements GateauFactory {
    public Recette make() {
        GateauComposite chou = new GateauComposite();
        chou.addFils(new Pate(Pate.Type.CHOUX));
        chou.addFils(new Creme(Creme.Type.CHOCOLAT));

        return chou;
    }
}
