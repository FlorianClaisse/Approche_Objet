package BuilderAndObserver;

import Composite.GateauComposite;

import java.util.ArrayList;

public interface RestockGateauxSubscriber {
    void restock(ArrayList<GateauComposite> gateaux);
}
