package leverx.homework.dao;

import leverx.homework.model.Product;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurHashMapProductDao {
    private final ConcurrentHashMap<Product, Integer> products = new ConcurrentHashMap<>();
    private static ConcurHashMapProductDao instance;

    public static ConcurHashMapProductDao getInstance() {
        if (instance == null) {
            instance = new ConcurHashMapProductDao();
        }
        return instance;
    }

    private ConcurHashMapProductDao() {
    }

    public void addProduct(Product product, Integer quantity) {
        products.put(product, quantity);
    }
    public ConcurrentHashMap<Product, Integer> getAll() {
        return products;
    }
    public void reduceStock(Product product, Integer quantity) {
        products.computeIfPresent(product, (p, currentQty) -> {
            int newQty = currentQty - quantity;
            if (newQty < 0) {
                System.out.println("⚠️ Недостаточно товара на складе: " + p.getName());
                return 0;
            }
            return newQty;
        });
    }
}
