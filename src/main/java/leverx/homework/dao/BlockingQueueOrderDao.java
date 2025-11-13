package leverx.homework.dao;

import leverx.homework.model.Order;
import leverx.homework.model.OrderStatus;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueOrderDao {
    private final BlockingQueue<Order> orders = new LinkedBlockingQueue<>();
    private static BlockingQueueOrderDao instance;

    public static synchronized BlockingQueueOrderDao getInstance() {
        if (instance == null) {
            instance = new BlockingQueueOrderDao();
        }
        return instance;
    }

    public synchronized void placeOrder(Order order) {
        order.setStatus(OrderStatus.PENDING);
        orders.add(order);
    }

    public BlockingQueue<Order> getAll() {
        return orders;
    }
}
