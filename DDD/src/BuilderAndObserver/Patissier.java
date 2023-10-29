package BuilderAndObserver;

import Composite.*;

import java.util.ArrayList;

/* GateauDirector & RestockGateauxPublisher */
public class Patissier implements MakeGateauxSubscriber {
    private GateauBuilder builder;
    private ArrayList<GateauComposite> gateauxPourRestock = new ArrayList<>();
    private RestockGateauxSubscriber subscriber; // Boulangerie

    public void subscribe(RestockGateauxSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void make(String gateauName, int number) {
        for(int i = 0; i < number; i++) {
            if (gateauName.equals("Chou Chocolat"))
                makeChouChocolat();
            else if (gateauName.equals("Chou Vanille Chantilly Amandes"))
                makeChouVanilleChantillyAmandes();
            else if (gateauName.equals("Tarte Feuilletée Pommes"))
                makeTarteFeuilleteePommes();
            else if (gateauName.equals("Tarte Brisée Abricots Meringue"))
                makeTarteBriseeAbricotsMeringue();

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
    public void makeChouVanilleChantillyAmandes() {
        setBuilder(new ChouBuilder(Creme.Type.VANILLE, Supplement.Type.CHANTILLY, Supplement.Type.AMANDE));
        step1_2_3_4();
    }

    // Tartes
    public void makeTarteFeuilleteePommes() {
        setBuilder(new TarteBuilder(Pate.Type.FEUILLETEE, Fruit.Type.POMME));
        step1_2();
    }
    public void makeTarteBriseeAbricotsMeringue() {
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
