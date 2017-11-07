package fr.iagl.gamification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import fr.iagl.gamification.services.HelloWorldService;

@RestController
public class HelloWorld {
	
	@Autowired
	private HelloWorldService service;

	@RequestMapping(path = "/hello", method = RequestMethod.GET)
	public String hello() {
		return service.hello();
	}
	
	public void setService(HelloWorldService service) {
		this.service = service;
	}
}
