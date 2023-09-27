package TD2.Products;

import java.util.Objects;
import java.util.UUID;

/** A representation of a product. */
public abstract class Product {
    private final UUID id;
    private final String name;
    private final int quantity;

    public Product(Product product, int quantity) {
        this.id = product.id;
        this.name = product.name;
        this.quantity = quantity;
    }

    public Product(String name, int quantity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
    }

    public abstract boolean canBeSold();
    public abstract Product update(int quantity);

    public String getName() { return name; }
    public int getQuantity() { return quantity; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return quantity == product.quantity && id.equals(product.id) && name.equals(product.name);
    }

    @Override
    public int hashCode() { return Objects.hash(id, name, quantity); }

    @Override
    public String toString() { return "name: " + name + ", quantity: " + quantity; }
}
