package org.project.model.resource;

public final class Material extends Buyable {
    private final Type type;

    public Material(Type type, int quantity) {
        super(type.price);
        this.type = type;
        this.addQuantity(quantity);
    }

    public enum Type {
        FOOD("Food", 1),
        WOOD("Wood", 2),
        STONE("Stone", 3),
        COAL("Coal", 4),
        IRON("Iron", 6),
        STEEL("Steel", 8),
        CEMENT("Cement", 10),
        LUMBER("Lumber", 12),
        TOOLS("Tools", 24);

        private final String rawValue;
        private final int price;

        Type(String rawValue, int price) {
            this.rawValue = rawValue;
            this.price = price;
        }
    }
}
