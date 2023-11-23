package org.project.model.resource;

/** The representation of a material in the game.
 *
 * @implNote A material can be used to construct a building,
 * it can be produced and consumed by a building.
 * In the case of food, it can also be consumed by citizens. */
public class Material extends Resource {
    /** The type of material (e.g. WOOD) */
    private final Type type;
    /** Initialize a new Material instance with the given type and initial quantity. */
    public Material(Type type, int quantity) {
        this.type = type;
        this.addQuantity(quantity);
    }
    /** An enumeration that contains all types of game resources. */
    public enum Type {
        GOLD("Gold", 1),
        FOOD("Food", 1),
        WOOD("Wood", 2),
        STONE("Stone", 3),
        COAL("Coal", 4),
        IRON("Iron", 6),
        STEEL("Steel", 8),
        CEMENT("Cement", 10),
        LUMBER("Lumber", 12),
        TOOLS("Tools", 24);
        /** This representation in the form of a string. */
        private final String rawValue;
        /** The gold price of this resource. */
        private final int price;

        Type(String rawValue, int price) {
            this.rawValue = rawValue;
            this.price = price;
        }
    }
}
