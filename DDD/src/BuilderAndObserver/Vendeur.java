package BuilderAndObserver;

import Composite.GateauComposite;

import java.util.ArrayList;

/* Publisher */
public class Vendeur {
    private MakeGateauxSubscriber subscriber; // Patissier

    public void subscribe(MakeGateauxSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    private void notifyManqueGateau(String gateauName, int number) {
        subscriber.make(gateauName, number);
    }

    // Déroulement d'une vente pour un gateau
    public ArrayList<GateauComposite> vente(String gateauName, int wantedNumber, Boulangerie boulangerie) {
        int currentGateauNumber = boulangerie.getGateauNumber(gateauName);
        if(wantedNumber <= currentGateauNumber) {
            boulangerie.removeFromStock(gateauName, wantedNumber);
            return boulangerie.getGateaux(gateauName, wantedNumber);
        } else {
            if(boulangerie.aDansChoixGateaux(gateauName)) {
                notifyManqueGateau(gateauName, wantedNumber - currentGateauNumber);
            }
            return boulangerie.getGateaux(gateauName, currentGateauNumber);
        }
    }
}
