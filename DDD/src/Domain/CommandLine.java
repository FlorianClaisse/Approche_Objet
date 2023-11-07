package Domain;

import java.util.Objects;
import java.util.UUID;

public final class CommandLine {
    private final String ref;
    private final Reference product;
    private int quantity;

    public CommandLine(String ref, Reference product, int quantity) {
        this.ref = UUID.randomUUID().toString();
        this.product = product;
        this.quantity = quantity;
    }

    public String getRef() { return ref; }
    public Reference getProductRef() { return product; }
    public int getQuantity() { return quantity; }
    public int getAmount() { return product.getPrix() * quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandLine that = (CommandLine) o;
        return Objects.equals(product.getRef(), that.product.getRef());
    }

    @Override public int hashCode() { return Objects.hash(product.getRef()); }
}
