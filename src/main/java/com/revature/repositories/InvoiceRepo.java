package com.revature.repositories;

import com.revature.models.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepo extends CrudRepository<Invoice, Integer> {
  Iterable<Invoice> findAllByShopId(int id);
}
