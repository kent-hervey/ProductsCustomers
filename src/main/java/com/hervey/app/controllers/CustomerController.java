package com.hervey.app.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;
import com.hervey.app.models.ProductCustomer;
import com.hervey.app.models.Vendor;
import com.hervey.app.services.ApiService;

@RequestMapping("/customers")
@Controller
public class CustomerController {
	private final ApiService apiService;

	// This is the develop branch!

	public CustomerController(ApiService apiService) {
		this.apiService = apiService;
	}

	@GetMapping("")
	public String showCustomers(Model model) {
		Vendor vendor = apiService.fetchVendor();
		model.addAttribute("vendor", vendor);

		List<Customer> customers = apiService.fetchAllCustomers();
		model.addAttribute("customers", customers);

		return "customersFiles/showCustomers.jsp";
	}

	// Do action of deleting product from this customer
	@DeleteMapping("/{customerId}/products/{productId}")
	public String deleteProductFromCustomer(@PathVariable("customerId") Long customerId,
			@PathVariable("productId") Long productId) {
		apiService.deleteProductFromCustomer(customerId, productId);

		return "redirect:/customers/" + customerId;
	}

	// Do action of deleting the customer
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable("id") Long customerId) {

		Customer customer = apiService.fetchThisCustomer(customerId);

		apiService.deleteThisCustomer(customer);

		return "redirect:/customers";
	}

	// Do action of adding product to this customer
	@PostMapping("/{customerId}/products")
	public String addProductToCustomer(@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer,
			@PathVariable("customerId") Long customerId, @RequestParam("product") Long productId) {
		System.out.println("toString of productCustomer:  " + productCustomer);
		System.out.println("product selected is:  " + productId);

		Product product = apiService.fetchThisProduct(productId);
		Customer customer = apiService.fetchThisCustomer(customerId);

		productCustomer.setCustomer(customer);
		productCustomer.setProduct(product);

		apiService.saveProductCustomer(productCustomer);

		return "redirect:/customers/" + customerId;
	}

	// display page for creating a new customer
	@GetMapping("/new")
	public String showCreateProduct(@ModelAttribute("customer") Customer customer) {

		return "customersFiles/createCustomer.jsp";

	}

	// does action of creating new customer
	@PostMapping("")
	public String createCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {

		apiService.saveCustomer(customer);

		return "redirect:/customers";
	}

	// Renders Show Customer Page
	@GetMapping("/{customerId}")
	public String showCustomer(@ModelAttribute("productCustomer") ProductCustomer productCustomer,
			@PathVariable("customerId") Long customerId, Model model) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		model.addAttribute("customer", customer);

		List<ProductCustomer> productCustomers = apiService.fetchAllProductCustomers();
		model.addAttribute("productCustomers", productCustomers);

		List<Product> productsWithoutCustomer = apiService.fetchProductsWithoutThisCustomer(customer);
		model.addAttribute("productsWithoutCustomer", productsWithoutCustomer);

		return "customersFiles/showCustomer.jsp";

	}

	// Renders edit Customer page
	@GetMapping("/{customerId}/edit")
	public String showEditProduct(@PathVariable("customerId") Long customerId, Model model) {

		Customer customer = apiService.fetchThisCustomer(customerId);
		model.addAttribute("customer", customer);

		return "customersFiles/editCustomer.jsp";
	}

	// Does action of editing Customer
	@PutMapping("/{id}")
	public String editCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {

		if (result.hasErrors()) {
			return "customersFiles/editCustomer.jsp";
		}

		apiService.updateCustomer(customer);

		//return "redirect:/customer";
		return "redirect:/customers/" + customer.getId();
	}

}
