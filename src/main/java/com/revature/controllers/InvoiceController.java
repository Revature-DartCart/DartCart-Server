package com.revature.controllers;

import com.revature.models.Invoice;
import com.revature.models.Seller;
import com.revature.services.InvoiceService;
import com.revature.services.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
public class InvoiceController {

    @Autowired
    InvoiceService invoiceService;

    /**
     *
     * @param id endpoint for retrieving a customers invoice by id
     * @return
     */
    @GetMapping(value = "/invoices/customer/{id}")
    public ResponseEntity<Invoice> getInvoiceByUsername(@PathVariable("id") String id) {
            Invoice invoice = (Invoice) invoiceService.getInvoiceByCustomerId(Integer.parseInt(id));
            return invoice != null ? new ResponseEntity<>(invoice, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
