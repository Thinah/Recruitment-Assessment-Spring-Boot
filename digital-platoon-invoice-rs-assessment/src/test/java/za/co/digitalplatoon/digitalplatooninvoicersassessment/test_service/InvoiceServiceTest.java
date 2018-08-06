package za.co.digitalplatoon.digitalplatooninvoicersassessment.test_service;

import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.dao.InvoiceRepository;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.LineItem;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.services.InvoiceService;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.services.InvoiceServiceImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.Silent.class)
public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @Qualifier("invoiceServiceImpl")
    @Autowired
    private InvoiceService invoiceService;

    @Before
    public void setup() {
        this.invoiceService = new InvoiceServiceImpl(invoiceRepository);
        Invoice invoice = getInvoice();
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        invoiceRepository.save(invoice);
    }

    @Test
    public void whenInvoiceDoesNotExist_thenAddInvoiceTest() {
        Invoice invoice = getInvoice();
        given(invoiceService.addInvoice(invoice)).willReturn(invoice);
        Invoice dbInvoice = invoiceService.addInvoice(invoice);
        assertThat(dbInvoice.getClient()).isEqualTo(invoice.getClient());
    }

    private Invoice getInvoice() {
        Invoice invoice = new Invoice();
        invoice.setClient("Momentum");
        invoice.setVatRate((long) 15.00);
        List<LineItem> lineItems = new ArrayList<>();
        LineItem item1 = new LineItem();
        item1.setDescription("August main Invoicing");
        item1.setQuantity((long) 3);
        item1.setUnitPrice(BigDecimal.valueOf(100.00));
        lineItems.add(item1);

        LineItem item2 = new LineItem();
        item2.setDescription("August additional Invoicing");
        item2.setQuantity((long) 2);
        item2.setUnitPrice(BigDecimal.valueOf(100.00));
        lineItems.add(item2);

        invoice.setId(1L);
        invoice.setLineItems(lineItems);
        invoice.setInvoiceDate(new Date());
        return invoice;
    }

}
