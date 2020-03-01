package com.hervey.app.dto;

import java.util.List;

import com.hervey.app.models.Product;

public class ListProductsForCustomer {
	
	private final Long id;
	private final String contactEmail, contactName, location, name;

	private final List<Product> products;
	
	public ListProductsForCustomer(Long id, String contactEmail, String contactName, String location, String name, List<Product> products ){
		this.id = id;
		this.contactEmail = contactEmail;
		this.contactName = contactName;
		this.location = location;
		this.name = name;
		this.products = products;
		
	}

	public ListProductsForCustomer() {
		this.id = null;
		this.contactEmail = null;
		this.contactName = null;
		this.location = null;
		this.name = null;
		this.products = null;
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public String getContactName() {
		return contactName;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contactEmail == null) ? 0 : contactEmail.hashCode());
		result = prime * result + ((contactName == null) ? 0 : contactName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ListProductsForCustomer other = (ListProductsForCustomer) obj;
		if (contactEmail == null) {
			if (other.contactEmail != null)
				return false;
		} else if (!contactEmail.equals(other.contactEmail))
			return false;
		if (contactName == null) {
			if (other.contactName != null)
				return false;
		} else if (!contactName.equals(other.contactName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}

	public List<Product> getProducts() {
		return products;
	}
	
	
	

}
