package com.revature.repositories;


import com.revature.models.Invoice;
import com.revature.models.Product;
import com.revature.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;


import java.util.Optional;


@Repository
public interface InvoiceRepo extends CrudRepository<Invoice, Integer> {

}
