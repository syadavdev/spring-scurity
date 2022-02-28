package com.security.authenticationproviders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.security.dao.CustomerDAO;
import com.security.dto.Customer;

@Component
public class MyCustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CustomerDAO customDAO;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<Customer> customers = customDAO.fetchCustomerByCustomerName(username);
		
		if(customers.size() > 0 && encoder.matches(password, customers.get(0).getPassword())) {
			
			String roles = customers.get(0).getRoles();
			List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
			auths.add(new SimpleGrantedAuthority(roles));
			
			return new UsernamePasswordAuthenticationToken(username, password, auths);
		}
		else
			throw new BadCredentialsException("Invalid username / password");
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
