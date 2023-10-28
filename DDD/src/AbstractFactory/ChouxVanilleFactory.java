package AbstractFactory;

import Composite.Creme;
import Composite.GateauComposite;
import Composite.Pate;
import Composite.Recette;

public class ChouxVanilleFactory extends GateauFactory {
    @Override
    Recette create() {
        var choux = new GateauComposite();
        choux.addFils(new Pate(Pate.Type.CHOUX));
        choux.addFils(new Creme(Creme.Type.VANILLE));

        return choux;
    }
}
