package BuilderAndObserver;

import Composite.GateauComposite;
import Composite.Recette;

import java.util.ArrayList;

/* Publisher */
public class Vendeur {
    private MakeGateauxSubscriber subscriber; // Patissier

    public void subscribe(MakeGateauxSubscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void notifyManqueGateau(String gateauName, int number) {
        subscriber.make(gateauName, number);
    }

    // Déroulement d'une vente pour un gateau
    public Pair<Recette, Integer> vente(String gateauName, int wantedNumber, Boulangerie boulangerie) {
        if(!boulangerie.aDansChoixGateaux(gateauName))
            return null;

        int currentGateauNumber = boulangerie.getGateauNumber(gateauName);
        if(wantedNumber > currentGateauNumber) {
            // System.out.println("notif manque gateaux à la vente");
            notifyManqueGateau(gateauName, wantedNumber - currentGateauNumber);
        }

        return boulangerie.getGateaux(gateauName, wantedNumber);
    }
}
