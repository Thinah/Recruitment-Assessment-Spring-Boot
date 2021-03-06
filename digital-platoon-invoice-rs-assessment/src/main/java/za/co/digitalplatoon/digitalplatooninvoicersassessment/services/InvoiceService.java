package za.co.digitalplatoon.digitalplatooninvoicersassessment.services;

import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;

import java.util.List;

public interface InvoiceService {
    Invoice addInvoice(Invoice invoice);
    List<Invoice> viewAllInvoices();
    Invoice viewInvoice(Long id);
}
