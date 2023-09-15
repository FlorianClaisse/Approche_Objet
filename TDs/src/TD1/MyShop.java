package TD1;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/** TD1 Main class. */
public final class MyShop {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Stock> stocks = new HashMap<>();

    /** The main entry.
     * @param args The programme list arguments.
     */
    public static void main(String[] args) {
        showMenu();

        String input = scanner.nextLine();
        while(!input.equals("0")) {
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

    /** Show context menu. */
    private static void showMenu() {
        System.out.println("""
                -------------------------------------------------------------
                - [1] Créer un stock.
                - [2] Ajouter des produits dans le stock.
                - [3] Afficher les caractéristiques d'un stock.
                - [4] Ajouter/Retirer une quantité d'un produit dans un stock.
                - [0] Quitter le programme.
                --------------------------------------------------------------
                """);
    }

    /** Create new stock with user entry. */
    private static void createStock() {
        System.out.println("""
                Creation d'un nouveau stock
                --------------------------------------------------------------
                Quel est le nom de ce stock:
                """);
        String name = scanner.nextLine();

        System.out.println("Quel est l'addresse de votre stock:");
        String address = scanner.nextLine();

        System.out.println("--------------------------------------------------------------");

        if (name.isBlank() || address.isBlank()) {
            System.out.println("----- Impossible de créer un stock avec un nom ou une addresse vide. -----");
            return;
        }

        stocks.put(name, new Stock(name, address));
    }

    /** Add product to a existing stock with user entry. */
    private static void addProductToStock() {
        System.out.println("""
                Ajout d'un nouveau produit dans un stock
                --------------------------------------------------------------
                Quelle est le nom du stock dans lequelle vous voulez ajouter un produit:
                """);
        String stockName = scanner.nextLine();

        Stock stock = stocks.get(stockName);
        if (stock == null) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Le nom du nouveau produit : ");
        String productName = scanner.nextLine();

        System.out.println("La quantity de ce produit présent dans le stock : ");
        String quantity = scanner.nextLine();

        System.out.println("--------------------------------------------------------------");

        int productQuantity = Integer.parseInt(quantity);
        if (!stock.add(new Product(productName, productQuantity))) {
            System.out.println("----- Un produit avec le mé^me nom existe déjà dans ce stock. -----");
            return;
        }
    }

    /** Show product stock stats. */
    private static void showStockStats() {
        System.out.println("""
                Affichage des stats d'un stock.
                --------------------------------------------------------------
                Quelle est le nom du stock dont vous voulez les stats:
                """);
        String stockName = scanner.nextLine();

        Stock stock = stocks.get(stockName);
        if (stock == null) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("all/All pour afficher les stats de tous le stock ou le nom d'un produit : ");
        String choice = scanner.nextLine();

        System.out.println("--------------------------------------------------------------");

        if (choice.equals("all") || choice.equals("All")) System.out.println(stocks.values());
        else {
            Product product = stock.getProduct(choice);
            if (product == null)
                System.out.println("----- Aucun produit avec ce nom n'existe. -----");
            else
                System.out.println(product);
        }
    }

    /** Update a existing product quantity in a existing stock. */
    private static void updateProductQuantity () {
        System.out.println("""
                Mettre à jour la qauntité d'un produit dans un stock
                "--------------------------------------------------------------"
                Dans quel stock voulez-vous modifier un produit:""");
        String stockName = scanner.nextLine();

        Stock stock = stocks.get(stockName);
        if (stock == null) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Le nom du produit a modifier : ");
        String productName = scanner.nextLine();

        Product product = stock.getProduct(productName);
        if (product == null) {
            System.out.println("--------------------------------------------------------------");
            System.out.println("----- Aucun produit avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Indiquez le quantité que vous voulez ajouter/retirer: ");
        String value = scanner.nextLine();
        int quantity = Integer.parseInt(value);

        System.out.println("--------------------------------------------------------------");

        stock.remove(product);
        stock.add(new Product(product, product.getQuantity() + quantity));
    }
}
