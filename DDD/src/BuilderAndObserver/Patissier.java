package BuilderAndObserver;

import Composite.*;

import java.util.ArrayList;

/* GateauDirector & RestockGateauxPublisher */
public class Patissier implements MakeGateauxSubscriber {
    private GateauBuilder builder;
    private final ArrayList<GateauComposite> gateauxPourRestock = new ArrayList<>();
    private RestockGateauxSubscriber subscriber; // Boulangerie

    public void subscribe(RestockGateauxSubscriber subscriber) {
        this.subscriber = subscriber;
    }


    public void make(String gateauName) {
        if (gateauName.equals("Chou Chocolat"))
            makeChouChocolat();
        else if (gateauName.equals("Chou Chocolat Chantilly"))
            makeChouChocolatChantilly();
        else if (gateauName.equals("Chou Chocolat Noisettes"))
            makeChouChocolatNoisettes();
        else if (gateauName.equals("Chou Chocolat Amandes"))
            makeChouChocolatNoisettes();
        else if (gateauName.equals("Chou Chocolat Chantilly Amandes"))
            makeChouChocolatChantillyAmandes();
        else if(gateauName.equals("Chou Chocolat Chantilly Noisettes"))
            makeChouChocolatChantillyNoisettes();
        else if (gateauName.equals("Chou Vanille"))
            makeChouVanille();
        else if (gateauName.equals("Chou Vanille Chantilly"))
            makeChouVanilleChantilly();
        else if (gateauName.equals("Chou Vanille Noisettes"))
            makeChouVanilleNoisettes();
        else if (gateauName.equals("Chou Vanille Amandes"))
            makeChouVanilleAmandes();
        else if (gateauName.equals("Chou Vanille Chantilly Amandes"))
            makeChouVanilleChantillyAmandes();
        else if(gateauName.equals("Chou Vanille Chantilly Noisettes"))
            makeChouVanilleChantillyNoisettes();
        else if (gateauName.equals("Tarte Feuilletée Pommes"))
            makeTarteFeuilleteePommes();
        else if (gateauName.equals("Tarte Feuilletée Pommes Meringue"))
            makeTarteFeuilleteePommesMeringue();
        else if (gateauName.equals("Tarte Feuilletée Pommes Meringue Amandes"))
            makeTarteFeuilleteePommesMeringueAmandes();
        else if (gateauName.equals("Tarte Feuilletée Pommes Meringue Noisettes"))
            makeTarteFeuilleteePommesMeringueNoisettes();
        else if (gateauName.equals("Tarte Feuilletée Abricots"))
            makeTarteFeuilleteeAbricots();
        else if (gateauName.equals("Tarte Feuilletée Abricots Meringue"))
            makeTarteFeuilleteeAbricotsMeringue();
        else if (gateauName.equals("Tarte Feuilletée Abricots Meringue Amandes"))
            makeTarteFeuilleteeAbricotsMeringueAmandes();
        else if (gateauName.equals("Tarte Feuilletée Abricots Meringue Noisettes"))
            makeTarteFeuilleteeAbricotsMeringueNoisettes();
    }
    public void make(String gateauName, int number) {
        for(int i = 0; i < number; i++) {
            make(gateauName);
            gateauxPourRestock.add(builder.getGateau());
        }
        subscriber.restock(gateauxPourRestock);
        gateauxPourRestock.clear();
    }

    private void setBuilder(GateauBuilder builder) { this.builder = builder; }
    public GateauBuilder getBuilder() { return builder; }

    // Choux
    public void makeChouChocolat() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT));
        step1_2();
    }
    public void makeChouChocolatChantilly() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT, Supplement.Type.CHANTILLY));
        step1_2_3();
    }
    public void makeChouChocolatNoisettes() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT, Supplement.Type.NOISETTE));
        step1_2_3();
    }
    public void makeChouChocolatAmandes() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT, Supplement.Type.AMANDE));
        step1_2_3();
    }
    public void makeChouChocolatChantillyAmandes() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT, Supplement.Type.CHANTILLY, Supplement.Type.AMANDE));
        step1_2_3_4();
    }
    public void makeChouChocolatChantillyNoisettes() {
        setBuilder(new ChouBuilder(Creme.Type.CHOCOLAT, Supplement.Type.CHANTILLY, Supplement.Type.NOISETTE));
        step1_2_3_4();
    }
    public void makeChouVanille() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE));
        step1_2();
    }
    public void makeChouVanilleChantilly() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.CHANTILLY));
        step1_2_3();
    }
    public void makeChouVanilleNoisettes() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.NOISETTE));
        step1_2_3();
    }
    public void makeChouVanilleAmandes() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.AMANDE));
        step1_2_3();
    }
    public void makeChouVanilleChantillyAmandes() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.CHANTILLY, Supplement.Type.AMANDE));
        step1_2_3_4();
    }
    public void makeChouVanilleChantillyNoisettes() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.CHANTILLY, Supplement.Type.NOISETTE));
        step1_2_3_4();
    }

    // Tartes
    public void makeTarteFeuilleteePommes() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.POMME));
        step1_2();
    }
    public void makeTarteFeuilleteePommesMeringue() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.POMME, Supplement.Type.MERINGUE));
        step1_2_3();
    }
    public void makeTarteFeuilleteePommesMeringueAmandes() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.POMME, Supplement.Type.MERINGUE, Supplement.Type.AMANDE));
        step1_2_3_4();
    }
    public void makeTarteFeuilleteePommesMeringueNoisettes() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.POMME, Supplement.Type.MERINGUE, Supplement.Type.NOISETTE));
        step1_2_3_4();
    }
    public void makeTarteFeuilleteeAbricots() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.ABRICOT));
        step1_2();
    }
    public void makeTarteFeuilleteeAbricotsMeringue() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.ABRICOT, Supplement.Type.MERINGUE));
        step1_2_3();
    }
    public void makeTarteFeuilleteeAbricotsMeringueAmandes() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.ABRICOT, Supplement.Type.MERINGUE, Supplement.Type.AMANDE));
        step1_2_3_4();
    }
    public void makeTarteFeuilleteeAbricotsMeringueNoisettes() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.ABRICOT, Supplement.Type.MERINGUE, Supplement.Type.NOISETTE));
        step1_2_3_4();
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
