package com.hervey.app.dto;

import java.util.List;

import com.hervey.app.models.Customer;
import com.hervey.app.models.Product;

public class ListCustomersForProducts {
	
	private final Long id;
	private final String name, modelNumber, description;

	private final List<Customer> customers;
	
	public ListCustomersForProducts(Long id, String name, String modelNumber, String description, List<Customer> customers ){
		this.id = id;
		this.name = name;
		this.modelNumber = modelNumber;
		this.description = description;
		this.customers = customers;
		
	}

	public ListCustomersForProducts() {
		this.id=null;
		this.name=null;
		this.modelNumber=null;
		this.description=null;
		this.customers = null;
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getModelNumber() {
		return modelNumber;
	}

	public String getDescription() {
		return description;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customers == null) ? 0 : customers.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((modelNumber == null) ? 0 : modelNumber.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ListCustomersForProducts other = (ListCustomersForProducts) obj;
		if (customers == null) {
			if (other.customers != null)
				return false;
		} else if (!customers.equals(other.customers))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (modelNumber == null) {
			if (other.modelNumber != null)
				return false;
		} else if (!modelNumber.equals(other.modelNumber))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ListCustomersForProducts [id=" + id + ", name=" + name + ", modelNumber=" + modelNumber
				+ ", description=" + description + "]";
	}


	
	

}
