package com.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	
	@GetMapping("/hello")
	public String getHello() {
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

}
