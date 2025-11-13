package leverx.homework.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode(of = "name")
@AllArgsConstructor
public class Product {
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return name + "\t" + price + "byn";
    }
}
