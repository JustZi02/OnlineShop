package leverx.homework.user;

import leverx.homework.service.CheckOutService;

public class WarehouseWorker implements User {
    private final String name;
    private final CheckOutService checkOutService = CheckOutService.getInstance();

    public WarehouseWorker(String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }

    @Override
    public void menu() {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n=== Warehouse Worker Menu ===");
            System.out.println("1. View pending orders");
            System.out.println("2. Process next order");
            System.out.println("3. View warehouse stock");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println(checkOutService.getPendingOrders());
                    break;

                case 2:

                    break;

                case 3:

                    break;

                case 4:
                    System.out.println("Exiting worker menu...");
                    break;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

}

