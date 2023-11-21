package Infra;

import Domain.Basket;
import Domain.BasketRepository;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;

public class BasketRepositoryInMemory implements BasketRepository {
    private final Map<UUID, Basket> baskets = new HashMap<>();

    @Override public UUID save(Basket basket) {
        UUID id = UUID.randomUUID();
        baskets.put(id, basket);
        return id;
    }

    @Override public Basket get(UUID id) {
        return baskets.get(id);
    }

    @Override public boolean update(UUID id, Basket newValue) {
        Basket basket = baskets.get(id);
        if (basket == null) return false;
        baskets.put(id, basket);
        return true;
    }
}
