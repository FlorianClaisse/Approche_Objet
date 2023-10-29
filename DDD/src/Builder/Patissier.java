package Builder;

import Composite.Creme;
import Composite.Fruit;
import Composite.Pate;
import Composite.Supplement;

/* GateauDirector */
public class Patissier {
    private GateauBuilder builder;

    private void setBuilder(GateauBuilder builder) { this.builder = builder; }
    public GateauBuilder getBuilder() { return builder; }

    // Choux
    public void makeChouChocolat() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT));
        step1_2();
    }
    public void makeChouVanilleChantillyAmandes() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.CHANTILLY, Supplement.Type.AMANDE));
        step1_2_3_4();
    }

    // Tartes
    public void makeTarteFeuilleteePomme() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.POMME));
        step1_2();
    }
    public void makeTarteBriseeAbricotMeringue() {
        setBuilder(new TarteBuilder(Pate.Type.BRISEE, Fruit.Type.ABRICOT, Supplement.Type.MERINGUE));
        step1_2_3();
    }

    // Private
    private void create() {
        builder.createNewGateau();
    }

    private void step1_2() {
        create();
        builder.buildStep1();
        builder.buildStep2();
    }

    private void step1_2_3() {
        step1_2();
        builder.buildStep3();
    }

    private void step1_2_3_4() {
        step1_2_3();
        builder.buildStep4();
    }
}
