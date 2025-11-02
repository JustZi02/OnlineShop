package leverx.homework.service;

import leverx.homework.dao.BlockingQueueOrderDao;
import leverx.homework.model.Order;
import leverx.homework.model.OrderStatus;
import lombok.Getter;

import java.util.List;
import java.util.concurrent.*;

public class CheckOutService {

    private final BlockingQueueOrderDao queueOrder = BlockingQueueOrderDao.getInstance();
    @Getter
    private final List<Order> processedOrders = new CopyOnWriteArrayList<>();
    private final ExecutorService executor = Executors.newFixedThreadPool(3);
    private static CheckOutService instance;
    private volatile boolean running = true;

    private final Warehouse warehouse = Warehouse.getInstance();

    private CheckOutService() {
        startOrderProcessing();
    }

    public static synchronized CheckOutService getInstance() {
        if (instance == null) {
            instance = new CheckOutService();
        }
        return instance;
    }

    public void placeOrder(Order order) {
        synchronized (this) {
            queueOrder.placeOrder(order);
            System.out.println("ADDED TO QUEUE || Order from " + order.getCustomer().name() + " added to queue.");
        }
    }

    private void startOrderProcessing() {
        Runnable worker = () -> {
            while (running) {
                try {
                    Order order = queueOrder.getAll().take();
                    processOrder(order);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        };
        for (int i = 0; i < 3; i++) {
            executor.submit(worker);
        }
    }

    private void processOrder(Order order) {
        synchronized (this) {
            System.out.println("PROCESSING || Processing order from " + order.getCustomer().name() + "...");

            order.getItems().forEach(warehouse::reduceStock);

            order.setStatus(OrderStatus.PROCESSED);

            processedOrders.add(order);


            System.out.println("PROCESSED || Order for " + order.getCustomer().name() +
                    " processed successfully (Total: " + order.getTotalPrice() + " BYN)");
        }
    }

    public void shutdown() {
        running = false;
        executor.shutdownNow();
        System.out.println("Checkout service stopped.");
    }
}
