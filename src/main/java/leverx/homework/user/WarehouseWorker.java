package leverx.homework.user;

import leverx.homework.model.Order;
import leverx.homework.model.OrderStatus;
import leverx.homework.service.Warehouse;

import java.util.concurrent.BlockingQueue;

public class WarehouseWorker implements User {
    private final String name;
    private final BlockingQueue<Order> orderQueue;
    private final Warehouse warehouse;

    public WarehouseWorker(String name, BlockingQueue<Order> orderQueue, Warehouse warehouse) {
        this.name = name;
        this.orderQueue = orderQueue;
        this.warehouse = warehouse;
    }

    @Override
    public String getName() { return name; }

    @Override
    public void start() {
        menu();
    }

    @Override
    public void menu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== Warehouse Worker Menu ===");
            System.out.println("1. View pending orders");
            System.out.println("2. Process next order");
            System.out.println("3. View warehouse stock");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    if (orderQueue.isEmpty()) {
                        System.out.println("No orders in queue.");
                    } else {
                        System.out.println("\nOrders waiting for processing: " + orderQueue.size());
                        orderQueue.forEach(o ->
                                System.out.println("- Order from " + o.getCustomer().getName() + " [" + o.getStatus() + "]"));
                    }
                    break;

                case 2:
                    if (orderQueue.isEmpty()) {
                        System.out.println("No orders to process.");
                    } else {
                        try {
                            Order order = orderQueue.take();
                            System.out.println("\nProcessing order from " + order.getCustomer().getName() + "...");
                            order.getItems().forEach((p, qty) -> warehouse.reduceStock(p, qty));
                            order.setStatus(OrderStatus.PROCESSED);
                            System.out.println("✅ Order processed successfully!");
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            System.out.println("Processing interrupted.");
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n=== Current Warehouse Stock ===");
                    warehouse.getStock().forEach((p, qty) ->
                            System.out.println(p.getName() + " — " + qty + " pcs"));
                    break;

                case 4:
                    System.out.println("Exiting worker menu...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}

