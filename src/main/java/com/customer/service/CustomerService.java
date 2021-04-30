package com.customer.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.domain.Customer;
import com.customer.repository.CustomerRepository;
import com.cutomer.model.CustomerRequest;

import ch.qos.logback.classic.Logger;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	public void findById(Integer userId) {

		customerRepository.findById(userId);

	}

	public CustomerRequest createCustomer(CustomerRequest customer) {

		try {
			ModelMapper modelMapper = new ModelMapper();
			Customer customerEntity = modelMapper.map(customer, Customer.class);
			System.out.println("Entity" + customerEntity.getUserName());
			try {
				// logger.info("customerEntity"+customerEntity.getUserId());
				return (modelMapper.map((Customer) customerRepository.save(customerEntity), CustomerRequest.class));

			} catch (Exception e)

			{
				System.out.println(e.getLocalizedMessage());
			}

		} catch (Exception e) {
			// handle in case of duplicate username

		}
		return customer;
	}

	/*
	 * public List<CustomerRequest> listAll()
	 * 
	 * { ModelMapper modelMapper = new ModelMapper();
	 * 
	 * 
	 * 
	 * return modelMapper.map(customerRepository.findAll(),
	 * List<CustomerRequest.class>); }
	 */

	public CustomerRequest getCustomer(Integer id) {
		ModelMapper modelMapper = new ModelMapper();

		Optional<Customer> cust = customerRepository.findById(id);

		System.out.println("cust  Retrieved" + cust.toString());
		CustomerRequest custReq = modelMapper.map(cust, CustomerRequest.class);
		System.out.println("cust  Retrieved" + custReq.getUserName());

		return custReq;
	}

	public void deleteCustomer(Integer id)

	{
		customerRepository.deleteById(id);
	}

}
