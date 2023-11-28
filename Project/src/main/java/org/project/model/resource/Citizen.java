package org.project.model.resource;

public final class Citizen extends Resource {
    public Citizen(int quantity) {
        this.addQuantity(quantity);
    }

    @Override
    public String toString() {
        return "Citizen(quantity=" + this.getQuantity() + ")";
    }
}
