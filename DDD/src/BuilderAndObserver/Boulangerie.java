package BuilderAndObserver;

import Composite.GateauComposite;

import java.util.ArrayList;
import java.util.HashMap;

public class Boulangerie implements RestockGateauxSubscriber {
    private final Vendeur vendeur = new Vendeur();
    private final Patissier patissier = new Patissier();
    private final HashMap<String, Integer> stockVitrine = new HashMap<>(); // Le nombre de gateaux actuellement disponible
    private final ArrayList<GateauComposite> gateauxVitrine = new ArrayList<>(); // Les gateaux "physiques" dans la vitrine
    private final ArrayList<String> nomsGateauxVendus; // La liste des noms de gateau disponible à la vente

    public Boulangerie(ArrayList<String> choixGateaux) {
        this.nomsGateauxVendus = choixGateaux;
        vendeur.subscribe(patissier);
        patissier.subscribe(this);
    }

    public void restock(ArrayList<GateauComposite> gateaux) {
        String gateauName = gateaux.get(0).getName();
        Integer currentGateauNumber = stockVitrine.get(gateauName);
        if(currentGateauNumber != null) {
            stockVitrine.replace(gateauName, currentGateauNumber + gateaux.size());
        } else {
            stockVitrine.put(gateauName, gateaux.size());
        }

        gateauxVitrine.addAll(gateaux);
    }

    public int getGateauNumber(String gateauName) {
        Integer gateauNumber = stockVitrine.get(gateauName);
        return (gateauNumber != null ? gateauNumber : 0);
    }

    public ArrayList<GateauComposite> getGateaux(String gateauName, int number) {
        ArrayList<GateauComposite> gateaux = new ArrayList<>();
        ArrayList<GateauComposite> gateauToRemove= new ArrayList<>();
        int i = 0;
        for(GateauComposite gateau : gateauxVitrine) {
            if(gateau.getName().equals(gateauName)) {
                gateaux.add(gateau);
                gateauToRemove.add(gateau);
                i++;
            }
            if(i == number)
                break;
        }
        gateauxVitrine.removeAll(gateauToRemove);
        return gateaux;
    }

    public void removeFromStock(String gateauName, int number) {
        Integer currentGateauNumber = stockVitrine.get(gateauName);
        stockVitrine.replace(gateauName, currentGateauNumber - number);
        if(currentGateauNumber - number == 0) {
            stockVitrine.remove(gateauName);
        }
    }

    public boolean aDansChoixGateaux(String gateauName) {
        return nomsGateauxVendus.contains(gateauName);
    }

    public Vendeur getVendeur() {
        return vendeur;
    }
}
