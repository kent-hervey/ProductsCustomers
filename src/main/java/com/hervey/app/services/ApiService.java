package com.hervey.app.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;
import com.hervey.app.models.ProductCustomer;
import com.hervey.app.models.Vendor;
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

	public List<Product> getAllProducts() {
		
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	public Vendor getVendor() {
		return vendorRepository.findById((long) 1).orElse(null);

	}

	public void saveProduct(Product product) {
		productRepository.save(product);
		
	}

	public Product getThisProduct(long productId) {
		return productRepository.findById(productId).orElse(null);
	}
	
	public Customer getThisCustomer(long customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}
	
	

	public void deleteThisProduct(Product product) {
		System.out.println("about to delete product with name:  " + product.getName());
		productRepository.delete(product);
		
	}

	public List<ProductCustomer> getAllProductCustomers() {
		return productCustomerRepository.findAll();
	}
	
	
	//Retrieves all Customers who don't have this product
	public List<Customer> findCustomersWithoutThisProduct(Product product){
		//System.out.println("product name is:  " + product.getName());
		return customerRepository.findByProductsNotContains(product);
	}

	public void saveProductCustomer(@Valid ProductCustomer productCustomer) {
		productCustomerRepository.save(productCustomer);
		
	}


	public void deleteCustomerFromProduct(Long productId, Long customerId) {
		Product product=productRepository.findById(productId).orElse(null);
		Customer customer =customerRepository.findById(customerId).orElse(null);
		ProductCustomer productCustomer=productCustomerRepository.findByProductAndCustomer(product, customer);
		System.out.println("Id of product customer being deleted is:  " + productCustomer.getId());
		productCustomerRepository.delete(productCustomer);
	}

	public void updateProduct(@Valid Product product) {
		productRepository.save(product);
		
	}
	
	

}
