package Composite;

/* FeuilleB */
public class Fruit implements Recette {
    private final Fruit.Type fruit;

    public Fruit(Type fruit) {
        this.fruit = fruit;
    }

    @Override
    public String getName() { return fruit.rawValue; }

    @Override
    public Ingredients getIngredients() { return null; }

    public enum Type {
        POMME("Pommes"),
        ABRICOT("Abricots"),
        FRAISE("Fraises"),
        FRAMBOISE("Framboises"),
        MYRTILLE("Myrtilles");

        private final String rawValue;

        Type(String rawValue) {
            this.rawValue = rawValue;
        }
    }
}
