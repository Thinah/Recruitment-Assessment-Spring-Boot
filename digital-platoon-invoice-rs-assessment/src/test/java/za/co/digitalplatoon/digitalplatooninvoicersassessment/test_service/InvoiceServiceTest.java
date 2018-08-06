package za.co.digitalplatoon.digitalplatooninvoicersassessment.test_service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.dao.InvoiceRepository;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.services.InvoiceService;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.services.InvoiceServiceImpl;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.test_dao.InvoiceRepositoryTest;

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
        Invoice invoice = InvoiceRepositoryTest.getInvoice();
        when(invoiceRepository.save(invoice)).thenReturn(invoice);
        invoiceRepository.save(invoice);
    }

    @Test
    public void whenInvoiceDoesNotExist_thenAddInvoiceTest() {
        Invoice invoice = InvoiceRepositoryTest.getInvoice();
        given(invoiceService.addInvoice(invoice)).willReturn(invoice);
        Invoice dbInvoice = invoiceService.addInvoice(invoice);
        assertThat(dbInvoice.getClient()).isEqualTo(invoice.getClient());
    }

}
