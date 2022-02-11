package com.security.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages =  {"com"})
public class MyAppConfig {
	
	@Bean
	InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	/*
	 * @Bean public PasswordEncoder getPasswordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	
	@Bean
	public PasswordEncoder getPasswordEncoder() { 
		 return NoOpPasswordEncoder.getInstance(); 
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		JdbcTemplate template = new JdbcTemplate(dataSource());
		return template;
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailManager() {
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
		jdbcUserDetailsManager.setDataSource(dataSource());
		return jdbcUserDetailsManager;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setUrl("jdbc:mysql://localhost:3306/test");
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");;
		return dataSource;
	}
	
}
