package Builder;

import Composite.Creme;
import Composite.Fruit;
import Composite.Pate;
import Composite.Supplement;

public class GateauDirector {
    private GateauBuilder builder;

    private void setBuilder(GateauBuilder builder) { this.builder = builder; }
    public GateauBuilder getBuilder() { return builder; }

    // Choux

    public void constructChouxVanille() {
        setBuilder(new ChouxBuilder(Creme.Type.VANILLE));
        step1_2();
    }

    public void constructChouxChocolat() {
        setBuilder(new ChouxBuilder(Creme.Type.CHOCOLAT));
        step1_2();
    }

    public void constructChouxVanilleChantilly() {
        setBuilder(new ChouxBuilder(Creme.Type.VANILLE, Supplement.Type.CHANTILLY));
        step1_2_3();
    }

    public void constructChouxChocolatNoisette() {
        setBuilder(new ChouxBuilder(Creme.Type.CHOCOLAT, Supplement.Type.NOISETTE));
        step1_2_3();
    }

    // Tarte

    public void constructTarteSablePomme() {
        setBuilder(new TarteBuilder(Pate.Type.SABLEE, Fruit.Type.POMME));
        step1_2();
    }

    public void constructTarteFeuilleteAbricotMeringue() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.ABRICOT, Supplement.Type.MERINGUE));
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
