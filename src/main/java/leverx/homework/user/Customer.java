package leverx.homework.user;

import leverx.homework.dao.ArrayListProductDao;
import leverx.homework.model.Order;
import leverx.homework.model.Product;
import leverx.homework.service.CheckOutService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Customer implements User {
    private final String name;
    private final CheckOutService checkOutService;
    private final ArrayListProductDao productDao;

    public Customer(String name, CheckOutService checkOutService, ArrayListProductDao productDao) {
        this.name = name;
        this.checkOutService = checkOutService;
        this.productDao = productDao;
    }

    @Override
    public String getName() { return name; }

    @Override
    public void start() {
        menu();
    }

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
                    System.out.println("\nAvailable products:");
                    productDao.getAll().forEach(System.out::println);
                    break;

                case 2:
                    System.out.println("\nYour current order:");
                    if (cart.isEmpty()) System.out.println("Cart is empty.");
                    else cart.forEach((p, q) -> System.out.println(p.getName() + " x" + q));
                    break;

                case 3:
                    if (cart.isEmpty()) {
                        System.out.println("Cart is empty, nothing to checkout!");
                    } else {
                        Order order = new Order(this, cart);
                        checkOutService.checkout(order);
                        System.out.println("Order placed successfully!");
                        cart.clear();
                    }
                    break;

                case 4:
                    System.out.println("\nEnter product number to add:");
                    for (int i = 0; i < productDao.getAll().size(); i++) {
                        System.out.println((i + 1) + ". " + productDao.getAll().get(i));
                    }
                    int prodIndex = scanner.nextInt() - 1;
                    if (prodIndex < 0 || prodIndex >= productDao.getAll().size()) {
                        System.out.println("Invalid product index!");
                        break;
                    }
                    Product selected = productDao.getAll().get(prodIndex);
                    System.out.print("Enter quantity: ");
                    int qty = scanner.nextInt();
                    cart.merge(selected, qty, Integer::sum);
                    System.out.println("Added " + qty + " x " + selected.getName());
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
