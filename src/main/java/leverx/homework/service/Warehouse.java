package leverx.homework.service;

import leverx.homework.dao.BlockingQueueOrderDao;
import leverx.homework.dao.ConcurHashMapProductDao;
import leverx.homework.model.Order;
import leverx.homework.model.Product;

import java.util.concurrent.ConcurrentMap;

public class Warehouse {
    private final ConcurHashMapProductDao stock = ConcurHashMapProductDao.getInstance();
    private static Warehouse instance;
    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }
    public void addProduct(Product product, Integer quantity) {
        stock.addProduct(product, quantity);
    }

    public void reduceStock(Product product, Integer quantity) {
        stock.reduceStock(product, quantity);
    }

    public ConcurrentMap<Product, Integer> getStock() {
        return stock.getAll();
    }


}
