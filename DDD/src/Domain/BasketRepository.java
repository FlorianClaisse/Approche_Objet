package Domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public interface BasketRepository {
    UUID save(Basket basket) throws IOException;
    Basket get(UUID id);
    boolean update(UUID id, Basket newValue);
}
