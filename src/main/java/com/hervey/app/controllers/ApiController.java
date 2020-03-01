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

import com.hervey.app.dto.ListCustomersForProducts;
import com.hervey.app.dto.ListProductsForCustomer;
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
	
	//Get list of valid IDs for customers
	@GetMapping("/customers-ids")
	public List<Integer> showCustomerIds(){
		System.out.println(apiService.fetchAllCustomerIDs());
		return apiService.fetchAllCustomerIDs();
	}
	
	
	//show one customer's id
	@GetMapping("/customers-id/{id}")
	public Long showCustomerID(@PathVariable("id") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		return customer.getId();
	
	}
	
	
	//Get one customer...
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
	
	
	//Get products for specified customer with customer shown
	@GetMapping("/customers/{customerId}/products")
	public ListProductsForCustomer showProductsForCustomer(@ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("customerId") Long customerId, Model model) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		if(customer ==  null) {
			return new ListProductsForCustomer();
		}

		//List<Product> productsWithCustomer = apiService.fetchProductsWithThisCustomer(customer);
		//model.addAttribute("productsWithCustomer", productsWithCustomer);
		//return productsWithCustomer;
		
		
		return new ListProductsForCustomer(customer.getId(), customer.getContactEmail(), customer.getContactName(), customer.getLocation(), customer.getName(), customer.getProducts());
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
	
	//Delete customer with this customerId
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		apiService.deleteThisCustomer(customer);
		
		return "customer deleted";
	}
	
	
	
	
	
	//End Customers, begin Products
	
	
	//Get all the products only
	@GetMapping("/products")
	public List<Product> showAllProducts(){
		List<Product> allProducts = apiService.fetchAllProducts();
		return allProducts;
	}

	

	//Get customers for specified product with product shown
	@GetMapping("/products/{productId}/customers")
	public ListCustomersForProducts showCustomersForProduct(@ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("productId") Long productId, Model model, BindingResult result) {

		Product product = apiService.fetchThisProduct(productId);
		if(product ==  null) {
			return new ListCustomersForProducts();
		}

		//List<Product> productsWithCustomer = apiService.fetchProductsWithThisCustomer(customer);
		//model.addAttribute("productsWithCustomer", productsWithCustomer);
		//return productsWithCustomer;
		
		
		return new ListCustomersForProducts(product.getId(), product.getName(), product.getModelNumber(), product.getDescription(), product.getCustomers());
	}

	//Add a product
	@PostMapping("/products")
	public Product createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		apiService.saveProduct(product);
		return product;
	}
	
	//Add customer to specified product
	@PostMapping("/products/{productId}/customers/{customerId}")
	public ProductCustomer addCustomerToProduct(@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("productId") Long productId, @PathVariable("customerId") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		Product product = apiService.fetchThisProduct(productId);
		productCustomer.setProduct(product);
		productCustomer.setCustomer(customer);
		apiService.saveProductCustomer(productCustomer);
		return productCustomer;
		
		
	}
	
	 //Deletes customer from specified product
	@DeleteMapping("/products/{productId}/customers/{customerId}")
	public String deleteCustomerFromProduct(@PathVariable("productId") Long productId, @PathVariable("customerId") Long customerId) {
		apiService.deleteCustomerFromProduct(productId, customerId);
		return "deleted";
	}
	
	//Delete product with this ID
	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable("productId") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		apiService.deleteThisProduct(product);
		return "product deleted";
	}
	
	
	
	
	
	
	
	
	
	
	
}
