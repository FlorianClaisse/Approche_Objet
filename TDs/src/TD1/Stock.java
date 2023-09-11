package TD1;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class Stock {
    private final String name;
    private final String address;
    private final List<Product> products;

    Stock(String name, String address, List<Product> products) {
        this.name = name;
        this.address = address;
        this.products = products;
    }

    Stock(String name, String address) { this(name, address, new ArrayList<>()); }

    public Product getProduct(String name) {
        for (Product product: products)
           if (product.getName().equals(name)) return product;

        return null;
    }

    public void add(Product product) {
        for (Product product1: products) {
            if (product1.getName().equals(product.getName())) return;
        }
        products.add(product);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(name, stock.name) && Objects.equals(address, stock.address) && Objects.equals(products, stock.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, products);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", products=" + products +
                '}';
    }
}
