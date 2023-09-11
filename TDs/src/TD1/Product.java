package TD1;

import java.util.Objects;
import java.util.UUID;

public final class Product {
    private final UUID id;
    private final String name;
    private int quantity;

    Product(String name, int quantity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
    }


    public String getName() { return name; }

    public void add(int quantity) {
        if (quantity > 0) this.quantity += quantity;
    }

    public void remove(int quantity) {
        if (quantity < 0) this.quantity += quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, quantity);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
