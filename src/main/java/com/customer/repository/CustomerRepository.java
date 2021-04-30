package com.customer.repository;

import org.springframework.data.repository.core.support.DefaultCrudMethods;
import org.springframework.stereotype.Repository;

import com.customer.domain.Customer;

import org.springframework.data.repository.CrudRepository;



@Repository
public interface CustomerRepository  extends CrudRepository<Customer,Integer>{
	
	
}
