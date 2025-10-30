package leverx.homework.service;

import leverx.homework.dao.ArrayListOrderDao;
import leverx.homework.model.Order;

import java.util.concurrent.BlockingQueue;

public class CheckOutService {
    private final ArrayListOrderDao orderDao;
    private final BlockingQueue<Order> orderQueue;

    public CheckOutService(ArrayListOrderDao orderDao, BlockingQueue<Order> orderQueue) {
        this.orderDao = orderDao;
        this.orderQueue = orderQueue;
    }

    public void checkout(Order order) {
        orderDao.addOrder(order);
        orderQueue.offer(order);
        System.out.println("Order from " + order.getCustomer().getName() + " added to queue.");
    }
}
