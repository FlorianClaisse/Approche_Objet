package Builder;

import Composite.GateauComposite;

public abstract class GateauBuilder {
    protected GateauComposite gateau;

    public void createNewGateau() { gateau = new GateauComposite(); }
    public GateauComposite getGateau() { return gateau; }

    public abstract void buildStep1();
    public abstract void buildStep2();
    public abstract void buildStep3();
    public abstract void buildStep4();
}
