package fr.iagl.gamification.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.SpringIntegrationTest;
import fr.iagl.gamification.services.HelloWorldService;

public class HelloWorldTest extends SpringIntegrationTest {
	@InjectMocks
	private HelloWorld controller;
	
	@Mock
	private HelloWorldService service;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testHelloWorld(){
		controller.hello();
		Mockito.verify(service, Mockito.times(1)).hello();
	}
}
