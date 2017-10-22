package fr.iagl.gamification.listener;

import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Initialise le thread qui va écouter et traiter tous les changements de la base de données
 * 
 * @author Hélène Meyer
 *
 */
@Component
public class ListenerInitializer {
	
	/**
	 * Thread qui va écouter et traiter tous les changements de la base de données
	 */
	@Autowired
	private Listener listener;
	
	/**
	 * Démarrage du thread qui va écouter et traiter tous les changements de la base de données
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InterruptedException
	 */
	@PostConstruct
	public void initialize() throws ClassNotFoundException, SQLException, InterruptedException {
		new java.lang.Thread(listener).start();
	}
}
