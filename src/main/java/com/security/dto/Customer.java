package com.security.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {
	
	private String username;
	private String password;
	private int enable;
	private String roles;

}
