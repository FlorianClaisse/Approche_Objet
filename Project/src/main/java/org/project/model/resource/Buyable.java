package org.project.model.resource;

public abstract class Buyable extends Resource {
    private final Gold price;

    public Buyable(int price) {
        if (price < 0) throw new IllegalArgumentException("price can't be less than 0.");
        this.price = new Gold(price);
    }

    public final int getPrice() { return this.price.getQuantity(); }
}
