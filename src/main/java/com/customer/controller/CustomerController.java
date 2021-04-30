package com.customer.controller;

import java.net.URI;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.customer.domain.Customer;
import com.customer.service.CustomerService;
import com.cutomer.model.CustomerRequest;


@RestController
@RequestMapping ("customerController")
public class  CustomerController

{
	
	private final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired CustomerService   customerService;
	
	//@RequestMapping({"/hello"})
	
	@GetMapping(value ="/hello")
	public String firstPage()
	
	{
		
		return "Hello";
		
		
	}
	
	@PostMapping(value="/Customers")
	
	public ResponseEntity<Object> createCustomer(@RequestBody  CustomerRequest customerRequet)
	{
		logger.info("Calling createCustomer");
		CustomerRequest savedCustomer = customerService.createCustomer(customerRequet);
		
		logger.info("User Saved "+savedCustomer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedCustomer.getUserId()).toUri();
		return ResponseEntity.created(location).build();
		
	}
	
	
	
	
	@GetMapping(value="/Customers/{id}")
	public CustomerRequest retrieveCustomer(@PathVariable Integer id)
	
	{
		logger.info("User ID "+id);
		CustomerRequest custReq = customerService.getCustomer(id);
		logger.info("Retrieved Customer"+custReq.getUserName());
		return custReq;
		
		
	}
	
	
	@DeleteMapping(value="/Customers/{id}" )
	public void deleteCustomer(@PathVariable Integer id)
	{
		
		System.out.println("ID"+id);
		customerService.deleteCustomer(id);
		
	}
	

}