package Infra;

import Domain.Basket;
import Domain.BasketRepository;

import java.util.ArrayList;
import java.util.List;

public class BasketRepository implements BasketRepository {
    private final List<Basket> baskets = new ArrayList<>();

    @Override public boolean save(Basket basket) {
        return baskets.add(basket);
    }
}
