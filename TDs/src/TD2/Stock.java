package TD2;

import java.util.HashMap;
import java.util.Objects;

public final class Stock {
    private final String name;
    private final String address;
    private final HashMap<String, Product> products = new HashMap<>();

    public Stock(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public boolean productExists(String name) {
        return products.get(name) != null;
    }

    public int getProductQuantity(String name) {
        Product product = products.get(name);
        if (product != null) return product.getQuantity();
        return -1;
    }

    public boolean addProduct(String name, int quantity) {
        if (productExists(name)) return false;
        products.put(name, new Product(name, quantity));
        return true;
    }

    public boolean updateProduct(String name, int newQuantity) {
        if (productExists(name)) {
            Product oldProduct = products.remove(name);
            products.put(oldProduct.getName(), new Product(oldProduct, oldProduct.getQuantity() + newQuantity));
            return true;
        }
        return false;
    }

    public void removeProduct(String name) { products.remove(name); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return name.equals(stock.name) && address.equals(stock.address) && products.equals(stock.products);
    }

    @Override
    public int hashCode() { return Objects.hash(name, address, products); }

    @Override
    public String toString() { return "name: " + name + ", address: " + address + ", products: " + products; }
    public String toString(String productName) { return productExists(productName) ? products.get(productName).toString() : ""; }
}
