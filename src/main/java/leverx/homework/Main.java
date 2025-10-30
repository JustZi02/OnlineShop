package leverx.homework;

import leverx.homework.factory.UserFactory;

import leverx.homework.user.User;

public class Main {
    public static void main(String[] args) {

            System.out.println("Choose your role: ");
            System.out.println("1. Client");
            System.out.println("2. Worker");

            int choice = new java.util.Scanner(System.in).nextInt();
            String name = new java.util.Scanner(System.in).nextLine();
            User user = switch (choice) {
                    case 1 -> UserFactory.createUser("Client", name);
                    case 2 -> UserFactory.createUser("Worker", name);
                    default -> throw new IllegalArgumentException("Неверный выбор");
            };
            user.menu();
    }
}