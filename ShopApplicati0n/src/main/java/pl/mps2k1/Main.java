package pl.mps2k1;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
   private final Scanner scanner = new Scanner(System.in);
    private final DBConnector dbConnector = new DBConnector();
    private final ShopService shopService = new ShopService(dbConnector,new ArrayList<>());

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
    public void run(){
        System.out.println("Witamy w sklepie!");
        boolean work = true;
        while (true){
            menu();
            int option = scanner.nextInt();
            switch (option){
                case 1 -> addProduct();
                case 2 ->getAll();
                case 3->addToShoppingCart();
                case 4->totalPrice();
                case 0 ->work=false;
            }
        }

    }
    public void menu(){
        System.out.println("Wybierz 1, jeśli chcesz dodać produkt do sklepu");
        System.out.println("Wybierz 2, jeśli chcesz sprawdzić liste dostępnych produktów");
        System.out.println("Wybierz 3, jeśli chcesz dodać produkt do koszyka");
        System.out.println("Wybierz 4, jeśli chcesz poznać pełną kwotę");
        System.out.println("Wybierz 0, jeśli chcesz zakończyć sesję...");

    }
    public void addProduct(){
        System.out.println("Nazwa produktu: ");
        String name = scanner.next();
        System.out.println("Cena produktu: ");
        double price = Double.parseDouble(scanner.next());
        System.out.println("Ilość: ");
        int quantity = Integer.parseInt(scanner.next());
        shopService.saveProduct(new Product(name,quantity,price));
    }
    public void getAll(){
        System.out.println("Wybrano listę dostępnych produktów");
        shopService.getAllProducts();
    }
    public void addToShoppingCart(){
        System.out.println("Nazwa produktu: ");
        String name = scanner.next();
        System.out.println("Ilość: ");
        int quantity = Integer.parseInt(scanner.next());
        shopService.addToShoppingCart(name,quantity);
    }
    public void totalPrice(){
        shopService.getTotalPrice();
    }
}