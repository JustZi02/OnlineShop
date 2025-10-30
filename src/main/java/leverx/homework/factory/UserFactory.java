package leverx.homework.factory;

import leverx.homework.user.Customer;
import leverx.homework.user.User;
import leverx.homework.user.WarehouseWorker;

public class UserFactory {
    public static User createUser(String type, String name) {
        return switch (type.toLowerCase()) {
            case "client" -> new Customer(name);
            case "worker" -> new WarehouseWorker(name);
            default -> throw new IllegalArgumentException("Unknown user type: " + type);
        };
    }
}
