package com.security.dao;

import com.security.dto.Customer;

public interface CustomerDAO {
	
	public Customer fetchCustomerByCustomerName(String username);

}
