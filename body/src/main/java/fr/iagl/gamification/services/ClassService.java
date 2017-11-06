package fr.iagl.gamification.services;

import fr.iagl.gamification.exceptions.ClassExistsException;
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
	 * @return la classe créée
	 * @throws ClassExistsException si la classe existe déjà en base de données 
	 */
	public ClassModel createClass(ClassModel classe) throws ClassExistsException;
}
