package com.asama.model.bo;

import java.util.List;

import com.asama.model.bean.Customer;
import com.asama.model.bean.dao.CustomerDao;
import com.asama.model.bean.dao.CustomerDaoImpl;

public class CustomerBO {

	private CustomerDao customerDao;

	public CustomerBO() {
		customerDao = new CustomerDaoImpl();
	}

	public List<Customer> getCustomers() {
		return customerDao.getListCustomers();
	}

	public boolean validateCustomer(String id, String name, String addr, String phone, String mail) {
		if (!validateId(id) || name == null || name.length() < 8 || addr == null || addr.length() < 15 || phone == null
				|| phone.length() < 10 || phone.length() > 11 || mail == null || mail.length() < 5) {
			return false;
		}
		return true;
	}

	public boolean validateId(String id) {
		if (id == null || id.length() < 7) {
			return false;
		}
		return true;
	}

	public boolean saveCustomer(Customer customer) {
		return customerDao.insertCustomer(customer);
	}

	public boolean updateCustomer(Customer customer) {
		return customerDao.updateCustomer(customer);
	}

	public Customer getCustomer(String id) {
		return customerDao.getSingleCustomer(id);
	}
	
	public boolean deteleCustomer(String id) {
		return customerDao.deleteCustomer(id);
	}
	
	public List<Customer> search(String name) {
		return customerDao.filterListByName(name);
	}
}
