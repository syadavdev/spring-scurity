package com.security.config.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.security.dao.CustomerDAO;
import com.security.dto.Customer;

@Service
public class CustomerUserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private CustomerDAO dao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = dao.fetchCustomerByCustomerName(username);
		if(customer == null)
			throw new UsernameNotFoundException("User is not found");
		
		List<GrantedAuthority> authorities = new ArrayList();
		authorities.add(new SimpleGrantedAuthority(customer.getRoles()));
		
		return User.withUsername(customer.getUsername())
				.password(customer.getPassword())
				.authorities(authorities).build();
	}

}
