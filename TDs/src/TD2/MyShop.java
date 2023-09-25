package TD2;

import java.util.HashMap;
import java.util.Map;

/** TD2 Main class. */
public final class MyShop {
    private final Map<String, Stock> stocks = new HashMap<>();

    public boolean stockExists(String name) { return stocks.get(name) != null; }
    public boolean productExists(String stockName, String productName) { return stockExists(stockName) && stocks.get(stockName).productExists(productName); }

    public boolean addStock(String name, String address) {
        if (name.isBlank() || address.isBlank() || stocks.get(name) != null) return false;

        stocks.put(name, new Stock(name, address));
        return true;
    }

    public boolean addProduct(String stockName, String productName, int quantity) {
        if (stockExists(stockName)) {
            stocks.get(stockName).addProduct(productName, quantity);
            return true;
        }
        return false;
    }

    public boolean updateProduct(String stockName, String productName, int udpatedQuantity) {
        if (stockExists(stockName) && stocks.get(stockName).productExists(productName)) {
            return stocks.get(stockName).updateProduct(productName, udpatedQuantity);
        }
        return false;
    }

    public void removeProduct(String stockName, String productName) {
        stocks.get(stockName).removeProduct(productName);
    }

    public String toString(String stockName) { return stockExists(stockName) ? stocks.get(stockName).toString() : ""; }
    public String toString(String stockName, String productName) { return (stockExists(stockName)) ? stocks.get(stockName).toString(productName) : ""; }
}
