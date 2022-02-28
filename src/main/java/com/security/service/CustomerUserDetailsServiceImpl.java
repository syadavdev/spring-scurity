package com.security.service;

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
		List<Customer> customers = dao.fetchCustomerByCustomerName(username);
		if(customers.size() > 0)
			throw new UsernameNotFoundException("User is not found");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(customers.get(0).getRoles()));
		
		return User.withUsername(customers.get(0).getUsername())
				.password(customers.get(0).getPassword())
				.authorities(authorities).build();
	}

}
