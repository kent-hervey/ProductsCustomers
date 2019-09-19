package com.hervey.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.hervey.app.models.Product;

@Service

public interface ProductRepository extends CrudRepository <Product, Long>{
	List<Product> findAll();
	

}
