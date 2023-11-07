package BuilderAndObserver;

import Composite.GateauComposite;
import Composite.Recette;

import java.util.ArrayList;
import java.util.HashMap;

public class Boulangerie implements RestockGateauxSubscriber {
    private final Vendeur vendeur = new Vendeur();
    private final Patissier patissier = new Patissier();
    private final HashMap<String, Pair<Recette, Integer>> stockVitrine = new HashMap<>();
    private final ArrayList<String> nomsGateauxVendus; // La liste des noms de gateau disponible à la vente
    private final int SEUIL_GATEAU_X;

    public Boulangerie(ArrayList<String> nomsGateauxVendus, int SEUIL_GATEAU_X) {
        this.nomsGateauxVendus = nomsGateauxVendus;
        this.SEUIL_GATEAU_X = SEUIL_GATEAU_X;

        for (String nomGateauVendu : nomsGateauxVendus) {
            /* for(int i = 0; i < SEUIL_GATEAU_X; i++) */
            // On imagine que le patissier fait SEUIL_GATEAU_X gateaux
                patissier.make(nomGateauVendu);
            stockVitrine.put(nomGateauVendu, new Pair<>(patissier.getBuilder().getGateau(), 3*SEUIL_GATEAU_X));
        }

        vendeur.subscribe(patissier);
        patissier.subscribe(this);
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
        Pair<Recette, Integer> returnedGateaux = new Pair<>(gateau.getFirst(), number);

        Integer newGateauNumber = gateau.getSecond() - number;
        stockVitrine.replace(gateauName, new Pair<>(gateau.getFirst(), newGateauNumber));

        if (newGateauNumber < SEUIL_GATEAU_X) {
            // System.out.println("notif manque gateaux après vente (nb inférieur à seuil)");
            vendeur.notifyManqueGateau(gateauName, 2*SEUIL_GATEAU_X);
        }

        return returnedGateaux;
    }

    public boolean aDansChoixGateaux(String gateauName) {
        return nomsGateauxVendus.contains(gateauName);
    }

    public Vendeur getVendeur() { return vendeur; }
}
