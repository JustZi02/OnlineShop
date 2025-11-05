package leverx.homework.factory;

import leverx.homework.user.Customer;
import leverx.homework.user.User;
import leverx.homework.user.WarehouseWorker;

public class UserFactory {
    public static User createUser(UserType type, String name) {
        return switch (type) {
            case CLIENT -> new Customer(name);
            case WORKER -> new WarehouseWorker(name);
        };
    }
}
