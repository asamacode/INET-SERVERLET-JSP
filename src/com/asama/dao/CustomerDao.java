package com.asama.dao;

import java.util.List;

import com.asama.model.Customer;

public interface CustomerDao {
	
	List<String> getListCustomerId();

	List<Customer> getListCustomers();
	
	Customer getSingleCustomer(String id);
	
	boolean insertCustomer(Customer customer);
	
	boolean updateCustomer(Customer customer);
	
	boolean deleteCustomer(String id);
}
