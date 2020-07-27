package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping(value ="/hello",method = RequestMethod.GET)
	public String home() {
		return "Shopping Cart Boot, reporting to duty test!";
	}
	

}
