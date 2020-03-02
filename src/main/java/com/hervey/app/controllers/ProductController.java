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
import com.hervey.app.repositories.ProductRepository;
import com.hervey.app.services.ApiService;

@RequestMapping("/products")
@Controller
public class ProductController {
	private final ApiService apiService;
	private final ProductRepository productRepository;

	public ProductController(ApiService apiService, ProductRepository productRepository) {
		this.apiService = apiService;
		this.productRepository = productRepository;
	}

	// display list of Products that includes a link to the show product
	// page, also how many customers, product name, link to add product page
	@GetMapping("")
	public String showProducts(Model model) {

		Vendor vendor = apiService.fetchVendor();
		model.addAttribute("vendor", vendor);

		List<Product> products = apiService.fetchAllProducts();
		model.addAttribute("products", products);

		return "productsFiles/showProducts.jsp";
	}

	// Do action of deleting customer from this product
	@DeleteMapping("/{id}/customers/{customerId}") 
	public String deleteCustomerFromProduct(@PathVariable("id") Long productId, @PathVariable("customerId") Long customerId) {
		System.out.println("Product ID is:  " + productId + " and customer ID is " + customerId);
		apiService.deleteCustomerFromProduct(productId, customerId);
		return "redirect:/products/" + productId;
	}

	// Do action of deleting the product
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable("id") Long productId) {
		System.out.println("ready to delete a product with id:  " + productId);

		Product product = apiService.fetchThisProduct(productId);
		System.out.println("product name to be deleted is:  " + product.getName());

		apiService.deleteThisProduct(product);

		return "redirect:/products";
	}

	// Do action of adding customer to this product
	@PostMapping("/{id}/customers") 
	public String addCustomerToProduct(@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("id") Long productId, @RequestParam("customer") Long customerId) {
		System.out.println("toString of productCustomer:  " + productCustomer);
		System.out.println("customer selected is:  " + customerId);

		Product product = apiService.fetchThisProduct(productId);

		Customer customer = apiService.fetchThisCustomer(customerId);
		System.out.println("customer name is:  " + customer);
		
		productCustomer.setCustomer(customer);
		productCustomer.setProduct(product);
		
		apiService.saveProductCustomer(productCustomer);
		return "redirect:/products/" + productId;
	}

	// display page for creating new product
	@GetMapping("/new")
	public String showCreateProduct(@ModelAttribute("product") Product product) {

		return "productsFiles/createProduct.jsp";
	}

	// does action of creating new product
	@PostMapping("")
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {

		apiService.saveProduct(product);

		return "redirect:/products";
	}

	// Renders Show Product page
	@GetMapping("/{id}")
	public String showProduct(@ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("id") Long productId, Model model) {

		Product product = apiService.fetchThisProduct(productId);
		if(product==null) {
			return "productsFiles/showProducts.jsp";
		}
		
		
		model.addAttribute("product", product);

		List<ProductCustomer> productCustomers = apiService.fetchAllProductCustomers();
		model.addAttribute("productCustomers", productCustomers);

		List<Customer> customersWithoutProduct = apiService.fetchCustomersWithoutThisProduct(product);
		model.addAttribute("customersWithoutProduct", customersWithoutProduct);

		return "productsFiles/showProduct.jsp";

	}

	// Renders Edit Product page
	@GetMapping("/{id}/edit")
	public String showEditProduct(@PathVariable("id") Long productId, Model model) {
		
		Product product = apiService.fetchThisProduct(productId);
		if(product==null) {
			return "productsFiles/showProducts.jsp";
		}
		
		model.addAttribute("product", product);

		return "productsFiles/editProduct.jsp";

	}

	// Does action of editing Product
	@PutMapping("/{id}")
	public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		System.out.println("product id is:  " + product.getId());
		String modelNum = product.getModelNumber();

		if (result.hasErrors()) {
			System.out.println("next");
			product.setModelNumber(modelNum);

			return "productsFiles/editProduct.jsp";
		}
		
		System.out.println("Product:  " + product);
		
		apiService.updateProduct(product);

		return "redirect:/products/" + product.getId();

	}

}
