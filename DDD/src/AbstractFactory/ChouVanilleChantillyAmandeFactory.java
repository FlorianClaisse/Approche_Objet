package AbstractFactory;

import Composite.*;

public class ChouVanilleChantillyAmandeFactory extends GateauFactory {
    @Override
    public Recette make() {
        GateauComposite chou = new GateauComposite();
        chou.addFils(new Pate(Pate.Type.CHOUX));
        chou.addFils(new Creme(Creme.Type.VANILLE));
        chou.addFils(new Supplement(Supplement.Type.CHANTILLY));
        chou.addFils(new Supplement(Supplement.Type.AMANDE));

        return chou;
    }
}
