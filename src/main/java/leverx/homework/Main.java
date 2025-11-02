package leverx.homework;

import leverx.homework.factory.UserFactory;
import leverx.homework.model.Order;
import leverx.homework.model.Product;
import leverx.homework.service.AnalyticsService;
import leverx.homework.service.CheckOutService;
import leverx.homework.service.Warehouse;
import leverx.homework.user.Customer;


import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Warehouse warehouse = Warehouse.getInstance();
        CheckOutService checkOutService = CheckOutService.getInstance();

        Customer alice = (Customer) UserFactory.createUser("client", "Alice");
        Customer bob = (Customer) UserFactory.createUser("client", "Bob");
        Customer charlie = (Customer) UserFactory.createUser("client", "Charlie");

        Thread t1 = new Thread(() -> {
            Map<Product, Integer> items = new HashMap<>();
            items.put(warehouse.getProductByIndex(1), 1);
            items.put(warehouse.getProductByIndex(3), 2);
            checkOutService.placeOrder(new Order(alice, items));
        }, "AliceThread");

        Thread t2 = new Thread(() -> {
            Map<Product, Integer> items = new HashMap<>();
            items.put(warehouse.getProductByIndex(2), 3);
            checkOutService.placeOrder(new Order(bob, items));
        }, "BobThread");

        Thread t3 = new Thread(() -> {
            Map<Product, Integer> items = new HashMap<>();
            items.put(warehouse.getProductByIndex(1), 5);
            items.put(warehouse.getProductByIndex(4), 1);
            checkOutService.placeOrder(new Order(charlie, items));
        }, "CharlieThread");

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        Thread.sleep(5000);

        System.out.println("\n=== FINAL WAREHOUSE STATE ===");
        warehouse.getStock().forEach((p, q) -> System.out.println(p.getName() + " — " + q + " pcs"));

        checkOutService.shutdown();

        AnalyticsService.runAnalytics(checkOutService.getProcessedOrders());
    }
}
