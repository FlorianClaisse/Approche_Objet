package TD2.Products;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public final class FoodProduct extends Product {
    private final LocalDate expirationDate;

    public FoodProduct(FoodProduct product, int quantity) {
        super(product, quantity);
        this.expirationDate = product.expirationDate;
    }

    public FoodProduct(String name, String expiationDate, int quantity) {
        super(name, quantity);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        this.expirationDate = LocalDate.parse(expiationDate, formatter);
    }

    @Override
    public boolean canBeSold() {
        LocalDate currentDate = LocalDate.now();
        Period diff = Period.between(expirationDate, currentDate);
        return diff.getDays() > 3;
    }

    @Override
    public Product update(int quantity) {
        return new FoodProduct(this, getQuantity() + quantity);
    }

    @Override
    public String toString() {
        return super.toString() + ", expirationDate: " + expirationDate;
    }
}
