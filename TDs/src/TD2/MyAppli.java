package TD2;

import java.util.Scanner;

public class MyAppli {
    private static final MyShop shop = new MyShop();
    private static final Scanner scanner = new Scanner(System.in);

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
        String name = startPrompt("Creation d'un nouveau stock", "Quel est le nom de ce stock");
        String address = userInput("Quel est l'addresse de votre stock:");
        endPrompt();

        if (!shop.addStock(name, address))
            System.out.println("----- Impossible de créer le stock -----");
    }

    /** Add product to a existing stock with user entry. */
    private static void addProductToStock() {
        String stockName = startPrompt("Ajout d'un nouveau produit dans un stock", "Quelle est le nom du stock dans lequelle vous voulez ajouter un produi:");

        if (!shop.stockExists(stockName)) {
            errorMessage("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        String productName = userInput("Le nom du nouveau produit :");
        String quantity = userInput("La quantity de ce produit présent dans le stock : ");
        int productQuantity = Integer.parseInt(quantity);

        if (!shop.addProduct(stockName, productName, productQuantity)) {
            errorMessage("----- Un produit avec le méme nom existe déjà dans ce stock. -----");
            return;
        }

        endPrompt();
    }

    /** Show product stock stats. */
    private static void showStockStats() {
        String stockName = startPrompt("Affichage des stats d'un stock.", "Quelle est le nom du stock dont vous voulez les stats:");

        if (!shop.stockExists(stockName)) {
            errorMessage("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        String choice = userInput("all/All pour afficher les stats de tous le stock ou le nom d'un produit : ");

        if (choice.equals("all") || choice.equals("All")) {
            System.out.println(shop.toString(stockName));
            endPrompt();
        } else System.out.println(shop.toString(stockName, choice));
    }

    /** Update an existing product quantity in a existing stock. */
    private static void updateProductQuantity () {
        String stockName = startPrompt("Mettre à jour la qauntité d'un produit dans un stock", "Dans quel stock voulez-vous modifier un produit:");

        if (!shop.stockExists(stockName)) {
            errorMessage("----- Aucun stock avec ce nom n'existe. -----");
            return;
        }

        System.out.println("Le nom du produit a modifier : ");
        String productName = scanner.nextLine();

        if (!shop.productExists(stockName, productName)) {
            errorMessage("----- Aucun produit avec ce nom n'existe. -----");
            return;
        }

        String value = userInput("Indiquez le quantité que vous voulez ajouter/retirer: ");
        int quantity = Integer.parseInt(value);

        endPrompt();

       shop.updateProduct(stockName, productName, quantity);
    }

    private static String startPrompt(String title, String message) {
        System.out.println(title);
        endPrompt();
        System.out.println(message);
        return scanner.nextLine();
    }

    private static String userInput(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }

    private static void errorMessage(String message) {
        endPrompt();
        System.out.println(message);
    }

    private static void endPrompt() {
        System.out.println("--------------------------------------------------------------");
    }
}
