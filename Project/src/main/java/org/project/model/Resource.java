package org.project.model;

public class Resource {

    private final Type type;
    private final int quantity;

    public Resource(Type type, int quantity) {
        this.type = type;
        this.quantity = quantity;
    }

    public Type getType() { return type; }
    public String getName() { return type.getName(); }
    public int getQuantity() { return quantity; }

    @Override
    public String toString() {
        return "Resource(type=" + type.getName()
                + ", quantity=" + quantity + ')';
    }

    public enum Type {
        GOLD("Gold"),
        FOOD("Food"),
        WOOD("Wood"),
        STONE("Stone"),
        COAL("Coal"),
        IRON("Iron"),
        STEEL("Steel"),
        CEMENT("Cement"),
        LUMBER("Lumber"),
        TOOLS("Tools");

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public String getName() { return name; }
    }
}
