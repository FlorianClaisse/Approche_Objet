package TD1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** A representation of a stock. */
public final class Stock {
    private final String name;
    private final String address;
    private final List<Product> products;

    /** Create a new instance of stock.
     * @param name The new stock name.
     * @param address The new stock address.
     * @param products The products present in the new stock.
     */
    Stock(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }

    /** Create a new instance of stock with empty product.
     * @param name The new stock name.
     * @param address The new stock address.
     */
    Stock(String name, String address) { this(name, address, new ArrayList<>()); }

    /** Get product in the stock by name.
     * @param name The name of the produt to retrieve.
     * @return The finding product, otherwise null.
     */
    public Product getProduct(String name) {
        for (Product product: products)
           if (product.getName().equals(name)) return product;

        return null;
    }

    /** Add new product in the stock.
     * @param product The product to add.
     */
    public boolean add(Product product) {
        for (Product product1: products) {
            if (product1.getName().equals(product.getName())) return false;
        }
        return products.add(product);
    }

    /** Remove a existing product in the stock.
     * @param product The product to remove.
     */
    public void remove(Product product) { products.remove(product); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(name, stock.name) && Objects.equals(address, stock.address) && Objects.equals(products, stock.products);
    }

    @Override
    public int hashCode() { return Objects.hash(name, address, products); }

    @Override
    public String toString() {
        return "name: " + name +
                ", address: " + address +
                ", products: " + products;
    }
}
