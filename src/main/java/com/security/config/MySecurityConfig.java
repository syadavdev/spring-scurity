package com.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// create filter chain

@EnableWebSecurity(debug = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * @Autowired private PasswordEncoder passwordEncorder;
	 */
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.inMemoryAuthentication()
		.withUser("sandi").password("{bcrypt}$2a$10$w89p8xrQEujsLXCjRLuP3OVax0Ncw.SpkxWzT0KPugWH2Z6izthTu").roles("admin")
		.and()
		.withUser("santi").password("{noop}santi").roles("user");

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		//.anyRequest()
		.antMatchers("/hello").authenticated()
		.antMatchers("/helloWorld").hasAnyRole("user")
		.antMatchers("/bye").permitAll()
		.and()
		.formLogin()
		.and()
		.httpBasic();
	}

}
