package fr.iagl.gamification.services;

import java.util.List;

import fr.iagl.gamification.model.StudentModel;

/**
 * Interface de gestion des élèves
 * @author ALEX
 *
 */
public interface StudentService {

	/**
	 * Récupère tous les élèves
	 * @return Liste d'élève
	 */
	public List<StudentModel> getAllStudent();
	
	/**
	 * Créer ou Modifier un élève
	 * @param model élève à créer ou à modifier
	 * @return élève créé ou modifié
	 */
	public StudentModel createOrUpdateStudent(StudentModel model);

}
