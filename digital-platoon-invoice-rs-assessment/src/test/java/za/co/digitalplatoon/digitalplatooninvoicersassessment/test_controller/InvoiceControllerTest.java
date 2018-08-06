package za.co.digitalplatoon.digitalplatooninvoicersassessment.test_controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.controllers.InvoiceController;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.services.InvoiceService;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.test_dao.InvoiceRepositoryTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceController.class)
public class InvoiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceService invoiceService;

    private JacksonTester<Invoice> invoiceJacksonTester = null;
    private Invoice invoice = InvoiceRepositoryTest.getInvoice();
    @Before
    public void setup(){
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void add_NewInvoiceTest() throws Exception {
        Invoice invoice = this.invoice;
        given(invoiceService.addInvoice(invoice)).willReturn(invoice);

        final MockHttpServletResponse response = mockMvc.perform(post("/invoice/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invoiceJacksonTester.write(invoice).getJson()))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    public void getInvoices_shouldReturnAllInvoices() throws Exception {
        final List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        given(invoiceService.viewAllInvoices()).willReturn(invoices);
        assertThat(invoices.size()).isEqualTo(invoiceService.viewAllInvoices().size());

        mockMvc.perform(get("/invoice/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
