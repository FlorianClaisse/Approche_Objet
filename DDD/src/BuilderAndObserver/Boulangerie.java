package BuilderAndObserver;

import Composite.GateauComposite;
import Composite.Recette;

import java.util.ArrayList;
import java.util.HashMap;

public class Boulangerie implements RestockGateauxSubscriber {
    private final Vendeur vendeur = new Vendeur();
    private final Patissier patissier = new Patissier();

    private final HashMap<String, Pair<Recette, Integer>> stockVitrine = new HashMap<>(); // Le nombre de gateaux actuellement disponible
    private final ArrayList<String> nomsGateauxVendus; // La liste des noms de gateau disponible à la vente

    public Boulangerie(ArrayList<String> choixGateaux) {
        vendeur.subscribe(patissier);
        patissier.subscribe(this);
        this.nomsGateauxVendus = choixGateaux;
    }

    public void restock(ArrayList<GateauComposite> gateaux) {
        String gateauName = gateaux.get(0).getName();
        Pair<Recette, Integer> currentGateau = stockVitrine.get(gateauName);
        if(currentGateau != null) {
            currentGateau.setSecond(currentGateau.getSecond() + gateaux.size());
        } else {
            stockVitrine.put(gateauName, new Pair<>(gateaux.get(0), gateaux.size()));
        }
    }

    public int getGateauNumber(String gateauName) {
        Pair<Recette, Integer> gateau = stockVitrine.get(gateauName);
        return (gateau != null ? gateau.getSecond() : 0);
    }

    public Pair<Recette, Integer> getGateaux(String gateauName, int number) {
        Pair<Recette, Integer> gateau = stockVitrine.get(gateauName);
        Pair<Recette, Integer> returnValue = new Pair<>(gateau.getFirst(), gateau.getSecond());

        gateau.setSecond(gateau.getSecond() - number);

        if (gateau.getSecond() == 0)
            stockVitrine.remove(gateauName);

        return returnValue;
    }

    public boolean aDansChoixGateaux(String gateauName) {
        return nomsGateauxVendus.contains(gateauName);
    }

    public Vendeur getVendeur() { return vendeur; }
}
