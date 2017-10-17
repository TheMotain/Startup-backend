package fr.iagl.gamification.listener;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenerInitializer {
	
	@Autowired
	private Listener listener;
	
	@PostConstruct
	public void initialize() throws ClassNotFoundException, SQLException {
		new java.lang.Thread(listener).start();
	}
}
