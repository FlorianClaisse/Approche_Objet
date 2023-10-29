package Composite;

/* FeuilleC */
public class Pate implements Recette {
    private final Pate.Type pate;

    public Pate(Type pate) {
        this.pate = pate;
    }

    @Override
    public String getName() { return pate.rawValue; }

    @Override
    public Ingredients getIngredients() { return null; }

    public enum Type {
        CHOUX("Chou"),
        BRISEE("Tarte Brisée"),
        FEUILLETEE("Tarte Feuilletée"),
        SABLEE("Tarte Sablée");

        private final String rawValue;

        Type(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
