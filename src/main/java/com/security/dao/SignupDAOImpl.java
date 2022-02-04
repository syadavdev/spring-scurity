package com.security.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.security.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public void saveUser(SignupDTO signupDTO) {
		String sqlUsers = "insert into users values(?, ? , ?)";
		template.update(sqlUsers, signupDTO.getUsername(), signupDTO.getPassword(), 1);
		
		String sqlAuth = "insert into authorities values(?, ?)";
		template.update(sqlAuth, signupDTO.getUsername(), "ADMIN");
	}

}
