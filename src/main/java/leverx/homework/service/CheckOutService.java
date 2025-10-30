package leverx.homework.service;

import leverx.homework.dao.BlockingQueueOrderDao;
import leverx.homework.model.Order;
import leverx.homework.model.OrderStatus;

import java.util.concurrent.BlockingQueue;

public class CheckOutService {

    private final BlockingQueueOrderDao queueOrder = BlockingQueueOrderDao.getInstance();
    private static CheckOutService instance;

    private CheckOutService() {}

    public static CheckOutService getInstance() {
        if (instance == null) {
            instance = new CheckOutService();
        }
        return instance;
    }

    public BlockingQueue<Order> getPendingOrders() {
        return queueOrder.getAll()
                .stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDING)
                .collect(
                        java.util.stream.Collectors.toCollection(
                                java.util.concurrent.LinkedBlockingQueue::new
                        )
                );
    }

    public void placeOrder(Order order) {
        queueOrder.placeOrder(order);
        System.out.println("Order from " + order.getCustomer().getName() + " added to queue.");
    }

    public void checkout(Order order) {
        queueOrder.checkoutOrder(order);
        System.out.println("Order from " + order.getCustomer().getName() + " was completed.");
    }
}
