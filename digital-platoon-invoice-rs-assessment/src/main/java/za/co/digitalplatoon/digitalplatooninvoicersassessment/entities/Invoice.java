package za.co.digitalplatoon.digitalplatooninvoicersassessment.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
public class Invoice {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String client;
    private Long vatRate;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date invoiceDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "liteItemFk")
    private List<LineItem> lineItems;

    public BigDecimal getSubTotal() {
        double total = 0.0;
        for (LineItem lineItem : getLineItems()) {
            total = total + lineItem.getLineItemTotal().doubleValue();
        }
        return BigDecimal.valueOf(total).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getVat() {
        return BigDecimal.valueOf(this.vatRate).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal getTotal() {
        //Now adding vat to the total to get grandTotal
        double grandTotal = getSubTotal().doubleValue() * (1 + ((getVat().doubleValue()) / 100));
        return BigDecimal.valueOf(grandTotal).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
