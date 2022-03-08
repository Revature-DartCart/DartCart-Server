package com.revature.services;

import com.revature.models.Invoice;
import com.revature.models.User;
import com.revature.repositories.InvoiceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {


    @Autowired
    InvoiceRepo invoiceRepo;



    /**
     *
     * @return a list of a customers invoices
     */
    @Override
    public List<Invoice> getAllInvoices() {
        return (List<Invoice>) invoiceRepo.findAll();
    }

    @Override
    public List<Invoice> getInvoiceByCustomerId(int id) {
        List<Invoice> invoices = (List<Invoice>) invoiceRepo.findAll();
        List<Invoice> generatedInvoice = null;
        generatedInvoice = invoices.stream()
                .filter(invoice -> invoice.getCustomer().getId() == id)
                .collect(Collectors.toList());
        return generatedInvoice;
    }


}
