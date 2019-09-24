package com.hervey.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;
import com.hervey.app.models.ProductCustomer;

@Repository
public interface ProductCustomerRepository extends CrudRepository <ProductCustomer, Long> {
	List<ProductCustomer> findAll();
	ProductCustomer findByProductAndCustomer(Product product, Customer customer);

}
