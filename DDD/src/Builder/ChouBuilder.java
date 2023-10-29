package Builder;

import Composite.Creme;
import Composite.Pate;
import Composite.Supplement;

public class ChouBuilder extends GateauBuilder {
    private final Creme.Type creme;
    private Supplement.Type supplement1;
    private Supplement.Type supplement2;

    public ChouBuilder(Creme.Type creme) {
        this.creme = creme;
    }
    public ChouBuilder(Creme.Type creme, Supplement.Type supplement) {
        this(creme);
        this.supplement1 = supplement;
    }
    public ChouBuilder(Creme.Type creme, Supplement.Type supplement1, Supplement.Type supplement2) {
        this(creme, supplement1);
        this.supplement2 = supplement2;
    }

    @Override public void buildStep1() {
        gateau.addFils(new Pate(Pate.Type.CHOUX));
    }

    @Override public void buildStep2() {
        gateau.addFils(new Creme(creme));
    }

    @Override public void buildStep3() {
        gateau.addFils(new Supplement(supplement1));
    }

    @Override public void buildStep4() {
        gateau.addFils(new Supplement(supplement2));
    }
}
