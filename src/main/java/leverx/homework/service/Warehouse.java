package leverx.homework.service;

import leverx.homework.dao.ConcurHashMapProductDao;
import leverx.homework.dao.InitializeProducts;
import leverx.homework.model.Product;

import java.util.concurrent.ConcurrentMap;

public class Warehouse {
    private final ConcurHashMapProductDao stock = ConcurHashMapProductDao.getInstance();

    private static Warehouse instance;

    private Warehouse() {
        InitializeProducts.initialize();
    }

    public static synchronized Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }


    public void reduceStock(Product product, Integer quantity) {
        synchronized (this) {
            stock.reduceStock(product, quantity);
            System.out.println("WAREHOUSE || Reduced " + quantity + " x " + product.getName());
        }
    }

    public ConcurrentMap<Product, Integer> getStock() {
        return stock.getAll();
    }

    public ConcurHashMapProductDao getStockProduct() {
        return stock;
    }

    public Product getProductByIndex(int index) {
        return stock.getAll().keySet()
                .stream()
                .skip(index - 1)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Product with index " + index + " not found"));
    }

}
