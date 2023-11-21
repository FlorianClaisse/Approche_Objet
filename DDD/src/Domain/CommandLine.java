package Domain;

import java.util.Objects;
import java.util.UUID;

public final class CommandLine {
    private final String ref;
    private final Reference product;
    private int quantity;

    public CommandLine(Reference product, int quantity) {
        this.ref = UUID.randomUUID().toString();
        this.product = product;
        this.quantity = quantity;
    }

    public String getRef() { return ref; }
    public Reference getProductRef() { return product; }
    public int getQuantity() { return quantity; }
    public int getAmount() { return product.getPrix() * quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
