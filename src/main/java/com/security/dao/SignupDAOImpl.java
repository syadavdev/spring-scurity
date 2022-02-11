package com.security.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;

import com.security.dto.SignupDTO;

@Repository
public class SignupDAOImpl implements SignupDAO{
	
	@Autowired
	private JdbcTemplate template;

	@Override
	public void saveUser(SignupDTO signupDTO) {
		String sqlUser = "insert into users values(?, ?, ?)";
		String sqlAuth = "insert into authorities values(?, ?)";
		
		//template.update(sqlUser, signupDTO.getUsername(), signupDTO.getPassword(), 1);
		//template.update(sqlAuth, signupDTO.getUsername(), "ADMIN");
		
		template.update(sqlUser, ps -> {	
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, signupDTO.getPassword());
				ps.setInt(3, 1);		
		});
		
		
		template.update(sqlAuth, ps -> {
				ps.setString(1, signupDTO.getUsername());
				ps.setString(2, "USER");
		});
		
	}

}
