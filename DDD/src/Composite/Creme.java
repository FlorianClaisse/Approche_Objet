package Composite;

/* FeuilleA */
public class Creme implements Recette {
    private final Creme.Type parfum;

    public Creme(Creme.Type parfum) {
        this.parfum = parfum;
    }

    @Override
    public String getName() { return parfum.rawValue; }

    @Override
    public Ingredients getIngredients() { return null; }

    public enum Type {
        CHOCOLAT("Chocolat"),
        VANILLE("Vanille"),
        CAFE("Café");

        private final String rawValue;

        Type(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
