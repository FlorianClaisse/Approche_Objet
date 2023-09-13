package TD1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public final class MyShop {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Stock> stocks = new HashMap<>();

    public static void main(String[] args) {
        showMenu();

        String input = scanner.nextLine();
        while(!input.equals("q")) {
            switch (input) {
                case "1" -> createStock();
                case "2" -> addProductToStock();
                case "3" -> showStockStats();
                case "4" -> updateProductQuantity();
                default -> System.out.println("Mauvaise entrée.");
            }
            showMenu();
            input = scanner.nextLine();
        }
    }

    private static void showMenu() {
        System.out.println("""
                ------------------------------------------------------------
                1 - Créer un stock.
                2 - Ajouter des produits dans le stock.
                3 - Afficher les caractéristiques d'un stock.
                4 - Ajouter/Retirer une quantité d'un produit dans un stock.
                q - Quitter le programme.
                -------------------------------------------------------------
                """);
    }

    private static void createStock() {
        System.out.println("Quel est le nom de ce stock : ");
        String name = scanner.nextLine();
        System.out.println("Quel est l'addresse de votre stock :");
        String address = scanner.nextLine();

        if (name.isBlank() || address.isBlank()) {
            System.out.println("----- Impossible de créer un stock avec un nom ou une addresse vide. -----");
            return;
        }

        stocks.put(name, new Stock(name, address));
    }

    private static void addProductToStock() {
        System.out.println("Quelle est le nom du stock dans lequelle vous voulez ajouter un produit : ");
        String stockName = scanner.nextLine();

        Stock stock = stocks.get(stockName);
        if (stock == null) {
            System.out.println("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Le nom du nouveau produit : ");
        String productName = scanner.nextLine();
        System.out.println("La quantity de ce produit présent dans le stock : ");
        String quantity = scanner.nextLine();
        int productQuantity = Integer.parseInt(quantity);

        stock.add(new Product(productName, productQuantity));
    }

    private static void showStockStats() {
        System.out.println("Quelle est le nom du stock dont vous voulez les stats : ");
        String stockName = scanner.nextLine();

        Stock stock = stocks.get(stockName);
        if (stock == null) {
            System.out.println("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("all/All pour afficher les stats de tous le stock ou le nom d'un produit : ");
        String choice = scanner.nextLine();

        if (choice.equals("all") || choice.equals("All")) System.out.println(stocks.values());
        else {
            Product product = stock.getProduct(choice);
            if (product == null)
                System.out.println("----- Aucun produit avec ce nom n'existe. -----");
            else
                System.out.println(product);
        }
    }

    private static void updateProductQuantity () {
        System.out.println("Dans quel stock voulez-vous modifier un produit : ");
        String stockName = scanner.nextLine();

        Stock stock = stocks.get(stockName);
        if (stock == null) {
            System.out.println("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Le nom du produit a modifier : ");
        String productName = scanner.nextLine();

        Product product = stock.getProduct(productName);
        if (product == null) {
            System.out.println("----- Aucun produit avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Indiquez le quantité que vous voulez ajouter/retirer: ");
        String value = scanner.nextLine();
        int quantity = Integer.parseInt(value);

        stock.remove(product);
        stock.add(new Product(product, product.getQuantity() + quantity));
    }
}
