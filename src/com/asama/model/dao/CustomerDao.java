package com.asama.model.dao;

import java.util.List;

import com.asama.model.bean.Customer;

public interface CustomerDao {
	
	List<String> getListCustomerId();

	List<Customer> getListCustomers();
	
	List<Customer> filterListByName(String name);
	
	Customer getSingleCustomer(String id);
	
	boolean insertCustomer(Customer customer);
	
	boolean updateCustomer(Customer customer);
	
	boolean deleteCustomer(String id);
}
