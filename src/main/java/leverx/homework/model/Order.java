package leverx.homework.model;

import leverx.homework.user.Customer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
public class Order {
    private final Customer customer;
    private final Map<Product, Integer> items;
    private OrderStatus status;

    public Order(Customer customer, Map<Product, Integer> items) {
        this.customer = customer;
        this.items = items;
        this.status = OrderStatus.PENDING;
    }

    public BigDecimal getTotalPrice() {
        return items.entrySet().stream()
                .map(e -> e.getKey().getPrice().multiply(BigDecimal.valueOf(e.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
