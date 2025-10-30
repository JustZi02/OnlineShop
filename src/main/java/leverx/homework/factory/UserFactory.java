package leverx.homework.factory;


import leverx.homework.dao.ArrayListProductDao;
import leverx.homework.service.CheckOutService;
import leverx.homework.service.Warehouse;
import leverx.homework.user.Customer;
import leverx.homework.user.User;
import leverx.homework.user.WarehouseWorker;

import java.util.concurrent.BlockingQueue;
public class UserFactory {
    public static User createUser(String role, String name,
                                  CheckOutService checkOutService,
                                  ArrayListProductDao productDao,
                                  BlockingQueue orderQueue,
                                  Warehouse warehouse) {
        return switch (role.toLowerCase()) {
            case "client", "customer" -> new Customer(name, checkOutService, productDao);
            case "worker", "employee" -> new WarehouseWorker(name, orderQueue, warehouse);
            default -> throw new IllegalArgumentException("Unknown role: " + role);
        };
    }
}
