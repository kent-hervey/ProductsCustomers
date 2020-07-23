package com.hervey.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hervey.app.dto.ListCustomersForProducts;
import com.hervey.app.dto.ListProductsForCustomer;
import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;
import com.hervey.app.models.ProductCustomer;
import com.hervey.app.models.Vendor;
import com.hervey.app.services.ApiService;

@RequestMapping("/api")
@RestController
public class ApiController {
	private final ApiService apiService;

	public ApiController(ApiService apiService) {
		this.apiService = apiService;
	}

	// vendor at top, then customers, then products
	//Get all vendors
	@GetMapping("/vendors")
	public List<Vendor> showAllVendors() {
		List<Vendor> vendors = apiService.fetchAllVendors();
		return vendors;
	}
	
	// Show Vendor
	@GetMapping(path = { "/vendor" }) // value also works instead of path
	public ResponseEntity<Vendor> showVendor() {
		System.out.println("the vendor is:  " + apiService.fetchVendor());
		return ResponseEntity.ok(apiService.fetchVendor());
	}

	//fetch ID of vendor
	@GetMapping("vendor-id")  //should always be 1
	public Long showVendorId() {
		Vendor vendor = apiService.fetchVendor();
		return vendor.getId();
	}
	
	// Modify the vendor: Note, there is only one
	@PutMapping("/vendor")
	public ResponseEntity<Vendor> modifyVendor(@RequestBody Vendor vendor) {
		return ResponseEntity.ok(apiService.updateVendor(vendor));
	}


	// CUSTOMERS

	// Get all the customers
	@GetMapping("/customers")
	public List<Customer> showAllCustomers() {
		List<Customer> allCustomers = apiService.fetchAllCustomers();
		return allCustomers;
	}

	// Get number of customers
	@GetMapping("/customers-number-of")
	public int showNumberCustomers() {
		List<Customer> allCustomers = apiService.fetchAllCustomers();
		return allCustomers.size();
	}

	// Get list of valid IDs for customers
	@GetMapping("/customers-ids")
	public List<Integer> showCustomerIds() {
		System.out.println(apiService.fetchAllCustomerIDs());
		return apiService.fetchAllCustomerIDs();
	}

	// Check if customer id is valid
	@GetMapping("/customers-id/{id}")
	public boolean showCustomerID(@PathVariable("id") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		if (customer != null) {
			return true;
		}
		return false; // indicates no customer with submitted ID
	}

	// Get one customer...
	@GetMapping("/customers/{id}")
	public Customer showCustomer(@PathVariable("id") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		return customer;
	}

	// Add a customer
	// @PostMapping(value = "/customers" , produces={"application/json;
	// charset=UTF-8"})
	@PostMapping("/customers")
	public Customer createCustomer(@RequestBody Customer customer) {
		apiService.saveCustomer(customer);
		return customer;
	}

	// Modify a customer
	@PutMapping("/customers/{id}")
	public Customer modifyCustomer(@RequestBody Customer customer) {
		apiService.updateCustomer(customer);
		return customer;
	}

	// Get customers only for specified product
	@GetMapping("/customers/products/{productId}")
	public List<Customer> showCustomersOnlyForProduct(@PathVariable("productId") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		if (product == null) {
			List<Customer> customers = new ArrayList<Customer>();
			return customers;
		}
		List<Customer> customers = product.getCustomers();
		return customers;
	}

	// Get customer and products for specified customer with customer shown
	@GetMapping("/customers/{customerId}/products")
	public ListProductsForCustomer showProductsForCustomer(
			@ModelAttribute("productCustomer") ProductCustomer productCustomer,
			@PathVariable("customerId") Long customerId, Model model) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		if (customer == null) {
			return new ListProductsForCustomer();
		}

		// List<Product> productsWithCustomer =
		// apiService.fetchProductsWithThisCustomer(customer);
		// model.addAttribute("productsWithCustomer", productsWithCustomer);
		// return productsWithCustomer;

