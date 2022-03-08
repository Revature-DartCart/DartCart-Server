package com.revature.services;

import com.revature.models.Invoice;
import com.revature.models.User;

import java.util.List;



public interface InvoiceService {

    List<Invoice> getInvoiceByCustomerId(int id);
    public List<Invoice> getAllInvoices();




}
