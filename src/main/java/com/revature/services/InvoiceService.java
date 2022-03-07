package com.revature.services;

import com.revature.models.Invoice;

import java.util.List;


public interface InvoiceService {
    /**
     * This method gets all Invoices from the database
     * @return  returns a List of all Shop Products
     */

    List<Invoice> getAllInvoicesBySellerId(int id);
    List<Invoice> getInvoicesByShopId(int id);
    List<Invoice> getInvoiceByCustomerId(int id);



}
