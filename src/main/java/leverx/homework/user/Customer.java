package leverx.homework.user;

import leverx.homework.dao.ArrayListProductDao;
import leverx.homework.model.Order;
import leverx.homework.model.Product;
import leverx.homework.service.CheckOutService;

import java.util.HashMap;
import java.util.Map;

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
        System.out.println("\nWelcome, " + name + "! Here’s our catalog:");
        productDao.getAll().forEach(System.out::println);

        Map<Product, Integer> cart = new HashMap<>();
        cart.put(productDao.getAll().get(0), 1);
        cart.put(productDao.getAll().get(1), 2);

        Order order = new Order(this, cart);
        checkOutService.checkout(order);
    }
}
