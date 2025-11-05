package leverx.homework.dao;

import leverx.homework.model.Product;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurHashMapProductDao {
    private final ConcurrentHashMap<Product, Integer> products = new ConcurrentHashMap<>();
    private static ConcurHashMapProductDao instance;

    public static synchronized ConcurHashMapProductDao getInstance() {
        if (instance == null) {
            instance = new ConcurHashMapProductDao();
        }
        return instance;
    }

    private ConcurHashMapProductDao() {
    }

    public void addProduct(Product product, Integer quantity) {
        synchronized (this) {
            products.put(product, quantity);
            System.out.printf("WAREHOUSE || Added %s x %s to warehouse%n", quantity, product.getName());
            }
    }

    public ConcurrentHashMap<Product, Integer> getAll() {
        return products;
    }

    public void reduceStock(Product product, Integer quantity) {
        synchronized (this) {
            products.computeIfPresent(product, (p, currentQty) -> {
                int newQty = currentQty - quantity;
                if (newQty < 0) {
                    System.out.println("We are out of stock now: " + p.getName());
                    return 0;
                }
                return newQty;
            });
        }
    }
}
