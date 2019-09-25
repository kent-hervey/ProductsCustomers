package com.hervey.app.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;
import com.hervey.app.models.ProductCustomer;
import com.hervey.app.models.Vendor;
import com.hervey.app.services.ApiService;

@RequestMapping("/products")
@Controller
public class ProductController {
	private final ApiService apiService;
	
	public ProductController(ApiService apiService) {
		this.apiService=apiService;
	}
	
	//display list of Products that at least includes a link to the show product page, also how many customers, product name, link to add product page
	@GetMapping("")
	//needs:  
	public String showProducts(Model model) {

		
		Vendor vendor=apiService.getVendor();
		model.addAttribute("vendor", vendor);
		
		List<Product> products=apiService.getAllProducts();
		model.addAttribute("products", products);
		

		
		

		return"products/showProducts.jsp";
	}
	
	//Do action of deleting customer from this product
	@DeleteMapping("/{id}/customers/{customerId}")  //maybe id2 won't work...there are other ways to pass it in such as RequestParam with input tag
	//needs customer id and product id
	public String deleteCustomerFromProduct(@PathVariable("id") Long productId, @PathVariable("customerId") Long customerId) {
		System.out.println("Product ID is:  " + productId + " and customer ID is " + customerId);	
		apiService.deleteCustomerFromProduct(productId, customerId);
		return"redirect:/products/"+productId;
	}
	
	//Do action of deleting the product
	@DeleteMapping("/{id}")
	public String deleteProduct(@PathVariable("id") Long productId){
		System.out.println("ready to delete a product with id:  " + productId);
		
		Product product=apiService.getThisProduct(productId);
		System.out.println("product name to be deleted is:  " + product.getName());
		
		apiService.deleteThisProduct(product);
		
		return"redirect:/products";
	}
	
	
	
	//Do action of adding customer to this product
	@PostMapping("/{id}/customers")  //not sure if id2 will work, but I really don't think have two "id"s will work...in prod Cat it is passed in via the option/select
	//needs
	public String addCustomerToProduct(@Valid @ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("id") Long productId,  @RequestParam("customerx") Long customerId) {
		System.out.println("toString of productCustomer:  "+ productCustomer);
		System.out.println("product isx:  " +productId);
		System.out.println("customer selected is:  " + customerId);
		
		Product product=apiService.getThisProduct(productId);
		
		
		Customer customer=apiService.getThisCustomer(customerId);
		System.out.println("customer name is:  " + customer);
		
		
		productCustomer.setCustomer(customer);
		productCustomer.setProduct(product);
		
		apiService.saveProductCustomer(productCustomer);
		
		System.out.println("toString of productCustomer2:  "+ productCustomer);
		
		return"redirect:/products/"+productId;
	}
	
	
	
	
	
	
	
	
	//display page for creating new product
	@GetMapping("/new")
	//needs:
	public String showCreateProduct(@ModelAttribute("product") Product product) {
		
		return"products/createProduct.jsp";
	}
	
	
	//does action of creating new products
	@PostMapping("")
	//needs:
	public String createProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		
		
		apiService.saveProduct(product);
		
		return"redirect:/products";
	}	
	
	
	//Renders Show Product page
	@GetMapping("/{id}")
	//needs:
	public String showProduct(@ModelAttribute("productCustomer") ProductCustomer productCustomer, @PathVariable("id") Long productId, Model model) {
		
		Product product=apiService.getThisProduct(productId);
		model.addAttribute("product", product);
		
		
		List<ProductCustomer> productCustomers=apiService.getAllProductCustomers();
		model.addAttribute("productCustomers", productCustomers);
		
		List<Customer> customersWithoutProduct=apiService.findCustomersWithoutThisProduct(product);
		model.addAttribute("customersWithoutProduct", customersWithoutProduct);
		
		
		return"products/showProduct.jsp";

	}
	
	
	//Renders Edit Product page
	@GetMapping("/{id}/edit")
	//needs:
	public String showEditProduct(@PathVariable("id") Long productId, Model model) {
		Product product = apiService.getThisProduct(productId);
		model.addAttribute("product", product);
		
		
		
		return"products/editProduct.jsp";

	}
	
	//Does action of editing Product
	@PutMapping("/{id}")
	//needs:
	public String editProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
		System.out.println("product id is:  " + product.getId());
		
		if (result.hasErrors()) {
			System.out.println("all errors:  " + result.toString());
			return"products/editProduct.jsp";
		}
		apiService.updateProduct(product);
		
		return "redirect:/products/"+product.getId();  //when complete, we show all the products, again, but we could show product details on the product page
	}
	
}
