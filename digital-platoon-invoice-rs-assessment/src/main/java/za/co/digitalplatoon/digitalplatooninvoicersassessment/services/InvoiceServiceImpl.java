package za.co.digitalplatoon.digitalplatooninvoicersassessment.services;

import org.springframework.stereotype.Service;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.dao.InvoiceRepository;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class InvoiceServiceImpl implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        invoice.setInvoiceDate(new Date());
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> viewAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice viewInvoice(Long invoiceId) {
        Invoice invoice = invoiceRepository.getOne(invoiceId);
        return (invoice != null) ? invoice : null;
    }

}