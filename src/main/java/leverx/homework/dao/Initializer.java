package leverx.homework.dao;

import leverx.homework.model.Product;

import java.math.BigDecimal;

public class Initializer {
    public static void initialize()
    {
        ConcurHashMapProductDao productDao = ConcurHashMapProductDao.getInstance();
        productDao.addProduct(new Product("Laptop", BigDecimal.valueOf(1200)), 20);
        productDao.addProduct(new Product("Tablet", BigDecimal.valueOf(800)), 50);
        productDao.addProduct(new Product("Mouse", BigDecimal.valueOf(10)), 100);
        productDao.addProduct(new Product("KeyBoard", BigDecimal.valueOf(50)), 70);
    }
}
