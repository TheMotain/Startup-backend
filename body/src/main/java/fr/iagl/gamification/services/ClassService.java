package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.exceptions.ClassroomExistsException;
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
	 * @throws ClassroomExistsException si la classe existe déjà en base de données 
	 */
	public ClassModel createClass(ClassModel classe) throws ClassroomExistsException;

	/**
	 * Récupère l'ensemble des classes
	 * 
	 * @return l'ensemble des classes
	 */
	public List<ClassModel> getAllClassroom();
}
