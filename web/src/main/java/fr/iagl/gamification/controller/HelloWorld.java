package fr.iagl.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.services.HelloWorldService;

@RestController
public class HelloWorld {
	
	@Autowired
	private HelloWorldService service;
	

	@RequestMapping("/hello")
	public String hello() {
		return service.hello();
	}
}
