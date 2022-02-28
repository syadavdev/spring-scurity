package com.security.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.security.dto.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	private JdbcTemplate template;
	
	@Override
	public List<Customer> fetchCustomerByCustomerName(String username) {
		Object[] args = {username};
		
		List<Customer> customers = template.query("select * from customer where username = ?", 
				args,
				new BeanPropertyRowMapper<Customer>(Customer.class));
		
		return customers;
	}

}
