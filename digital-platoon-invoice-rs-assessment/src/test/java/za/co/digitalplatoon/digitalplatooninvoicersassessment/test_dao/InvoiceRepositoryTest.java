package za.co.digitalplatoon.digitalplatooninvoicersassessment.test_dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.dao.InvoiceRepository;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.LineItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InvoiceRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Before
    public void setup() {
        initialize();
    }

    private void initialize() {
        Invoice invoice = getInvoice();
        testEntityManager.persist(invoice);
        System.err.println(invoice);
        testEntityManager.flush();
    }

    @Test
    public void whenFindById_thenReturnInvoiceTest() {
        Long invoiceId = 1L;    //The first generated invoiceId value in the DB should be 1
        Invoice invoice = invoiceRepository.getOne(invoiceId);
        assertThat(invoice.getId()).isEqualTo(invoiceId);
    }

    @Test
    public void whenFindById_thenReturnCorrectNumberOfLineItemsTest() {
        Invoice invoice = invoiceRepository.findAll().get(0);       //Get the only Invoice in the repo as initialized in the initialize() method
        int expectedLineItems = 2;  //Should return two lineItems as initialized in the initiaze() method above
        assertThat(invoice.getLineItems().size()).isEqualTo(expectedLineItems);
    }

    @Test
    public void whenInvoiceRepositoryIsNotEmpty_thenReturnAllInvoicesTest() {
        List<Invoice> invoices = invoiceRepository.findAll();
        assertThat(invoices.size()).isGreaterThan(0);
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

        invoice.setLineItems(lineItems);
        invoice.setInvoiceDate(new Date());
        return invoice;
    }

}
