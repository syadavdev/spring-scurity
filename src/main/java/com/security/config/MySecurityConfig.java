package com.security.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

// create filter chain

@EnableWebSecurity(debug = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	/*
	 * @Autowired private PasswordEncoder passwordEncorder;
	 */
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * auth .inMemoryAuthentication() .withUser("sandi").password(
		 * "{bcrypt}$2a$10$w89p8xrQEujsLXCjRLuP3OVax0Ncw.SpkxWzT0KPugWH2Z6izthTu").roles
		 * ("admin") .and() .withUser("santi").password("{noop}santi").roles("user");
		 */
		 
		
		auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
		
		/*
		 * UserDetailsManager manager = new InMemoryUserDetailsManager();
		 * 
		 * UserDetails sandeep =
		 * User.withUsername("sandi").password("sandi").roles("ADMIN").build();
		 * UserDetails santi =
		 * User.withUsername("santi").password("santi").roles("ADMIN,USER").build();
		 * 
		 * manager.createUser(santi); manager.createUser(sandeep);
		 * 
		 * auth.userDetailsService(manager);
		 */

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http .authorizeRequests() .antMatchers("/hello").authenticated()
		 * .antMatchers("/helloWorld").hasAnyRole("user")
		 * .antMatchers("/bye").permitAll() .antMatchers("/myCustomLogin").permitAll()
		 * .and() .formLogin().loginPage("/myCustomLogin") .and() .httpBasic() .and()
		 * .logout();
		 */
		
		http
		.authorizeRequests()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/user").hasAuthority("USER")
		.and()
		.formLogin().loginPage("/myCustomLogin").permitAll()
		.and()
		.httpBasic()
		.and()
		.logout()
		.and()
		.exceptionHandling().accessDeniedPage("/accessDenied");
	}

}
