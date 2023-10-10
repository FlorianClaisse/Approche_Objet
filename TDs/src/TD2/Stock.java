package TD2;

import TD2.Products.Product;

import java.util.HashMap;
import java.util.Objects;

public final class Stock extends HashMap<String, Product> {
    private final String name;
    private final String address;

    public Stock(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public boolean exists(String name) {
        return this.get(name) != null;
    }

    public int getQuantity(String name) {
        Product product = get(name);
        if (product != null) return product.getQuantity();
        return -1;
    }

    public void update(String name, int newQuantity) {
        if (exists(name)) {
            Product oldProduct = remove(name);
            put(oldProduct.getName(), oldProduct.update(newQuantity));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return name.equals(stock.name) && address.equals(stock.address) && super.equals(o);
    }

    @Override
    public int hashCode() { return Objects.hash(name, address); }

    @Override
    public String toString() { return "name: " + name + ", address: " + address + ", products : " + super.toString(); }
    public String toString(String productName) { return exists(productName) ? get(productName).toString() : ""; }
}
