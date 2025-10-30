package leverx.homework;

import leverx.homework.dao.ArrayListOrderDao;
import leverx.homework.dao.ArrayListProductDao;
import leverx.homework.factory.UserFactory;
import leverx.homework.model.Order;
import leverx.homework.service.AnalyticsService;
import leverx.homework.service.CheckOutService;
import leverx.homework.service.Warehouse;
import leverx.homework.user.User;

import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    public static void main(String[] args) {
            ArrayListProductDao productDao = new ArrayListProductDao();
            ArrayListOrderDao orderDao = new ArrayListOrderDao();
            Warehouse warehouse = new Warehouse();
            productDao.getAll().forEach(p -> warehouse.addProduct(p, 10));

            BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
            CheckOutService checkOutService = new CheckOutService(orderDao, orderQueue);
            AnalyticsService analyticsService = new AnalyticsService();

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter your role (customer / worker): ");
            String role = scanner.nextLine();
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();

            User user = UserFactory.createUser(role, name, checkOutService, productDao, orderQueue, warehouse);
            user.start();

            if (role.equalsIgnoreCase("worker")) {
                analyticsService.runAnalytics(orderDao.getAll());
            };
    }
}