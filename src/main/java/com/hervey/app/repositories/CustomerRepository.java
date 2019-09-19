package com.hervey.app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.Customer;

@Repository
public interface CustomerRepository extends CrudRepository <Customer, Long> {
	List<Customer> findAll();
	Optional<Customer> findById(Long id);
	

}
