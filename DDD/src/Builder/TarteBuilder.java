package Builder;

import Composite.Fruit;
import Composite.Pate;
import Composite.Supplement;

public class TarteBuilder extends GateauBuilder {

    private final Pate.Type pate;
    private Fruit.Type fruits;
    private Supplement.Type supplement1;
    private Supplement.Type supplement2;

    public TarteBuilder(Pate.Type pate) {
        this.pate = pate;
    }

    public TarteBuilder(Pate.Type pate, Fruit.Type fruits) {
        this(pate);
        this.fruits = fruits;
    }

    public TarteBuilder(Pate.Type pate, Fruit.Type fruits, Supplement.Type supplement1) {
        this(pate, fruits);
        this.supplement1 = supplement1;
    }

    public TarteBuilder(Pate.Type pate, Fruit.Type fruits, Supplement.Type supplement1, Supplement.Type supplement2) {
        this(pate, fruits, supplement1);
        this.supplement2 = supplement2;
    }

    @Override public void buildStep1() {
        gateau.addFils(new Pate(pate));
    }

    @Override public void buildStep2() {
        gateau.addFils(new Fruit(fruits));
    }

    @Override public void buildStep3() {
        gateau.addFils(new Supplement(supplement1));
    }

    @Override public void buildStep4() {
        gateau.addFils(new Supplement(supplement2));
    }
}
