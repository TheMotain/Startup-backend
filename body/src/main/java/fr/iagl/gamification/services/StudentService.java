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
	 * Créer un élève
	 * @param model élève à créer
	 * @return élève créé
	 */
	public StudentModel createStudent(StudentModel model);
}
