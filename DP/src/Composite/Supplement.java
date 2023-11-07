package Composite;

/* FeuilleD */
public class Supplement implements Recette {
    private final Supplement.Type type;

    public Supplement(Supplement.Type type) {
        this.type = type;
    }

    @Override
    public String getName() { return type.rawValue; }

    @Override
    public Ingredients getIngredients() { return null; }

    public enum Type {
        MERINGUE("Meringue"),
        CHANTILLY("Chantilly"),
        NOISETTE("Noisettes"),
        AMANDE("Amandes");

        private final String rawValue;

        Type(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
