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
        System.out.println("\n" + name + " started processing orders...");
        while (!orderQueue.isEmpty()) {
            try {
                Order order = orderQueue.take();
                order.getItems().forEach((p, qty) -> warehouse.reduceStock(p, qty));
                order.setStatus(OrderStatus.PROCESSED);
                System.out.println("Processed order from " + order.getCustomer().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(name + " finished processing orders.");
    }
}

