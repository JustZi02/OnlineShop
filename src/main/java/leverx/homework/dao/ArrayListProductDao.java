package leverx.homework.dao;

import leverx.homework.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ArrayListProductDao {
    private final List<Product> products = new ArrayList<>();

    public ArrayListProductDao() {
        products.add(new Product("Laptop", BigDecimal.valueOf(1200)));
        products.add(new Product("Mouse", BigDecimal.valueOf(25)));
        products.add(new Product("Keyboard", BigDecimal.valueOf(70)));
        products.add(new Product("Monitor", BigDecimal.valueOf(300)));
    }

    public List<Product> getAll() {
        return products;
    }
}
