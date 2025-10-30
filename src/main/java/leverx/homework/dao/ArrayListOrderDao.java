package leverx.homework.dao;

import leverx.homework.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ArrayListOrderDao {
    private final List<Order> orders = new ArrayList<>();

    public synchronized void addOrder(Order order) {
        orders.add(order);
    }

    public List<Order> getAll() {
        return orders;
    }

    public void clear() {
        orders.clear();
    }
}