		return new ListProductsForCustomer(customer.getId(), customer.getContactEmail(), customer.getContactName(),
				customer.getLocation(), customer.getName(), customer.getProducts());
	}

	// Does this customer have this product
	@GetMapping("/customers/{customerId}/products/{productId}")
	public boolean customerProductIsTrue(@PathVariable("productId") Long productId,
			@PathVariable("customerId") Long customerId) {
		return apiService.fetchIsCustomerProduct(productId, customerId);
	}

	// Add product to specified customer
	// To be updated to include adding serial number and purchase date
	@PostMapping("/customers/{customerId}/products/{productId}")
	public ProductCustomer addProductToCustomer(
			@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer,
			@PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		Customer customer = apiService.fetchThisCustomer(customerId);
		productCustomer.setCustomer(customer);
		productCustomer.setProduct(product);

		apiService.saveProductCustomer(productCustomer);
		return productCustomer;
	}

	// Deletes product from specified customer
	@DeleteMapping("/customers/{customerId}/products/{productId}")
	public String deleteProductFromCustomer(@PathVariable("customerId") Long customerId,
			@PathVariable("productId") Long productId) {
		apiService.deleteProductFromCustomer(customerId, productId);
		return "deleted";
	}

	// Delete customer with this customerId
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		apiService.deleteThisCustomer(customer);

		return "customer deleted";
	}

	// ==================================
	// End Customers, begin Products

	// Get all the products only
	@GetMapping("/products")
	public List<Product> showAllProducts() {
		List<Product> allProducts = apiService.fetchAllProducts();
		return allProducts;
	}

	// Get one product...
	@GetMapping("/products/{id}")
	public Product showProduct(@PathVariable("id") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		return product;
	}

	// Fetch list of product IDs
	@GetMapping("/product-ids")
	public List<Integer> showProductIds() {
		return apiService.fetchAllProductIDs();
	}

	// Check if product id is valid
	@GetMapping("/products-id/{id}")
	public boolean showProductID(@PathVariable("id") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		if (product != null) {
			return true;
		}
		return false; // indicates no product with submitted ID
	}

	// Get products only for specified customer
	@GetMapping("/products/customers/{customerId}")
	public List<Product> showProductsOnlyForCustomer(@PathVariable("customerId") Long customerId) {

		Customer customer = apiService.fetchThisCustomer(customerId);
		if (customer == null) {
			return new ArrayList<Product>();
		}

		return customer.getProducts();

	}

	// Get product and customers for specified product with product shown
	@GetMapping("/products/{productId}/customers")
	public ListCustomersForProducts showCustomersForProduct(
			@ModelAttribute("productCustomer") ProductCustomer productCustomer,
			@PathVariable("productId") Long productId, Model model, BindingResult result) {

		Product product = apiService.fetchThisProduct(productId);
		if (product == null) {
			return new ListCustomersForProducts();
		}

		// List<Product> productsWithCustomer =
		// apiService.fetchProductsWithThisCustomer(customer);
		// model.addAttribute("productsWithCustomer", productsWithCustomer);
		// return productsWithCustomer;

		return new ListCustomersForProducts(product.getId(), product.getName(), product.getModelNumber(),
				product.getDescription(), product.getCustomers());
	}

	// Add a product
	@PostMapping("/products")
	public Product createProduct(@RequestBody Product product, BindingResult result) {
		apiService.saveProduct(product);
		return product;
	}

	// Modify or update a product
	@PutMapping("/products/{id}")
	public Product modifyProduct(@RequestBody Product product) {
		apiService.updateProduct(product);
		return product;
	}

	// Add customer to specified product
	@PostMapping("/products/{productId}/customers/{customerId}")
	public ProductCustomer addCustomerToProduct(
			@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer,
			@PathVariable("productId") Long productId, @PathVariable("customerId") Long customerId) {
		Customer customer = apiService.fetchThisCustomer(customerId);
		Product product = apiService.fetchThisProduct(productId);
		productCustomer.setProduct(product);
		productCustomer.setCustomer(customer);
		apiService.saveProductCustomer(productCustomer);
		return productCustomer;

	}

	// Get number of products
	@GetMapping("/products-number-of")
	public int showNumberProducts() {
		List<Product> allProducts = apiService.fetchAllProducts();
		return allProducts.size();
	}

	// Deletes customer from specified product
	@DeleteMapping("/products/{productId}/customers/{customerId}")
	public String deleteCustomerFromProduct(@PathVariable("productId") Long productId,
			@PathVariable("customerId") Long customerId) {
		apiService.deleteCustomerFromProduct(productId, customerId);
		return "deleted";
	}

	// Delete product with this ID
	@DeleteMapping("/products/{productId}")
	public String deleteProduct(@PathVariable("productId") Long productId) {
		Product product = apiService.fetchThisProduct(productId);
		apiService.deleteThisProduct(product);
		return "product deleted";
	}

}
