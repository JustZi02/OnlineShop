package leverx.homework.service;

import leverx.homework.model.Product;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Warehouse {
    private final ConcurrentMap<Product, Integer> stock = new ConcurrentHashMap<>();

    public Warehouse() {}

    public void addProduct(Product product, int quantity) {
        stock.put(product, quantity);
    }

    public boolean reduceStock(Product product, int quantity) {
        return stock.computeIfPresent(product, (p, oldQty) -> oldQty >= quantity ? oldQty - quantity : oldQty) >= 0;
    }

    public ConcurrentMap<Product, Integer> getStock() {
        return stock;
    }
}
