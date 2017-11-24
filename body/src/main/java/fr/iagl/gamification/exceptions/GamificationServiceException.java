package fr.iagl.gamification.exceptions;

import java.util.List;

/**
 * Exception qui gère toutes les erreurs au sein des services
 *
 * @author Hélène MEYER
 *
 */
public class GamificationServiceException extends Exception {

	
	/**
	 * defaut serial version id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Liste de toutes les erreurs
	 */
	private final List<String> errors;
	
	/**
	 * Constructeur de l'exception
	 * 
	 * @param errors la liste de toutes les erreurs
	 */
	public GamificationServiceException(List<String> errors) {
		this.errors = errors;
	}

	/**
	 * Récupération des erreurs
	 * 
	 * @return l'ensemble des messages d'erreur
	 */
	public List<String> getErrors() {
		return errors;
	}	
	
}
