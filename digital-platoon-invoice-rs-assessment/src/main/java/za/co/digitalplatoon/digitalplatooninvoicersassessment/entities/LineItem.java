package za.co.digitalplatoon.digitalplatooninvoicersassessment.entities;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class LineItem {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;

    public BigDecimal getLineItemTotal() {
        double unitPrice = this.unitPrice.doubleValue();
        BigDecimal total = BigDecimal.valueOf(unitPrice * quantity);
        BigDecimal lineItemTotal = total.setScale(2, total.ROUND_HALF_UP);
        return lineItemTotal;
    }
}
