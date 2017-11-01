package fr.iagl.gamification.services;

import fr.iagl.gamification.model.ClassModel;

/**
 * Service d'une classe
 * 
 * @author Hélène Meyer
 *
 */
public interface ClassService {

	/**
	 * Creer la classe
	 * 
	 * @param classe objet représentant la classe
	 */
	public void createClass(ClassModel classe);
}
