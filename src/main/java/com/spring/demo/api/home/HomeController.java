package com.spring.demo.api.home;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "hi";
	}
	
	@GetMapping("/index")
	public String index2() {
		return "h2";
	}
}
