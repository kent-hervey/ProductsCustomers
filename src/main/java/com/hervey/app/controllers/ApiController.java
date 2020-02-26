package com.hervey.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;
import com.hervey.app.models.ProductCustomer;
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
	
	//Get number of customers
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
	
	//Modify a customer
	@PutMapping("/customers/{id}")
	public Customer modifyCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result ) {
		if(result.hasErrors()) {
			return null;
		}
		
		apiService.updateCustomer(customer);
		return customer;
	}
	
	
	//Get products for specified customer
	@GetMapping("/customers/{customerId}/products")
	public List<Product> showProductsForCustomer(@ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("customerId") Long customerId, Model model) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		model.addAttribute("customer", customer);
		List<Product> productsWithCustomer = apiService.fetchProductsWithThisCustomer(customer);
		//model.addAttribute("productsWithCustomer", productsWithCustomer);
		return productsWithCustomer;
	}
	

	//Add product to specified customer
	@PostMapping("/customers/{customerId}/products/{productId}")
	public ProductCustomer addProductToCustomer(@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		Customer customer = apiService.fetchThisCustomer(customerId);
		productCustomer.setCustomer(customer);
		productCustomer.setProduct(product);
		apiService.saveProductCustomer(productCustomer);
		return productCustomer;
	}
	
	//Deletes product from specified customer
	@DeleteMapping("/customers/{customerId}/products/{productId}")
	public String deleteProductFromCustomer(@PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId) {
		 apiService.deleteProductFromCustomer(customerId, productId);
		return "deleted";
	}
	
	
	
	
	
	//End Customers, begin Products
	
	
	//Get all the products
	@GetMapping("/products")
	public List<Product> showAllProducts(){
		List<Product> allProducts = apiService.fetchAllProducts();
		return allProducts;
	}
	
	
	
	


}
