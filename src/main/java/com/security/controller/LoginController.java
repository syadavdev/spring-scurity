package com.security.controller;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.security.dao.SignupDAO;
import com.security.dto.ChangePasswordDTO;
import com.security.dto.SignupDTO;

@Controller
public class LoginController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JdbcUserDetailsManager jdbcUserDetailsManager;
	
	/*
	 * @Autowired SignupDAO signupDAO;
	 */
	
	@GetMapping("/myCustomLogin")
	public String login() {
		return "login";
	}
	
	@GetMapping("/signup")
	public String signup(@ModelAttribute("signupdto") SignupDTO signupDTO) {
		return "signup";
	}
	
	@PostMapping("/process-signup")
	public String processSignup(SignupDTO signupDTO) {
		String encodedPassword = passwordEncoder.encode(signupDTO.getPassword());
		signupDTO.setPassword(encodedPassword);
		
		UserDetails user = User.withUsername(signupDTO.getUsername())
		.password(signupDTO.getPassword())
		.authorities("USER").build();
		
		jdbcUserDetailsManager.createUser(user);
		
		//signupDAO.saveUser(signupDTO);
		return "redirect:/myCustomLogin";
	}
	
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username") String username) {
		jdbcUserDetailsManager.deleteUser(username);
		
		return "redirect:/myCustomLogin";
	}
	
	@GetMapping("/changePassword")
	public String changePassword(Model model) {	
		model.addAttribute("change-pwd", new ChangePasswordDTO());
		return "change-password";
	}
	
	@PostMapping("/processPassword")
	public String changePassword(Authentication auth, ChangePasswordDTO request) {
		if(!request.getNewPassword().equals(request.getConfirmPassword()))
			return "redirect:/changePassword?notMatched";
		
		UserDetails user = jdbcUserDetailsManager.loadUserByUsername(auth.getName());
		Boolean matches = passwordEncoder.matches(request.getOldPassword(), user.getPassword());
		if(!matches)
			return "redirect:/changePassword?invalidPassword";
		
		String encodedPassword = passwordEncoder.encode(request.getNewPassword());
		jdbcUserDetailsManager.changePassword(request.getOldPassword(), encodedPassword);
		
		return "redirect:/";
	}
	

}
