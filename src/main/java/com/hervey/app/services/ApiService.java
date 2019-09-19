package com.hervey.app.services;

import org.springframework.stereotype.Service;

import com.hervey.app.repositories.CustomerRepository;
import com.hervey.app.repositories.ProductCustomerRepository;
import com.hervey.app.repositories.ProductRepository;
import com.hervey.app.repositories.VendorRepository;

@Service
public class ApiService {
	private final CustomerRepository customerRepository;
	private final ProductRepository productRepository;
	private final ProductCustomerRepository productCustomerRepository;
	private final VendorRepository vendorRepository;
	
	public ApiService(CustomerRepository customerRepository, ProductRepository productRepository, ProductCustomerRepository productCustomerRepository, VendorRepository vendorRepository) {
		this.customerRepository=customerRepository;
		this.productRepository=productRepository;
		this.productCustomerRepository=productCustomerRepository;
		this.vendorRepository=vendorRepository;
	}
	
	
	
	
	
	
	
	
	

}
