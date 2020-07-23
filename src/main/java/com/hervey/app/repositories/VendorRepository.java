package com.hervey.app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hervey.app.models.Vendor;

@Repository
public interface VendorRepository extends CrudRepository<Vendor, Long> {
	List<Vendor> findAll();

	Vendor findFirstByOrderById();

}
