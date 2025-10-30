package leverx.homework.user;

import leverx.homework.dao.ConcurHashMapProductDao;
import leverx.homework.model.Order;
import leverx.homework.model.Product;
import leverx.homework.service.CheckOutService;
import leverx.homework.service.Warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer implements User {
    private final String name;
    private final CheckOutService checkOutService = CheckOutService.getInstance();
    private final Warehouse warehouse = Warehouse.getInstance();

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }


    @Override
    public void menu() {
        Map<Product, Integer> cart = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (choice != 5) {
            System.out.println("\n=== Customer Menu ===");
            System.out.println("1. Product Catalog");
            System.out.println("2. Your Order");
            System.out.println("3. Check Out");
            System.out.println("4. Add Product to Cart");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    break;

                case 2:
                    break;

                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty, nothing to checkout!");
                    } else {
                    }
                    break;

                case 4:
                    System.out.println("\nEnter product number to add:");
                    break;

                case 5:
                    System.out.println("Exiting menu...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}
