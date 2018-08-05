package za.co.digitalplatoon.digitalplatooninvoicersassessment.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.digitalplatoon.digitalplatooninvoicersassessment.entities.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
