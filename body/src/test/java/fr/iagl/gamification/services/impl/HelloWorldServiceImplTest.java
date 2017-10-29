package fr.iagl.gamification.services.impl;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.iagl.gamification.repository.HelloWorldRepository;

public class HelloWorldServiceImplTest {
	
	@InjectMocks
	private HelloWorldServiceImpl service;
	
	@Mock
	private HelloWorldRepository repository;
	
	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testHello(){
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<>());
		service.hello();
		Mockito.verify(repository, Mockito.times(1)).findAll();
	}
}
