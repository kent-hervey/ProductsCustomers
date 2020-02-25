package com.hervey.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hervey.app.models.Customer;
import com.hervey.app.services.ApiService;

@RequestMapping("/api")
@RestController
public class ApiController {
	private final ApiService apiService;
	
	
	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}
	
	//customers at top, then products
	
	//Get all the customers
	@GetMapping("/customers")
	public List<Customer> showAllCustomers(){
		List<Customer> allCustomers = apiService.fetchAllCustomers();
		return allCustomers;
	}
	
	//Get all the customers
	@GetMapping("/customers-number-of")
	public int showNumberCustomers(){
		List<Customer> allCustomers = apiService.fetchAllCustomers();
		return allCustomers.size();
	}
	
	
	//show one customer's id
	@GetMapping("/customers-id/{id}")
	public Long showCustomerID(@PathVariable("id") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		return customer.getId();
	
	}
	
	//Get one customer
	@GetMapping("/customers/{id}")
	public Customer showCustomer(@PathVariable("id") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		return customer;
	
	}
	
	//Add a customer
	@PostMapping("/customers")
	public Customer createCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result ) {
		apiService.saveCustomer(customer);
		
		return customer;
	}
	
	

	
	
	//End Customers
	
	
	
	
	


}
