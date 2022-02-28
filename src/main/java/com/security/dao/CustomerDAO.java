package com.security.dao;

import java.util.List;

import com.security.dto.Customer;

public interface CustomerDAO {
	
	public List<Customer> fetchCustomerByCustomerName(String username);

}
