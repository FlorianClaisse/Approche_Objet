package TD2;

import TD2.Products.FoodProduct;
import TD2.Products.SanitaryProduct;

import java.util.HashMap;
import java.util.Map;

/** TD2 Main class. */
public final class MyShop {
    private final Map<String, Stock> stocks = new HashMap<>();

    public boolean stockExists(String name) { return stocks.get(name) != null; }
    public boolean productExists(String stockName, String productName) { return stockExists(stockName) && stocks.get(stockName).exists(productName); }

    public boolean addStock(String name, String address) {
        if (name.isBlank() || address.isBlank() || stocks.get(name) != null) return false;

        stocks.put(name, new Stock(name, address));
        return true;
    }

    public boolean addFood(String stockName, String productName, String date, int quantity) {
        if (stockExists(stockName)) {
            stocks.get(stockName).put(productName, new FoodProduct(productName, date, quantity));
            return true;
        }
        return false;
    }

    public boolean addSanitary(String stockName, String productName, int quantity) {
        if (stockExists(stockName)) {
            stocks.get(stockName).put(productName, new SanitaryProduct(productName, quantity));
            return true;
        }
        return false;
    }

    public void updateProduct(String stockName, String productName, int quantity) {
        if (stockExists(stockName) && stocks.get(stockName).exists(productName)) {
            stocks.get(stockName).update(productName, quantity);
        }
    }

    public void removeProduct(String stockName, String productName) {
        stocks.get(stockName).remove(productName);
    }

    public String toString(String stockName) { return stockExists(stockName) ? stocks.get(stockName).toString() : ""; }
    public String toString(String stockName, String productName) { return (stockExists(stockName)) ? stocks.get(stockName).toString(productName) : ""; }
}
