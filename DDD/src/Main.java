import Domain.Basket;
import Domain.Reference;
import Infra.BasketRepositoryJSON;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Reference mac = new Reference("12345", "mac", "amac", 1200);
        Reference tableau = new Reference("12346", "tableau", "untableau", 50);

        Basket basket = new Basket();
        basket.addReference(mac, 4);
        basket.addReference(tableau, 40);

        BasketRepositoryJSON repo = new BasketRepositoryJSON();
        Basket retrieved = repo.get(repo.save(basket));
        System.out.println(retrieved.equals(basket));
    }
}