package com.security.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	
	@GetMapping("/hello")
	public String getHello(Principal principal, Authentication auth, Model model) {
		System.out.println("Logged in user : " + auth.getName());
		System.out.println("Authorities : " + auth.getAuthorities());
		
		model.addAttribute("username", principal.getName());
		model.addAttribute("authorities", auth.getAuthorities());
		
		return "hello-world";
	}
	
	
	@ResponseBody
	@GetMapping("/bye")
	public String bye() {
		return "bye bye";
	}
	
	@ResponseBody
	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/")
	public String homePage(Principal principal, Authentication auth, Model model) {
		model.addAttribute("username", principal.getName());
		model.addAttribute("roles", auth.getAuthorities());
		return "home-page";
	}
	
	@GetMapping("/admin")
	public String admin() {
		return "admin-page";
	}
		
	@GetMapping("/user")
	public String user() {
		return "user-page";
	}
	
	@GetMapping("/accessDenied")
	public String accessDenied() {
		return "access-denied";
	}
	
}
