package com.hervey.app.services;

import java.util.ArrayList;
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

	public ApiService(CustomerRepository customerRepository, ProductRepository productRepository,
			ProductCustomerRepository productCustomerRepository, VendorRepository vendorRepository) {
		this.customerRepository = customerRepository;
		this.productRepository = productRepository;
		this.productCustomerRepository = productCustomerRepository;
		this.vendorRepository = vendorRepository;
	}

	public List<Product> fetchAllProducts() {

		// TODO Auto-generated method stub
		return productRepository.findAll();
	}
	
	
	public List<Integer> fetchAllCustomerIDs(){
		List<Integer> idList = new ArrayList<Integer>();
		for(Customer customer : customerRepository.findAll()) {
			idList.add(customer.getId().intValue());
		}
		return idList;
	}

	public List<Integer> fetchAllProductIDs() {
		List<Integer> idList = new ArrayList<Integer>();
		for(Product product : productRepository.findAll()) {
			System.out.println("this product is:  " + product.toString());
			idList.add(product.getId().intValue());
		}
		return idList;
	} 
	
	//fetches one vendor regardless
	public Vendor fetchVendor() {
		return vendorRepository.findFirstByOrderById();
	}

	public void saveProduct(Product product) {
		productRepository.save(product);

	}

	public void saveCustomer(@Valid Customer customer) {
		customerRepository.save(customer);

	}

	public Product fetchThisProduct(Long productId) {
		return productRepository.findById(productId).orElse(null);
	}

	public Customer fetchThisCustomer(Long customerId) {
		return customerRepository.findById(customerId).orElse(null);
	}

	public void deleteThisProduct(Product product) {
		productRepository.delete(product);

	}

	public void deleteThisCustomer(Customer customer) {
		customerRepository.delete(customer);

	}

	public List<ProductCustomer> fetchAllProductCustomers() {
		return productCustomerRepository.findAll();
	}

	// Retrieves all Customers who don't have this product
	public List<Customer> fetchCustomersWithoutThisProduct(Product product) {
		return customerRepository.findByProductsNotContains(product);
	}

	// Retrieves all Products who don't have this customer
	public List<Product> fetchProductsWithoutThisCustomer(Customer customer) {
		return productRepository.findByCustomersNotContains(customer);
	}

	// Retrieves all Products which do have this customer...added 2/25/20 for API
	public List<Product> fetchProductsWithThisCustomer(Customer customer) {
		return productRepository.findByCustomersContains(customer);
	}
	
	
	public void saveProductCustomer(@Valid ProductCustomer productCustomer) {
		productCustomerRepository.save(productCustomer);
	}

	// deletes row from middle table, same as deleteProductFromCustomer
	public void deleteCustomerFromProduct(Long productId, Long customerId) {
		this.disassociateProductAndCustomer(customerId, productId);
	}

	// deletes row from middle table, same as deleteCustomerFromProduct
	public void deleteProductFromCustomer(Long customerId, Long productId) {
		this.disassociateProductAndCustomer(customerId, productId);
	}

	private void disassociateProductAndCustomer(Long customerId, Long productId) {
		Product product = productRepository.findById(productId).orElse(null);
		Customer customer = customerRepository.findById(customerId).orElse(null);
		ProductCustomer productCustomer = productCustomerRepository.findByProductAndCustomer(product, customer);
		productCustomerRepository.delete(productCustomer);
	}

	public Vendor updateVendor(Vendor vendor) {
		return vendorRepository.save(vendor);
	}
	
	public List<Vendor> fetchAllVendors() {
		return vendorRepository.findAll();
	}

	
	public void updateProduct(@Valid Product product) {
		productRepository.save(product);
	}

	public void updateCustomer(@Valid Customer customer) {
		customerRepository.save(customer);

	}

	public List<Customer> fetchAllCustomers() {
		return customerRepository.findAll();
	}

	public boolean fetchIsCustomerProduct(Long productId, Long customerId) {
		Product product = productRepository.findById(productId).orElse(null);
		Customer customer = customerRepository.findById(customerId).orElse(null);
		ProductCustomer productCustomer = productCustomerRepository.findByProductAndCustomer(product, customer);
		System.out.println("productCustomer:  " + productCustomer);
		if(productCustomer !=null) {
			return true;
		}
		
		return false;
	}

	public Long fetchCountVendors() {
		return vendorRepository.count();
	}









	
	
}
