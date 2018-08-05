package za.co.digitalplatoon.digitalplatooninvoicersassessment.controllers;

import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.services.InvoiceService;

import java.util.List;

@RestController
@RequestMapping(value = "/invoice")
public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping
    public Invoice addInvoice(@RequestBody Invoice invoice) {
        return invoiceService.addInvoice(invoice);
    }

    @GetMapping
    public List<Invoice> viewAllInvoices() {
        return invoiceService.viewAllInvoices();
    }

    @GetMapping(value = "/{invoiceId}")
    public Invoice viewInvoice(@PathVariable Long invoiceId) {
        return invoiceService.viewInvoice(invoiceId);
    }
}
