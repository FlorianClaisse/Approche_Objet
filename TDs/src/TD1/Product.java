package TD1;

import java.util.Objects;
import java.util.UUID;

/** A representation of a product. */
public final class Product {
    private final UUID id;
    private final String name;
    private final int quantity;

    /** Create a new product from a old product instance with new quantity value.
     * @param product The old product instance.
     * @param quantity The new quantity value.
     */
    public Product(Product product, int quantity) {
        this.id = product.id;
        this.name = product.name;
        this.quantity = quantity;
    }

    /** Create a new product with name and quantity and random ID.
     * @param name The new name for the product.
     * @param quantity The new quantity for the product.
     */
    Product(String name, int quantity) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
    }

    /** Get the current product name.
     * @return The product name.
     */
    public String getName() { return name; }

    /** Get the current product quantity.
     * @return The product quantity.
     */
    public int getQuantity() { return quantity; }

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
        return "name: " + name +
                ", quantity: " + quantity;
    }
}
