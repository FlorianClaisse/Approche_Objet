package Infra;

import Domain.Basket;
import Domain.BasketRepository;
import com.google.gson.Gson;

import java.io.*;
import java.util.UUID;

public class BasketRepositoryJSON implements BasketRepository {
    @Override public UUID save(Basket basket) throws IOException {
        UUID id = UUID.randomUUID();
        String json = new Gson().toJson(basket);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(id.toString() + ".json")));
        writer.write(json);
        writer.close();

        return id;
    }

    @Override public Basket get(UUID id) {
        try {
            File file = new File(id.toString() + ".json");
            if (!file.exists()) return null;
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line = reader.readLine();
            Basket basket = new Gson().fromJson(line, Basket.class);
            reader.close();
            return basket;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override public boolean update(UUID id, Basket newValue) {
        return false;
    }
}
