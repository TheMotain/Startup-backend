package fr.iagl.gamification.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.iagl.gamification.entity.HelloWorldEntity;
import fr.iagl.gamification.repository.HelloWorldRepository;
import fr.iagl.gamification.services.HelloWorldService;

@Service
public class HelloWorldServiceImpl implements HelloWorldService{

	@Autowired
	private HelloWorldRepository repository;
	
	@Override
	public String hello() {
		List<HelloWorldEntity> result = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(result::add);
		return result.size() + "";
	}

	public void setRepository(HelloWorldRepository repository) {
		this.repository = repository;
	}

	
}
