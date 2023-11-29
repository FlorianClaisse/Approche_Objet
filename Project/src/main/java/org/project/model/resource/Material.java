package org.project.model.resource;

import java.util.Objects;

public final class Material implements Purchasable {
    private final Type type;
    private final int price;

    public Material(Type type) {
        this.type = type;
        this.price = type.price;
    }

    @Override  public String getTypeName() { return this.type.rawValue; }
    @Override  public int getPrice() { return this.price; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Material material = (Material) o;
        return type == material.type;
    }

    @Override public int hashCode() { return Objects.hash(this.type); }

    @Override public String toString() { return this.type.rawValue + "(price=" + this.price + ")"; }

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
