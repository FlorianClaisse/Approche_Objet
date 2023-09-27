package TD2.Products;

public final class SanitaryProduct extends Product {

    public SanitaryProduct(SanitaryProduct product, int quantity) { super(product, quantity); }
    public SanitaryProduct(String name, int quantity) { super(name, quantity); }

    @Override
    public boolean canBeSold() { return true; }

    @Override
    public Product update(int quantity) { return new SanitaryProduct(this, getQuantity() + quantity); }
}
