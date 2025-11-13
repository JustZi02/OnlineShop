package leverx.homework.service;

import leverx.homework.model.Order;
import leverx.homework.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnalyticsService {

    public static void runAnalytics(List<Order> orders) {
        System.out.println("\n=== ANALYTICS REPORT ===");

        long totalOrders = orders.size();
        BigDecimal totalProfit = orders.parallelStream()
                .map(Order::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<Product, Integer> productSales = orders.parallelStream()
                .flatMap(o -> o.getItems().entrySet().stream())
                .collect(Collectors.toConcurrentMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        Integer::sum));

        List<Map.Entry<Product, Integer>> topProducts = productSales.entrySet().stream()
                .sorted(Map.Entry.<Product, Integer>comparingByValue().reversed())
                .limit(3)
                .toList();

        System.out.println("Total orders: " + totalOrders);
        System.out.println("Total profit: " + totalProfit);
        System.out.println("Top 3 products:");
        topProducts.forEach(e -> System.out.println(" - " + e.getKey().getName() + ": " + e.getValue() + " sold"));
    }
}
